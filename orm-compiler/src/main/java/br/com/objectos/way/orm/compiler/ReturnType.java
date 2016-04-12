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
package br.com.objectos.way.orm.compiler;

import java.util.Optional;

import javax.lang.model.element.Modifier;

import br.com.objectos.way.pojo.plugin.PojoProperty;
import br.com.objectos.way.testable.Testable;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.TypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
abstract class ReturnType implements Testable {

  abstract TypeName typeName();
  abstract Optional<ClassName> companionTypeClassName();

  ReturnType() {
  }

  public abstract <T> T adapt(ReturnTypeAdapter<T> adapter);

  public PojoProperty executePojoProperty(ColumnOrmProperty property) {
    return PojoProperty.of(
        constructorStatement(property),
        field(property),
        method(property));
  }

  public abstract String findByPrimaryKeyMethodName();

  abstract void acceptQueryCollectExpression(CodeBlock.Builder collectCode, ClassName ownerClassName);

  TypeName actualTypeName() {
    return typeName();
  }

  abstract PojoProperty constructorStatement(ColumnOrmProperty property);

  abstract PojoProperty method(ColumnOrmProperty property);

  private PojoProperty field(ColumnOrmProperty property) {
    return PojoProperty.fieldBuilder(property.property())
        .modifiers(Modifier.PRIVATE, Modifier.FINAL)
        .type(property.columnClassName())
        .build();
  }

}