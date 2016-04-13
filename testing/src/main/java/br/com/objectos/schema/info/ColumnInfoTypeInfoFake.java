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

import br.com.objectos.orm.compiler.TypeInfoFake;
import br.com.objectos.schema.info.ColumnInfoTypeInfo;
import br.com.objectos.schema.info.OrmAutoIncrementGenerationInfo;
import br.com.objectos.schema.info.OrmNoGenerationInfo;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class ColumnInfoTypeInfoFake {

  public static final ColumnInfoTypeInfo EMPLOYEE_EMP_NO = ColumnInfoTypeInfo.builder()
      .tableName(TableNameAnnotationInfoFake.EMPLOYEE)
      .simpleName("EMP_NO")
      .nullable(false)
      .generationInfo(OrmNoGenerationInfo.get())
      .simpleTypeInfo(TypeInfoFake.EMPLOYEE_EMP_NO.toSimpleTypeInfo())
      .build();
  public static final ColumnInfoTypeInfo EMPLOYEE_BIRTH_DATE = ColumnInfoTypeInfo.builder()
      .tableName(TableNameAnnotationInfoFake.EMPLOYEE)
      .simpleName("BIRTH_DATE")
      .nullable(false)
      .generationInfo(OrmNoGenerationInfo.get())
      .simpleTypeInfo(TypeInfoFake.EMPLOYEE_BIRTH_DATE.toSimpleTypeInfo())
      .build();
  public static final ColumnInfoTypeInfo EMPLOYEE_FIRST_NAME = ColumnInfoTypeInfo.builder()
      .tableName(TableNameAnnotationInfoFake.EMPLOYEE)
      .simpleName("FIRST_NAME")
      .nullable(false)
      .generationInfo(OrmNoGenerationInfo.get())
      .simpleTypeInfo(TypeInfoFake.EMPLOYEE_FIRST_NAME.toSimpleTypeInfo())
      .build();
  public static final ColumnInfoTypeInfo EMPLOYEE_LAST_NAME = ColumnInfoTypeInfo.builder()
      .tableName(TableNameAnnotationInfoFake.EMPLOYEE)
      .simpleName("LAST_NAME")
      .nullable(false)
      .generationInfo(OrmNoGenerationInfo.get())
      .simpleTypeInfo(TypeInfoFake.EMPLOYEE_LAST_NAME.toSimpleTypeInfo())
      .build();
  public static final ColumnInfoTypeInfo EMPLOYEE_HIRE_DATE = ColumnInfoTypeInfo.builder()
      .tableName(TableNameAnnotationInfoFake.EMPLOYEE)
      .simpleName("HIRE_DATE")
      .nullable(false)
      .generationInfo(OrmNoGenerationInfo.get())
      .simpleTypeInfo(TypeInfoFake.EMPLOYEE_HIRE_DATE.toSimpleTypeInfo())
      .build();

  public static final ColumnInfoTypeInfo PAIR_ID = ColumnInfoTypeInfo.builder()
      .tableName(TableNameAnnotationInfoFake.PAIR)
      .simpleName("ID")
      .nullable(false)
      .generationInfo(OrmNoGenerationInfo.get())
      .simpleTypeInfo(TypeInfoFake.PAIR_ID.toSimpleTypeInfo())
      .build();
  public static final ColumnInfoTypeInfo PAIR_NAME = ColumnInfoTypeInfo.builder()
      .tableName(TableNameAnnotationInfoFake.PAIR)
      .simpleName("NAME")
      .nullable(false)
      .generationInfo(OrmNoGenerationInfo.get())
      .simpleTypeInfo(TypeInfoFake.PAIR_NAME.toSimpleTypeInfo())
      .build();

  public static final ColumnInfoTypeInfo REVISION_SEQ = ColumnInfoTypeInfo.builder()
      .tableName(TableNameAnnotationInfoFake.REVISION)
      .simpleName("SEQ")
      .nullable(false)
      .generationInfo(OrmAutoIncrementGenerationInfo.get())
      .simpleTypeInfo(TypeInfoFake.REVISION_SEQ.toSimpleTypeInfo())
      .build();
  public static final ColumnInfoTypeInfo REVISION_DATE = ColumnInfoTypeInfo.builder()
      .tableName(TableNameAnnotationInfoFake.REVISION)
      .simpleName("DATE")
      .nullable(false)
      .generationInfo(OrmNoGenerationInfo.get())
      .simpleTypeInfo(TypeInfoFake.REVISION_DATE.toSimpleTypeInfo())
      .build();
  public static final ColumnInfoTypeInfo REVISION_DESCRIPTION = ColumnInfoTypeInfo.builder()
      .tableName(TableNameAnnotationInfoFake.REVISION)
      .simpleName("DESCRIPTION")
      .nullable(false)
      .generationInfo(OrmNoGenerationInfo.get())
      .simpleTypeInfo(TypeInfoFake.REVISION_DESCRIPTION.toSimpleTypeInfo())
      .build();

  public static final ColumnInfoTypeInfo SALARY_EMP_NO = ColumnInfoTypeInfo.builder()
      .tableName(TableNameAnnotationInfoFake.SALARY)
      .simpleName("EMP_NO")
      .nullable(false)
      .generationInfo(OrmNoGenerationInfo.get())
      .simpleTypeInfo(TypeInfoFake.SALARY_EMP_NO.toSimpleTypeInfo())
      .build();
  public static final ColumnInfoTypeInfo SALARY_SALARY_ = ColumnInfoTypeInfo.builder()
      .tableName(TableNameAnnotationInfoFake.SALARY)
      .simpleName("SALARY")
      .nullable(false)
      .generationInfo(OrmNoGenerationInfo.get())
      .simpleTypeInfo(TypeInfoFake.SALARY_SALARY_.toSimpleTypeInfo())
      .build();
  public static final ColumnInfoTypeInfo SALARY_FROM_DATE = ColumnInfoTypeInfo.builder()
      .tableName(TableNameAnnotationInfoFake.SALARY)
      .simpleName("FROM_DATE")
      .nullable(false)
      .generationInfo(OrmNoGenerationInfo.get())
      .simpleTypeInfo(TypeInfoFake.SALARY_FROM_DATE.toSimpleTypeInfo())
      .build();
  public static final ColumnInfoTypeInfo SALARY_TO_DATE = ColumnInfoTypeInfo.builder()
      .tableName(TableNameAnnotationInfoFake.SALARY)
      .simpleName("TO_DATE")
      .nullable(false)
      .generationInfo(OrmNoGenerationInfo.get())
      .simpleTypeInfo(TypeInfoFake.SALARY_TO_DATE.toSimpleTypeInfo())
      .build();

  private ColumnInfoTypeInfoFake() {
  }

}