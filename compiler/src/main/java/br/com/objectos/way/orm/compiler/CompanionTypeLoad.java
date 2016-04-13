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
package br.com.objectos.way.orm.compiler;

import java.util.List;
import java.util.stream.Stream;

import javax.lang.model.element.Modifier;

import br.com.objectos.pojo.plugin.Naming;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class CompanionTypeLoad implements CompanionTypeExe {

  private final OrmPojoInfo pojoInfo;
  private final Naming naming;
  private final OrmInject inject;

  private CompanionTypeLoad(OrmPojoInfo pojoInfo, Naming naming, OrmInject inject) {
    this.pojoInfo = pojoInfo;
    this.naming = naming;
    this.inject = inject;
  }

  public static CompanionTypeLoad of(OrmPojoInfo pojoInfo) {
    return new CompanionTypeLoad(
        pojoInfo,
        pojoInfo.naming(),
        pojoInfo.inject());
  }

  @Override
  public CompanionType acceptCompanionType(CompanionType type) {
    return type.addMethods(pojoInfo.constructorContextList()
        .stream()
        .map(Constructor::new)
        .flatMap(Constructor::stream));
  }

  private class Constructor {

    private final List<ParameterSpec> parameterSpecList;

    public Constructor(ConstructorContext context) {
      parameterSpecList = context.parameterSpecList();
    }

    public Stream<MethodSpec> stream() {
      return pojoInfo.hasForeignKeys()
          ? Stream.of(load0(), load1())
          : Stream.of(load0());
    }

    private MethodSpec load0() {
      return MethodSpec.methodBuilder("load")
          .addModifiers(Modifier.PUBLIC)
          .returns(naming.superClassTypeName())
          .addParameters(parameterSpecList)
          .addParameters(pojoInfo.foreignKeyParameterSpecList())
          .addParameter(pojoInfo.rowParameterSpecColumns())
          .addCode("return new $T($L, ", naming.pojo(), inject.name())
          .addCode(Joiner.on(", ")
              .addAll(parameterSpecList.stream().map(spec -> spec.name))
              .addAll(pojoInfo.foreignKeyPropertyList().stream().map(OrmProperty::name))
              .add("row")
              .join())
          .addStatement(")")
          .build();
    }

    private MethodSpec load1() {
      return MethodSpec.methodBuilder("load")
          .addModifiers(Modifier.PUBLIC)
          .returns(naming.superClassTypeName())
          .addParameters(parameterSpecList)
          .addParameter(pojoInfo.rowParameterSpec())
          .addCode("return new $T($L, ", naming.pojo(), inject.name())
          .addCode(Joiner.on(", ")
              .addAll(parameterSpecList.stream().map(spec -> spec.name))
              .add("row")
              .join())
          .addStatement(")")
          .build();
    }

  }

}