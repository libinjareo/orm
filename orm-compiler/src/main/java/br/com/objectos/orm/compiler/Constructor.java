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

import java.util.List;
import java.util.stream.Collectors;

import javax.lang.model.element.Modifier;

import br.com.objectos.code.ConstructorInfo;
import br.com.objectos.code.ParameterInfo;
import br.com.objectos.pojo.plugin.Contribution;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class Constructor {

  private final OrmPojoInfo pojoInfo;
  private final OrmInject inject;
  private final ConstructorInfo constructorInfo;

  private Constructor(OrmPojoInfo pojoInfo, OrmInject inject, ConstructorInfo constructorInfo) {
    this.pojoInfo = pojoInfo;
    this.inject = inject;
    this.constructorInfo = constructorInfo;
  }

  public static Constructor of(OrmPojoInfo pojoInfo, OrmInject inject, ConstructorInfo constructorInfo) {
    return new Constructor(pojoInfo, inject, constructorInfo);
  }

  public void accept(Contribution.Builder builder) {
    builder.addMethod(constructor1());
  }

  private MethodSpec constructor1() {
    MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
        .addModifiers(Modifier.PUBLIC)
        .addParameter(inject.parameterSpec())
        .addParameters(parameterSpecList())
        .addCode(constructorInfo.statementWriter()
            .addStandardSuperStatement()
            .write())
        .addCode(inject.assignToFieldStatement());

    pojoInfo.propertyList().stream()
        .sorted()
        .forEach(property -> property.acceptConstructor1(constructor));

    return constructor.build();
  }

  private List<ParameterSpec> parameterSpecList() {
    return constructorInfo.parameterInfoStream()
        .map(ParameterInfo::parameterSpec)
        .collect(Collectors.toList());
  }

}