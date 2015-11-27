/*
 * Copyright 2014-2015 Objectos, Fábrica de Software LTDA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package br.com.objectos.orm.compiler;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.lang.model.element.Modifier;

import br.com.objectos.db.query.NoResultFoundException;
import br.com.objectos.pojo.plugin.Naming;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class CompanionTypeFind implements CompanionTypeExe {

  private static final Noop NOOP = new Noop();

  public static CompanionTypeFind of(OrmPojoInfo pojoInfo) {
    TableInfoMap tableInfoMap = pojoInfo.tableInfoMap();
    return tableInfoMap.containsPrimaryKey() ? Pk.of(pojoInfo, tableInfoMap) : NOOP;
  }

  @Override
  public CompanionType acceptCompanionType(CompanionType type) {
    return type;
  }

  private static class Noop extends CompanionTypeFind {}

  private static class Pk extends CompanionTypeFind {

    private final Naming naming;
    private final List<ParameterSpec> primaryKeyParameterSpecList;

    public Pk(Naming naming, List<ParameterSpec> primaryKeyParameterSpecList) {
      this.naming = naming;
      this.primaryKeyParameterSpecList = primaryKeyParameterSpecList;
    }

    public static CompanionTypeFind of(OrmPojoInfo pojoInfo, TableInfoMap tableInfoMap) {
      AtomicInteger i = new AtomicInteger(0);
      return new Pk(
          pojoInfo.naming(),
          tableInfoMap.primaryKeyPropertyList()
              .stream()
              .map(property -> property.parameterSpec("pk" + i.getAndIncrement()))
              .collect(Collectors.toList()));
    }

    @Override
    public CompanionType acceptCompanionType(CompanionType type) {
      return type
          .addMethod(findByPrimaryKey0())
          .addMethod(findByPrimaryKey1());
    }

    private MethodSpec findByPrimaryKey0() {
      String paramNames = primaryKeyParameterSpecList.stream()
          .map(param -> param.name)
          .collect(Collectors.joining(", "));
      return MethodSpec.methodBuilder("find")
          .addModifiers(Modifier.PUBLIC)
          .addParameters(primaryKeyParameterSpecList)
          .returns(naming.superClassTypeName())
          .addStatement("return maybe($L).orElseThrow($T::new)", paramNames, NoResultFoundException.class)
          .build();
    }

    private MethodSpec findByPrimaryKey1() {
      ClassName optionalClassName = ClassName.get(Optional.class);
      return MethodSpec.methodBuilder("maybe")
          .addModifiers(Modifier.PUBLIC)
          .addParameters(primaryKeyParameterSpecList)
          .returns(ParameterizedTypeName.get(optionalClassName, naming.superClassTypeName()))
          .addStatement("return $T.empty()", optionalClassName)
          .build();
    }

  }

}