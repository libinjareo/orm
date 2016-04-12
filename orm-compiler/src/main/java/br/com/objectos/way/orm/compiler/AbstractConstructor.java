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

import br.com.objectos.way.sql.Row;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
abstract class AbstractConstructor {

  final MethodSpec.Builder constructor = MethodSpec.constructorBuilder();
  final ConstructorContext context;

  public AbstractConstructor(ConstructorContext context) {
    this.context = context;
  }

  public abstract MethodSpec execute();

  ParameterSpec rowParameterSpec(List<? extends OrmProperty> propertyList) {
    return ParameterSpec.builder(rowTypeName(propertyList), "row").build();
  }

  private ParameterizedTypeName rowTypeName(List<? extends OrmProperty> propertyList) {
    ClassName rowClassName = ClassName.get(Row.class).peerClass("Row" + propertyList.size());
    ClassName[] columnClassNameArray = propertyList.stream()
        .sorted()
        .flatMap(OrmProperty::columnClassNameStream)
        .toArray(ClassName[]::new);
    return ParameterizedTypeName.get(rowClassName, columnClassNameArray);
  }

}