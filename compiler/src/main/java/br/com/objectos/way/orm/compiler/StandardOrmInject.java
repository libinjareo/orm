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

import br.com.objectos.pojo.plugin.Contribution;
import br.com.objectos.testable.Equality;
import br.com.objectos.way.orm.Orm;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class StandardOrmInject extends OrmInject {

  public static final OrmInject INSTANCE = new StandardOrmInject();

  private static final ClassName className = ClassName.get(Orm.class);

  private StandardOrmInject() {
  }

  @Override
  public Contribution execute() {
    return Contribution.builder()
        .addCustomField(typeName(), name())
        .build();
  }

  @Override
  public Equality isEqualTo(Object that) {
    return Equality.instanceOf(that, StandardOrmInject.class);
  }

  @Override
  String name() {
    return "orm";
  }

  @Override
  TypeName typeName() {
    return className;
  }

}