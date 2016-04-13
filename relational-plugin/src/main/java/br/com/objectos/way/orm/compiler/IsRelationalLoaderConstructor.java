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

import com.squareup.javapoet.MethodSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class IsRelationalLoaderConstructor extends AbstractConstructor {

  private IsRelationalLoaderConstructor(ConstructorContext context) {
    super(context);
  }

  public static IsRelationalLoaderConstructor of(ConstructorContext context) {
    return new IsRelationalLoaderConstructor(context);
  }

  @Override
  public MethodSpec execute() {
    OrmPojoInfo pojoInfo = context.pojoInfo();
    OrmInject inject = pojoInfo.inject();

    MethodSpec.Builder c = MethodSpec.constructorBuilder()
        .addParameter(inject.parameterSpec())
        .addParameters(context.parameterSpecList())
        .addCode(inject.assignToFieldStatement());

    context
        .parameterSpecList()
        .forEach(param -> c.addStatement("this.$1L = $1L", param.name));

    return c.build();
  }

}