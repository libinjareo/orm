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

import javax.inject.Inject;

import com.squareup.javapoet.MethodSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class CompanionTypeInject implements CompanionTypeExe {

  private final OrmInject inject;

  private CompanionTypeInject(OrmInject inject) {
    this.inject = inject;
  }

  public static CompanionTypeInject of(OrmPojoInfo pojoInfo) {
    OrmInject inject = pojoInfo.inject();
    return new CompanionTypeInject(inject);
  }

  @Override
  public CompanionType acceptCompanionType(CompanionType type) {
    return type
        .addField(inject.fieldSpec())
        .addMethod(MethodSpec.constructorBuilder()
            .addAnnotation(Inject.class)
            .addParameter(inject.parameterSpec())
            .addCode(inject.assignToFieldStatement())
            .build());
  }

}