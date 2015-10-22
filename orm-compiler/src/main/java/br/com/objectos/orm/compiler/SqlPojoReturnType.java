/*
 * Copyright 2015 Objectos, Fábrica de Software LTDA.
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

import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.code.TypeParameterInfo;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.testable.Testable;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class SqlPojoReturnType implements Testable {

  abstract TypeName typeName();
  abstract Optional<ClassName> sqlClassName();

  SqlPojoReturnType() {
  }

  public static SqlPojoReturnType of(SimpleTypeInfo returnTypeInfo) {
    return returnTypeInfo.isInfoOf(Optional.class)
        ? ofOptional(returnTypeInfo)
        : SqlPojoReturnType.standardBuilder()
            .typeName(returnTypeInfo.typeName())
            .sqlClassNameOfNullable(returnTypeInfo.isPrimitive() ? null : returnTypeInfo.classNameSuffix("Sql"))
            .build();
  }

  static SqlPojoReturnTypeBuilder standardBuilder() {
    return new SqlPojoReturnTypeBuilderPojo();
  }

  private static OptionalSqlPojoReturnType ofOptional(SimpleTypeInfo returnTypeInfo) {
    TypeInfo enclosedTypeInfo = returnTypeInfo.getTypeParameterInfoStream()
        .findFirst()
        .flatMap(TypeParameterInfo::typeInfo)
        .get();
    TypeName enclosedTypeName = enclosedTypeInfo.typeName();
    return OptionalSqlPojoReturnType.builder()
        .typeName(returnTypeInfo.typeName())
        .sqlClassNameOfNullable(enclosedTypeName.isPrimitive() ? null : enclosedTypeInfo.classNameSuffix("Sql"))
        .enclosedTypeName(enclosedTypeInfo.typeName())
        .build();
  }

  public ColumnPropertyBindType bindType(SimpleTypeInfo returnTypeInfo, TypeInfo columnClassTypeInfo) {
    return ColumnPropertyBindType.of(returnTypeInfo, columnClassTypeInfo);
  }

  public String constructorCode(String code) {
    return code;
  }

  public List<Object> constructorArgs(List<Object> list) {
    return list;
  }

  public String findByPrimaryKeyMethodName() {
    return "find";
  }

  public boolean isOptional() {
    return false;
  }

  public boolean isPrimitive() {
    return typeName().isPrimitive();
  }

  TypeName enclosedTypeName() {
    return typeName();
  }

}