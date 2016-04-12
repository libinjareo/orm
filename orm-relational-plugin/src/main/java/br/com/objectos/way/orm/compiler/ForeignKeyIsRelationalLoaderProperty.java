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

import br.com.objectos.core.collections.ImmutableList;
import br.com.objectos.way.relational.ResultSetWrapper;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class ForeignKeyIsRelationalLoaderProperty extends IsRelationalLoaderProperty<ForeignKeyOrmProperty> {

  private ForeignKeyIsRelationalLoaderProperty(ForeignKeyOrmProperty ormProperty) {
    super(ormProperty);
  }

  public static ForeignKeyIsRelationalLoaderProperty of(ForeignKeyOrmProperty ormProperty) {
    return new ForeignKeyIsRelationalLoaderProperty(ormProperty);
  }

  @Override
  public Stream<MethodSpec> execute() {
    ParameterSpec param0 = ParameterSpec.builder(ResultSetWrapper.class, "rs").build();
    ParameterSpec param1 = ParameterSpec.builder(String.class, "columnName").build();
    List<ParameterSpec> parameterList = ImmutableList.of(param0, param1);
    TypeName returnTypeName = ormProperty.returnType().actualTypeName();
    RelationalTypeName typeName = typeName();
    return Stream.of(
        m0(parameterList, returnTypeName, typeName),
        m1(parameterList, returnTypeName, typeName));
  }

  private MethodSpec m0(List<ParameterSpec> parameterList, TypeName returnTypeName, RelationalTypeName rtName) {
    return MethodSpec.methodBuilder(propertyName())
        .addParameters(parameterList)
        .returns(returnTypeName)
        .addStatement("return $L(rs, columnName, rs.$L(columnName))", propertyName(), rtName.resultSetMethod())
        .build();
  }

  private MethodSpec m1(List<ParameterSpec> parameterList, TypeName returnTypeName, RelationalTypeName rtName) {
    return MethodSpec.methodBuilder(propertyName())
        .addModifiers(Modifier.ABSTRACT)
        .returns(returnTypeName)
        .addParameters(parameterList)
        .addParameter(rtName.typeName(), "key")
        .build();
  }

  private RelationalTypeName typeName() {
    List<ColumnOrmProperty> referencedPropertyList = ormProperty.referencedPropertyList();
    ColumnOrmProperty referencedMethod = referencedPropertyList.get(0);
    TypeName returnTypeName = referencedMethod.returnType().typeName();
    return RelationalTypeName.of(returnTypeName);
  }

}