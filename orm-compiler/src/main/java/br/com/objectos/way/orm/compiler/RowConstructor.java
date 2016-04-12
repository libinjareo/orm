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

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.lang.model.element.Modifier;

import br.com.objectos.way.code.ParameterInfo;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class RowConstructor extends AbstractConstructor {

  private RowConstructor(ConstructorContext context) {
    super(context);
  }

  public static RowConstructor of(ConstructorContext context) {
    return new RowConstructor(context);
  }

  @Override
  public MethodSpec execute() {
    OrmPojoInfo pojoInfo = context.pojoInfo();
    OrmInject inject = context.inject();

    AtomicInteger i = new AtomicInteger(1);
    Stream<String> ctrStream = context.parameterInfoStream().map(ParameterInfo::name);
    Stream<String> colStream = pojoInfo.propertyList().stream()
        .sorted()
        .map(property -> property.rowConstructorParameterName(i));

    return constructor
        .addModifiers(Modifier.PUBLIC)
        .addParameter(inject.parameterSpec())
        .addParameters(context.parameterSpecList())
        .addParameters(foreignKeyParameterSpecList())
        .addParameter(rowParameterSpec(pojoInfo.columnPropertyList()))
        .addCode("this($L, ", inject.name())
        .addCode(Stream.concat(ctrStream, colStream).collect(Collectors.joining(", ")))
        .addStatement(")")
        .build();
  }

  private List<ParameterSpec> foreignKeyParameterSpecList() {
    OrmPojoInfo pojoInfo = context.pojoInfo();
    return pojoInfo.foreignKeyPropertyList().stream()
        .map(ForeignKeyOrmProperty::parameterSpec)
        .collect(Collectors.toList());
  }

}