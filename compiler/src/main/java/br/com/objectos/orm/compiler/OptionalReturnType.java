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

import java.util.Optional;

import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.pojo.plugin.PojoProperty;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.TypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class OptionalReturnType extends ReturnType {

  @Override
  abstract TypeName actualTypeName();

  OptionalReturnType() {
  }

  public static OptionalReturnType get(SimpleTypeInfo returnTypeInfo, SimpleTypeInfo actualTypeInfo) {
    return OptionalReturnType.builder()
        .typeName(returnTypeInfo.typeName())
        .companionTypeClassNameOfNullable(returnTypeInfo.isPrimitive() ? null : returnTypeInfo.classNameSuffix("Orm"))
        .actualTypeName(actualTypeInfo.typeName())
        .build();
  }

  private static OptionalReturnTypeBuilder builder() {
    return new OptionalReturnTypeBuilderPojo();
  }

  @Override
  public <T> T adapt(ReturnTypeAdapter<T> adapter) {
    return adapter.onOptional(this);
  }

  @Override
  public String findByPrimaryKeyMethodName() {
    return "maybe";
  }

  @Override
  void acceptQueryCollectExpression(CodeBlock.Builder collectCode, ClassName ownerClassName) {
    collectCode.add("$T.of($T.this)", Optional.class, ownerClassName);
  }

  @Override
  PojoProperty constructorStatement(ColumnOrmProperty property) {
    return property.optionalConstructorStatement();
  }

  @Override
  PojoProperty method(ColumnOrmProperty property) {
    return property.optionalMethod();
  }

}