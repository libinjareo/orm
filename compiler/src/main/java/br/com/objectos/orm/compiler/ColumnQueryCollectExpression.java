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
class ColumnQueryCollectExpression implements QueryCollectExpression {

  private final OrmPojoInfo pojoInfo;
  private final QueryReturnType returnType;

  public ColumnQueryCollectExpression(OrmPojoInfo pojoInfo, QueryReturnType returnType) {
    this.pojoInfo = pojoInfo;
    this.returnType = returnType;
  }

  @Override
  public CodeBlock get() {
    Naming naming = pojoInfo.naming();
    OrmInject inject = pojoInfo.inject();
    return returnType.collect(CodeBlock.builder()
        .add("$T.get($L)::load", naming.superClassSuffix("Orm"), inject.name())
        .build());
  }

}