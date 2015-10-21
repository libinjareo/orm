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
import br.com.objectos.collections.ImmutableList;
import br.com.objectos.pojo.Pojo;

import com.squareup.javapoet.TypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class OptionalSqlPojoReturnType extends SqlPojoReturnType {

  @Override
  abstract TypeName enclosedTypeName();

  OptionalSqlPojoReturnType() {
  }

  public static OptionalSqlPojoReturnTypeBuilder builder() {
    return new OptionalSqlPojoReturnTypeBuilderPojo();
  }

  @Override
  public ColumnSqlPojoBindType bindType(SimpleTypeInfo returnTypeInfo, TypeInfo columnClassTypeInfo) {
    SimpleTypeInfo enclosedTypeInfo = returnTypeInfo.getTypeParameterInfoStream()
        .findFirst()
        .map(TypeParameterInfo::simpleTypeInfo)
        .get();
    return ColumnSqlPojoBindType.of(enclosedTypeInfo, columnClassTypeInfo);
  }

  @Override
  public String constructorCode(String code) {
    return String.format("$T.ofNullable(%s)", super.constructorCode(code));
  }

  @Override
  public List<Object> constructorArgs(List<Object> list) {
    return ImmutableList.<Object> builder()
        .add(Optional.class)
        .addAll(list)
        .build();
  }

  @Override
  public String findByPrimaryKeyMethodName() {
    return "maybe";
  }

  @Override
  public boolean isOptional() {
    return true;
  }

}