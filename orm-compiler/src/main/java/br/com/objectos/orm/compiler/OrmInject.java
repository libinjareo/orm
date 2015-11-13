/*
 * Copyright 2015 Objectos, Fábrica de Software LTDA.
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
import br.com.objectos.orm.Orm;
import br.com.objectos.pojo.plugin.Contribution;
import br.com.objectos.pojo.plugin.PojoInfo;
import br.com.objectos.testable.Testable;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
abstract class OrmInject implements Testable {

  OrmInject() {
  }

  public static OrmInject of(PojoInfo pojoInfo) {
    return pojoInfo.ignoredPropertyStream()
        .filter(property -> {
          SimpleTypeInfo returnTypeInfo = property.returnTypeInfo();
          return returnTypeInfo.isSubType(Orm.class);
        })
        .map(PropertyOrmInject::of)
        .findFirst()
        .orElse(StandardOrmInject.INSTANCE);
  }

  abstract Contribution get();

}