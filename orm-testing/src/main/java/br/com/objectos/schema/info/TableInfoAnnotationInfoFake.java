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
package br.com.objectos.schema.info;

import br.com.objectos.orm.compiler.NamingFake;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class TableInfoAnnotationInfoFake {

  public static final TableInfoAnnotationInfo PAIR = TableInfoAnnotationInfo.thisBuilder()
      .className(NamingFake.schemaIt("PAIR"))
      .tableName(TableNameAnnotationInfoFake.PAIR)
      .columnInfoList(
          ColumnInfoTypeInfoFake.PAIR_ID,
          ColumnInfoTypeInfoFake.PAIR_NAME)
      .build();
  public static final TableInfoAnnotationInfo REVISION = TableInfoAnnotationInfo.thisBuilder()
      .className(NamingFake.schemaIt("REVISION"))
      .tableName(TableNameAnnotationInfoFake.REVISION)
      .columnInfoList(
          ColumnInfoTypeInfoFake.REVISION_SEQ,
          ColumnInfoTypeInfoFake.REVISION_DATE,
          ColumnInfoTypeInfoFake.REVISION_DESCRIPTION)
      .build();
  public static final TableInfoAnnotationInfo SALARY = TableInfoAnnotationInfo.thisBuilder()
      .className(NamingFake.schemaIt("SALARY"))
      .tableName(TableNameAnnotationInfoFake.SALARY)
      .columnInfoList(
          ColumnInfoTypeInfoFake.SALARY_EMP_NO,
          ColumnInfoTypeInfoFake.SALARY_SALARY_,
          ColumnInfoTypeInfoFake.SALARY_FROM_DATE,
          ColumnInfoTypeInfoFake.SALARY_TO_DATE)
      .build();

  private TableInfoAnnotationInfoFake() {
  }

}