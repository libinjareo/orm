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

import java.util.Iterator;
import java.util.List;

import br.com.objectos.db.core.SqlRuntimeException;
import br.com.objectos.db.core.Transaction;
import br.com.objectos.pojo.plugin.Naming;

import com.squareup.javapoet.CodeBlock;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class QueryMethodBody {

  final OrmPojoInfo pojoInfo;
  final QueryReturnType returnType;

  public QueryMethodBody(OrmPojoInfo pojoInfo, QueryReturnType returnType) {
    this.pojoInfo = pojoInfo;
    this.returnType = returnType;
  }

  public CodeBlock get() {
    OrmInject inject = pojoInfo.inject();
    return CodeBlock.builder()
        .beginControlFlow("try ($T trx = $L.startTransaction())", Transaction.class, inject.name())
        .add(selectFrom())
        .add(where())
        .add(orderBy())
        .add(returnType.collect(collectCode()))
        .nextControlFlow("catch ($T e)", Exception.class)
        .addStatement("throw new $T(e)", SqlRuntimeException.class)
        .endControlFlow()
        .build();
  }

  CodeBlock collectCode() {
    Naming naming = pojoInfo.naming();
    OrmInject inject = pojoInfo.inject();
    return CodeBlock.builder()
        .add("$T.get($L)::load", naming.superClassSuffix("Orm"), inject.name())
        .build();
  }

  CodeBlock orderBy() {
    return CodeBlocks.empty();
  }

  CodeBlock selectFrom() {
    return pojoInfo.tableInfoMap().selectFrom();
  }

  CodeBlock where() {
    return CodeBlocks.empty();
  }

  CodeBlock where0(List<? extends OrmProperty> propertyList) {
    CodeBlock.Builder expression = CodeBlock.builder();
    Iterator<? extends OrmProperty> iterator = propertyList.iterator();
    if (iterator.hasNext()) {
      OrmProperty property = iterator.next();
      where1(expression, property, "where");
      while (iterator.hasNext()) {
        property = iterator.next();
        where1(expression, property, "and");
      }
    }
    return expression.build();
  }

  private void where1(CodeBlock.Builder expression, OrmProperty property, String keyword) {
    expression.add("    .$L(", keyword);
    property.where(expression);
    expression.add(")\n");
  }

}