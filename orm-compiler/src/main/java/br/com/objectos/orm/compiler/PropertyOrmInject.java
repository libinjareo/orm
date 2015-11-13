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
import br.com.objectos.pojo.plugin.Contribution;
import br.com.objectos.pojo.plugin.Property;
import br.com.objectos.testable.Equality;
import br.com.objectos.testable.Tester;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class PropertyOrmInject extends OrmInject {

  private final Property property;

  public PropertyOrmInject(Property property) {
    this.property = property;
  }

  public static OrmInject of(Property property) {
    return new PropertyOrmInject(property);
  }

  @Override
  public Equality isEqualTo(Object that) {
    return Tester.of(PropertyOrmInject.class)
        .add("property", o -> o.property)
        .test(this, that);
  }

  @Override
  Contribution get() {
    SimpleTypeInfo returnTypeInfo = property.returnTypeInfo();
    return Contribution.builder()
        .addCustomField(returnTypeInfo.typeName(), property.name())
        .addPojoProperty(property.standardPojoMethod())
        .build();
  }

}