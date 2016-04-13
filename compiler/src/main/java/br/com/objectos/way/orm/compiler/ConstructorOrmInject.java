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

import javax.lang.model.element.Modifier;

import br.com.objectos.code.ParameterInfo;
import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.pojo.plugin.Contribution;
import br.com.objectos.testable.Equality;
import br.com.objectos.testable.Tester;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class ConstructorOrmInject extends OrmInject {

  private final TypeName typeName;
  private final String name;

  private ConstructorOrmInject(TypeName typeName, String name) {
    this.typeName = typeName;
    this.name = name;
  }

  public static ConstructorOrmInject of(ParameterInfo parameterInfo) {
    SimpleTypeInfo simpleTypeInfo = parameterInfo.simpleTypeInfo();
    return new ConstructorOrmInject(
        simpleTypeInfo.typeName(),
        parameterInfo.name());
  }

  @Override
  public Contribution execute() {
    return Contribution.builder()
        .addField(FieldSpec.builder(typeName(), name())
            .addModifiers(Modifier.FINAL)
            .build())
        .addPojoConstructorStatement("this.$1L = $1L", name)
        .build();
  }

  @Override
  public Equality isEqualTo(Object that) {
    return Tester.of(ConstructorOrmInject.class)
        .add("typeName", o -> o.typeName)
        .add("name", o -> o.name)
        .test(this, that);
  }

  @Override
  String name() {
    return name;
  }

  @Override
  TypeName typeName() {
    return typeName;
  }

}