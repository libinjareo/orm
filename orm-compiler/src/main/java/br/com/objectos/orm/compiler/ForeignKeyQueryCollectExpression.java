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

import br.com.objectos.pojo.plugin.Naming;

import com.squareup.javapoet.CodeBlock;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class ForeignKeyQueryCollectExpression extends QueryCollectExpression {

  private final OrmPojoInfo ownerPojoInfo;

  public ForeignKeyQueryCollectExpression(OrmPojoInfo pojoInfo, QueryReturnType returnType, OrmPojoInfo ownerPojoInfo) {
    super(pojoInfo, returnType);
    this.ownerPojoInfo = ownerPojoInfo;
  }

  @Override
  CodeBlock collectCode(Naming naming, OrmInject inject) {
    return CodeBlock.builder()
        .add("row -> $T.get($L).load($T.this, row)",
            naming.superClassSuffix("Orm"),
            inject.name(),
            ownerPojoInfo.naming().pojo())
        .build();
  }

}