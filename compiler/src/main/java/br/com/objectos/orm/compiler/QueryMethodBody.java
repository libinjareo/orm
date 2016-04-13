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

import br.com.objectos.db.SqlRuntimeException;
import br.com.objectos.db.Transaction;

import com.squareup.javapoet.CodeBlock;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class QueryMethodBody {

  private final OrmPojoInfo pojoInfo;
  private final QuerySelectExpression selectExpression;
  private final QueryWhereExpression whereExpression;
  private final QueryOrderByExpression orderByExpression;
  private final QueryCollectExpression collectExpression;

  private QueryMethodBody(OrmPojoInfo pojoInfo,
                          QuerySelectExpression selectExpression,
                          QueryWhereExpression whereExpression,
                          QueryOrderByExpression orderByExpression,
                          QueryCollectExpression collectExpression) {
    this.pojoInfo = pojoInfo;
    this.selectExpression = selectExpression;
    this.whereExpression = whereExpression;
    this.orderByExpression = orderByExpression;
    this.collectExpression = collectExpression;
  }

  public static Builder builder(OrmPojoInfo pojoInfo, QueryReturnType returnType) {
    return new Builder(pojoInfo, returnType);
  }

  public CodeBlock get() {
    OrmInject inject = pojoInfo.inject();
    return CodeBlock.builder()
        .beginControlFlow("try ($T trx = $L.startTransaction())", Transaction.class, inject.name())
        .add(selectExpression.get())
        .add(whereExpression.get())
        .add(orderByExpression.get())
        .add(collectExpression.get())
        .nextControlFlow("catch ($T e)", Exception.class)
        .addStatement("throw new $T(e)", SqlRuntimeException.class)
        .endControlFlow()
        .build();
  }

  public static class Builder {

    private final OrmPojoInfo pojoInfo;

    private QuerySelectExpression selectExpression;
    private QueryWhereExpression whereExpression = EmptyQueryWhereExpression.INSTANCE;
    private QueryOrderByExpression orderByExpression = EmptyQueryOrderByExpression.INSTANCE;
    private QueryCollectExpression collectExpression;

    private Builder(OrmPojoInfo pojoInfo, QueryReturnType returnType) {
      this.pojoInfo = pojoInfo;
      selectExpression = pojoInfo.tableInfoMap().selectExpression();
      collectExpression = new ColumnQueryCollectExpression(pojoInfo, returnType);
    }

    public CodeBlock build() {
      QueryMethodBody body = new QueryMethodBody(
          pojoInfo,
          selectExpression,
          whereExpression,
          orderByExpression,
          collectExpression);
      return body.get();
    }

    public Builder selectExpression(QuerySelectExpression selectExpression) {
      this.selectExpression = selectExpression;
      return this;
    }

    public Builder orderByExpression(QueryOrderByExpression orderByExpression) {
      this.orderByExpression = orderByExpression;
      return this;
    }

    public Builder whereExpression(QueryWhereExpression whereExpression) {
      this.whereExpression = whereExpression;
      return this;
    }

    public Builder collectExpression(QueryCollectExpression collectExpression) {
      this.collectExpression = collectExpression;
      return this;
    }

  }

}