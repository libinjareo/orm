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

import br.com.objectos.way.code.TypeInfo;
import br.com.objectos.way.orm.compiler.IsOrmInsertable;
import br.com.objectos.way.orm.compiler.OrmInsertable;
import br.com.objectos.way.orm.compiler.OrmNaming;
import br.com.objectos.way.schema.info.TableInfoAnnotationInfoFake;

import com.squareup.javapoet.ClassName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class OrmInsertableFake {

  public static final OrmInsertable Pair = IsOrmInsertable.builder()
      .tableInfo(TableInfoAnnotationInfoFake.PAIR)
      .insertableRowTypeName(
          OrmNaming.insertableRowTypeName(
              cn(TypeInfoFake.PAIR_PAIR_ID),
              cn(TypeInfoFake.PAIR_PAIR_NAME)))
      .insertableRowValuesTypeName(
          OrmNaming.insertableRowValuesTypeName(
              cn(TypeInfoFake.PAIR_PAIR_ID),
              cn(TypeInfoFake.PAIR_PAIR_NAME)))
      .insertableRowExpression(InsertableRowExpressionBuilder.get()
          .expressionPartList("id")
          .expressionPartList("name")
          .build())
      .build();
  public static final OrmInsertable Revision = IsOrmInsertable.builder()
      .tableInfo(TableInfoAnnotationInfoFake.REVISION)
      .insertableRowTypeName(
          OrmNaming.insertableRowTypeName(
              cn(TypeInfoFake.REVISION_REVISION_SEQ),
              cn(TypeInfoFake.REVISION_REVISION_DATE),
              cn(TypeInfoFake.REVISION_REVISION_DESCRIPTION)))
      .insertableRowValuesTypeName(
          OrmNaming.insertableRowValuesTypeName(
              cn(TypeInfoFake.REVISION_REVISION_SEQ),
              cn(TypeInfoFake.REVISION_REVISION_DATE),
              cn(TypeInfoFake.REVISION_REVISION_DESCRIPTION)))
      .insertableRowExpression(InsertableRowExpressionBuilder.get()
          .expressionPartList("seq")
          .expressionPartList("date")
          .expressionPartList("description")
          .generatedKeyListenerNameList("seq")
          .build())
      .build();

  private OrmInsertableFake() {
  }

  private static ClassName cn(TypeInfo typeInfo) {
    return typeInfo.className();
  }

}