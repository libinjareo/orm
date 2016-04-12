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

import br.com.objectos.way.code.SimpleTypeInfo;
import br.com.objectos.way.code.TypeInfo;
import br.com.objectos.way.orm.compiler.BindType;
import br.com.objectos.way.orm.compiler.ColumnOrmProperty;
import br.com.objectos.way.orm.compiler.ForeignKeyOrmProperty;
import br.com.objectos.way.orm.compiler.GenerationType;
import br.com.objectos.way.orm.compiler.OrmProperty;
import br.com.objectos.way.schema.info.TableInfoAnnotationInfoFake;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class OrmPropertyFake {

  public static final ColumnOrmProperty Employee_empNo = ColumnOrmProperty.builder()
      .property(PropertyFake.Employee_empNo)
      .returnType(ReturnTypeFake.INT)
      .tableInfo(TableInfoAnnotationInfoFake.EMPLOYEE)
      .columnAnnotationClassList(simple(TypeInfoFake.EMPLOYEE_EMP_NO))
      .columnSeq(0)
      .columnAnnotationInfo(AnnotationInfoFake.EMPLOYEE_EMP_NO)
      .columnClassName(NamingFake.schemaIt("EMPLOYEE", "EMPLOYEE_EMP_NO"))
      .columnSimpleName("EMP_NO")
      .bindType(BindType.STANDARD)
      .generationType(GenerationType.NONE)
      .build();

  public static final ColumnOrmProperty Pair_id = ColumnOrmProperty.builder()
      .property(PropertyFake.Pair_id)
      .returnType(ReturnTypeFake.INT)
      .tableInfo(TableInfoAnnotationInfoFake.PAIR)
      .columnAnnotationClassList(simple(TypeInfoFake.PAIR_ID))
      .columnSeq(0)
      .columnAnnotationInfo(AnnotationInfoFake.PAIR_ID)
      .columnClassName(NamingFake.schemaIt("PAIR", "PAIR_ID"))
      .columnSimpleName("ID")
      .bindType(BindType.STANDARD)
      .generationType(GenerationType.NONE)
      .build();
  public static final ColumnOrmProperty Pair_name = ColumnOrmProperty.builder()
      .property(PropertyFake.Pair_name)
      .returnType(ReturnTypeFake.STRING)
      .tableInfo(TableInfoAnnotationInfoFake.PAIR)
      .columnAnnotationClassList(simple(TypeInfoFake.PAIR_NAME))
      .columnSeq(1)
      .columnAnnotationInfo(AnnotationInfoFake.PAIR_NAME)
      .columnClassName(NamingFake.schemaIt("PAIR", "PAIR_NAME"))
      .columnSimpleName("NAME")
      .bindType(BindType.STANDARD)
      .generationType(GenerationType.NONE)
      .build();

  public static final ForeignKeyOrmProperty Salary_employee = ForeignKeyOrmProperty.builder()
      .property(PropertyFake.Salary_employee)
      .returnType(ReturnTypeFake.EMPLOYEE)
      .tableInfo(TableInfoAnnotationInfoFake.SALARY)
      .columnAnnotationClassList(simple(TypeInfoFake.SALARY_EMP_NO))
      .columnSeq(0)
      .foreignKeyAnnotationInfo(AnnotationInfoFake.SALARY_EMP_NO_FK)
      .build();
  public static final OrmProperty Salary_salary_ = ColumnOrmProperty.builder()
      .property(PropertyFake.Salary_salary)
      .returnType(ReturnTypeFake.INT)
      .tableInfo(TableInfoAnnotationInfoFake.SALARY)
      .columnAnnotationClassList(simple(TypeInfoFake.SALARY_SALARY_))
      .columnSeq(1)
      .columnAnnotationInfo(AnnotationInfoFake.SALARY_SALARY_)
      .columnClassName(NamingFake.schemaIt("SALARY", "SALARY_SALARY"))
      .columnSimpleName("SALARY")
      .bindType(BindType.STANDARD)
      .generationType(GenerationType.NONE)
      .build();
  public static final ColumnOrmProperty Salary_fromDate = ColumnOrmProperty.builder()
      .property(PropertyFake.Salary_fromDate)
      .returnType(ReturnTypeFake.LOCAL_DATE)
      .tableInfo(TableInfoAnnotationInfoFake.SALARY)
      .columnAnnotationClassList(simple(TypeInfoFake.SALARY_FROM_DATE))
      .columnSeq(2)
      .columnAnnotationInfo(AnnotationInfoFake.SALARY_FROM_DATE)
      .columnClassName(NamingFake.schemaIt("SALARY", "SALARY_FROM_DATE"))
      .columnSimpleName("FROM_DATE")
      .bindType(BindType.STANDARD)
      .generationType(GenerationType.NONE)
      .build();
  public static final OrmProperty Salary_toDate = ColumnOrmProperty.builder()
      .property(PropertyFake.Salary_toDate)
      .returnType(ReturnTypeFake.LOCAL_DATE)
      .tableInfo(TableInfoAnnotationInfoFake.SALARY)
      .columnAnnotationClassList(simple(TypeInfoFake.SALARY_TO_DATE))
      .columnSeq(2)
      .columnAnnotationInfo(AnnotationInfoFake.SALARY_TO_DATE)
      .columnClassName(NamingFake.schemaIt("SALARY", "SALARY_TO_DATE"))
      .columnSimpleName("TO_DATE")
      .bindType(BindType.STANDARD)
      .generationType(GenerationType.NONE)
      .build();

  private OrmPropertyFake() {
  }

  private static SimpleTypeInfo simple(TypeInfo typeInfo) {
    return typeInfo.toSimpleTypeInfo();
  }

}