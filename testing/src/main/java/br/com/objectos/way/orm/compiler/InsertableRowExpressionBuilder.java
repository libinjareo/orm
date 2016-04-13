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

import java.util.ArrayList;
import java.util.List;

import com.squareup.javapoet.CodeBlock;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class InsertableRowExpressionBuilder {

  private final List<CodeBlock> expressionPartList = new ArrayList<>();
  private final List<String> generatedKeyListenerNameList = new ArrayList<>();

  private InsertableRowExpressionBuilder() {
  }

  public static InsertableRowExpressionBuilder get() {
    return new InsertableRowExpressionBuilder();
  }

  public InsertableRowExpression build() {
    return new InsertableRowExpression(expressionPartList, generatedKeyListenerNameList);
  }

  public InsertableRowExpressionBuilder expressionPartList(String format, Object... args) {
    expressionPartList.add(CodeBlock.builder()
        .add(format, args)
        .build());
    return this;
  }

  public InsertableRowExpressionBuilder generatedKeyListenerNameList(String name) {
    generatedKeyListenerNameList.add(name);
    return this;
  }

}