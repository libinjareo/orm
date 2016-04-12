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

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.objectos.way.testable.Equality;
import br.com.objectos.way.testable.Testable;
import br.com.objectos.way.testable.Tester;

import com.squareup.javapoet.CodeBlock;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class InsertableRowExpression implements QueryExpression, Testable {

  private final List<CodeBlock> expressionPartList;
  private final List<String> generatedKeyListenerNameList;

  public InsertableRowExpression(List<CodeBlock> expressionPartList, List<String> generatedKeyListenerNameList) {
    this.expressionPartList = expressionPartList;
    this.generatedKeyListenerNameList = generatedKeyListenerNameList;
  }

  @Override
  public CodeBlock get() {
    CodeBlock.Builder body = CodeBlock.builder()
        .add("return row.values(");

    Iterator<CodeBlock> expressionPartIterator = expressionPartList.iterator();
    if (expressionPartIterator.hasNext()) {
      body.add(expressionPartIterator.next());
      while (expressionPartIterator.hasNext()) {
        body.add(", ");
        body.add(expressionPartIterator.next());
      }
    }

    body.add(")");

    String generated = generatedKeyListenerNameList.stream().collect(Collectors.joining(", "));
    if (!generated.isEmpty()) {
      body.add(".onGeneratedKey(" + generated + ")");
    }

    return body
        .add(";\n")
        .build();
  }

  @Override
  public Equality isEqualTo(Object that) {
    return Tester.of(InsertableRowExpression.class)
        .add("expression", o -> o.get().toString())
        .test(this, that);
  }

}