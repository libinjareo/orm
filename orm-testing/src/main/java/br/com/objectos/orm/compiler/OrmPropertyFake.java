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

import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.schema.info.TableInfoAnnotationInfoFake;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class OrmPropertyFake {

  public static final ColumnOrmProperty Pair_id = ColumnOrmProperty.builder()
      .property(PropertyFake.Pair_id)
      .tableInfo(TableInfoAnnotationInfoFake.PAIR)
      .columnAnnotationClassList(simple(TypeInfoFake.PAIR_ID))
      .columnSeq(0)
      .columnAnnotationInfo(AnnotationInfoFake.PAIR_ID)
      .columnClassName(NamingFake.schemaIt("PAIR", "PAIR_ID"))
      .columnSimpleName("ID")
      .returnType(ReturnTypeFake.INT)
      .generationType(GenerationType.NONE)
      .build();
  public static final ColumnOrmProperty Pair_name = ColumnOrmProperty.builder()
      .property(PropertyFake.Pair_name)
      .tableInfo(TableInfoAnnotationInfoFake.PAIR)
      .columnAnnotationClassList(simple(TypeInfoFake.PAIR_NAME))
      .columnSeq(1)
      .columnAnnotationInfo(AnnotationInfoFake.PAIR_NAME)
      .columnClassName(NamingFake.schemaIt("PAIR", "PAIR_NAME"))
      .columnSimpleName("NAME")
      .returnType(ReturnTypeFake.STRING)
      .generationType(GenerationType.NONE)
      .build();

  public static final ForeignKeyOrmProperty Salary_employee = ForeignKeyOrmProperty.builder()
      .property(PropertyFake.Salary_employee)
      .tableInfo(TableInfoAnnotationInfoFake.SALARY)
      .columnAnnotationClassList(simple(TypeInfoFake.SALARY_EMP_NO))
      .columnSeq(0)
      .foreignKeyAnnotationInfo(AnnotationInfoFake.SALARY_EMP_NO_FK)
      .build();

  private OrmPropertyFake() {
  }

  private static SimpleTypeInfo simple(TypeInfo typeInfo) {
    return typeInfo.toSimpleTypeInfo();
  }

}