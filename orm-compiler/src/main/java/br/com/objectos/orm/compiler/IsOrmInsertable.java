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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.lang.model.element.Modifier;

import br.com.objectos.collections.ImmutableList;
import br.com.objectos.orm.InsertableRowBinder;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.pojo.plugin.Contribution;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class IsOrmInsertable implements OrmInsertable {

  abstract ParameterizedTypeName insertableRowTypeName();
  abstract ParameterizedTypeName insertableRowValuesTypeName();
  abstract List<String> valueNameList();

  IsOrmInsertable() {
  }

  public static IsOrmInsertable of(List<OrmProperty> propertyList) {
    List<ClassName> columnClassNameList = new ArrayList<>();
    ImmutableList.Builder<String> valueNameList = ImmutableList.builder();

    for (OrmProperty property : propertyList) {
      property.columnClassNameStream().forEach(columnClassNameList::add);
      valueNameList.add(property.name());
    }

    ClassName[] columnClassNameArray = columnClassNameList.toArray(new ClassName[] {});

    return IsOrmInsertable.builder()
        .insertableRowTypeName(Naming.insertableRowTypeName(columnClassNameArray))
        .insertableRowValuesTypeName(Naming.insertableRowValuesTypeName(columnClassNameArray))
        .valueNameList(valueNameList.build())
        .build();
  }

  static IsOrmInsertableBuilder builder() {
    return new IsOrmInsertableBuilderPojo();
  }

  @Override
  public Contribution execute() {
    return Contribution.builder()
        .addSuperinterface(superinterface())
        .addMethod(bindInsertableRow())
        .build();
  }

  private MethodSpec bindInsertableRow() {
    return MethodSpec.methodBuilder("bindInsertableRow")
        .addAnnotation(Override.class)
        .addModifiers(Modifier.PUBLIC)
        .addParameter(insertableRowTypeName(), "row")
        .returns(insertableRowValuesTypeName())
        .addStatement("return row.values($L)", valueNameList().stream().collect(Collectors.joining(", ")))
        .build();
  }

  private TypeName superinterface() {
    ClassName rawType = ClassName.get(InsertableRowBinder.class);
    return ParameterizedTypeName.get(rawType, insertableRowTypeName(), insertableRowValuesTypeName());
  }

}