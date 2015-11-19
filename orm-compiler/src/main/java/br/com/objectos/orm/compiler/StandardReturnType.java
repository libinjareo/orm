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

import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.pojo.plugin.PojoProperty;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class StandardReturnType extends ReturnType {

  StandardReturnType() {
  }

  public static StandardReturnType get(SimpleTypeInfo returnTypeInfo) {
    return StandardReturnType.builder()
        .typeName(returnTypeInfo.typeName())
        .build();
  }

  static StandardReturnTypeBuilder builder() {
    return new StandardReturnTypeBuilderPojo();
  }

  @Override
  public <T> T adapt(ReturnTypeAdapter<T> adapter) {
    return adapter.onStandard(this);
  }

  @Override
  PojoProperty constructorStatement(ColumnOrmProperty property) {
    return property.standardConstructorStatement();
  }

  @Override
  PojoProperty method(ColumnOrmProperty property) {
    return property.standardMethod();
  }

}