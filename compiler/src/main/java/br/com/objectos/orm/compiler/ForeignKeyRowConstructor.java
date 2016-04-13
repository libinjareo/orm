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
import java.util.stream.IntStream;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.MethodSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class ForeignKeyRowConstructor extends AbstractConstructor {

  private ForeignKeyRowConstructor(ConstructorContext context) {
    super(context);
  }

  public static ForeignKeyRowConstructor of(ConstructorContext context) {
    return new ForeignKeyRowConstructor(context);
  }

  @Override
  public MethodSpec execute() {
    OrmPojoInfo pojoInfo = context.pojoInfo();
    List<OrmProperty> propertyList = pojoInfo.propertyList();
    OrmInject inject = context.inject();
    return constructor
        .addModifiers(Modifier.PUBLIC)
        .addParameter(inject.parameterSpec())
        .addParameters(context.parameterSpecList())
        .addParameter(rowParameterSpec(propertyList))
        .addStatement("this($L)", Joiner.on(", ")
            .add(inject.name())
            .addAll(context.parameterSpecList().stream().map(spec -> spec.name))
            .addAll(IntStream.rangeClosed(1, propertyList.size()).mapToObj(i -> "row.column" + i + "()"))
            .join())
        .build();
  }

}