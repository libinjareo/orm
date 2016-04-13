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

import br.com.objectos.code.ConstructorInfo;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class ColumnsConstructor extends AbstractConstructor {

  private ColumnsConstructor(ConstructorContext context) {
    super(context);
  }

  public static ColumnsConstructor of(ConstructorContext context) {
    return new ColumnsConstructor(context);
  }

  @Override
  public MethodSpec execute() {
    OrmInject inject = context.inject();
    ConstructorInfo constructorInfo = context.constructorInfo();

    constructor
        .addModifiers(Modifier.PUBLIC)
        .addParameter(inject.parameterSpec())
        .addParameters(context.parameterSpecList())
        .addCode(constructorInfo.statementWriter()
            .addStandardSuperStatement()
            .write())
        .addCode(inject.assignToFieldStatement());

    OrmPojoInfo pojoInfo = context.pojoInfo();
    pojoInfo.propertyList().stream()
        .sorted()
        .forEach(property -> property.acceptColumnsConstructor(this));

    return constructor.build();
  }

  public void set(TypeName typeName, String name) {
    constructor
        .addParameter(typeName, name)
        .addStatement("this.$1L = $1L", name);
  }

}