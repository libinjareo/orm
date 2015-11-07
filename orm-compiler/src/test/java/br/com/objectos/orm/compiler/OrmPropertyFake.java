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

  public static final OrmProperty Pair_id = ColumnOrmProperty.builder()
      .property(PropertyFake.Pair_id)
      .tableClassInfo(TableInfoAnnotationInfoFake.PAIR)
      .columnAnnotationClassList(simple(TypeInfoFake.PAIR_ID))
      .columnAnnotationInfo(AnnotationInfoFake.PAIR_ID)
      .generationType(GenerationType.NONE)
      .build();
  public static final OrmProperty Pair_name = ColumnOrmProperty.builder()
      .property(PropertyFake.Pair_name)
      .tableClassInfo(TableInfoAnnotationInfoFake.PAIR)
      .columnAnnotationClassList(simple(TypeInfoFake.PAIR_NAME))
      .columnAnnotationInfo(AnnotationInfoFake.PAIR_NAME)
      .generationType(GenerationType.NONE)
      .build();

  public static final OrmProperty Salary_employee = ForeignKeyOrmProperty.builder()
      .property(PropertyFake.Salary_employee)
      .tableClassInfo(TableInfoAnnotationInfoFake.SALARY)
      .columnAnnotationClassList(simple(TypeInfoFake.SALARY_EMP_NO))
      .foreignKeyAnnotationInfo(AnnotationInfoFake.SALARY_EMP_NO_FK)
      .build();

  private OrmPropertyFake() {
  }

  private static SimpleTypeInfo simple(TypeInfo typeInfo) {
    return typeInfo.toSimpleTypeInfo();
  }

}