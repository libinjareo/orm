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

import java.util.stream.Stream;

import br.com.objectos.orm.compiler.BindType;
import br.com.objectos.orm.compiler.ColumnOrmProperty;
import br.com.objectos.way.relational.ResultSetWrapper;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class ColumnIsRelationalLoaderProperty extends IsRelationalLoaderProperty<ColumnOrmProperty> {

  private ColumnIsRelationalLoaderProperty(ColumnOrmProperty ormProperty) {
    super(ormProperty);
  }

  public static ColumnIsRelationalLoaderProperty of(ColumnOrmProperty ormProperty) {
    return new ColumnIsRelationalLoaderProperty(ormProperty);
  }

  @Override
  public Stream<MethodSpec> execute() {
    return Stream.of(MethodSpec.methodBuilder(propertyName())
        .addParameter(ResultSetWrapper.class, "rs")
        .addParameter(String.class, "columnName")
        .returns(returns())
        .addStatement("return rs.$L(columnName)", resultSetMethod())
        .build());
  }

  private String resultSetMethod() {
    TypeName returnTypeName = returnTypeName();
    RelationalTypeName typeName = RelationalTypeName.of(returnTypeName);
    BindType bindType = ormProperty.bindType();
    switch (bindType) {
    case BOOLEAN_INT:
    case ENUM_ORDINAL:
      return RelationalTypeName.INT.resultSetMethod();
    case ENUM_SQL:
    case ENUM_STRING:
      return RelationalTypeName.STRING.resultSetMethod();
    case STANDARD:
    default:
      return typeName.resultSetMethod();
    }
  }

  private TypeName returns() {
    TypeName returnTypeName = returnTypeName();
    BindType bindType = ormProperty.bindType();
    switch (bindType) {
    case BOOLEAN_INT:
    case ENUM_ORDINAL:
      return TypeName.INT;
    case ENUM_SQL:
    case ENUM_STRING:
      return TypeName.get(String.class);
    case STANDARD:
    default:
      return returnTypeName;
    }
  }

}