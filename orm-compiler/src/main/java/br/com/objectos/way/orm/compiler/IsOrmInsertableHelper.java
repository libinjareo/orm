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

import java.util.stream.Stream;

import br.com.objectos.core.collections.ImmutableList;
import br.com.objectos.way.schema.info.TableInfoAnnotationInfo;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class IsOrmInsertableHelper {

  private final ImmutableList.Builder<ClassName> columnClassNameList = ImmutableList.builder();
  private final ImmutableList.Builder<CodeBlock> expressionPartList = ImmutableList.builder();
  private final ImmutableList.Builder<String> generatedKeyListenerNameList = ImmutableList.builder();

  private IsOrmInsertableHelper() {
  }

  public static IsOrmInsertableHelper get() {
    return new IsOrmInsertableHelper();
  }

  public IsOrmInsertableHelper addColumnClassNameStream(Stream<ClassName> stream) {
    stream.forEach(columnClassNameList::add);
    return this;
  }

  public IsOrmInsertableHelper addExpressionPart(String template, Object... args) {
    CodeBlock part = CodeBlock.builder()
        .add(template, args)
        .build();
    expressionPartList.add(part);
    return this;
  }

  public IsOrmInsertableHelper addGeneratedKeyListenerName(String valueName) {
    generatedKeyListenerNameList.add(valueName);
    return this;
  }

  public IsOrmInsertable build(TableInfoAnnotationInfo tableInfo) {
    ClassName[] columnClassNameArray = columnClassNameList.build().toArray(new ClassName[] {});
    return IsOrmInsertable.builder()
        .tableInfo(tableInfo)
        .insertableRowTypeName(OrmNaming.insertableRowTypeName(columnClassNameArray))
        .insertableRowValuesTypeName(OrmNaming.insertableRowValuesTypeName(columnClassNameArray))
        .insertableRowExpression(insertableRowExpression())
        .build();
  }

  private InsertableRowExpression insertableRowExpression() {
    return new InsertableRowExpression(
        expressionPartList.build(),
        generatedKeyListenerNameList.build());
  }

}