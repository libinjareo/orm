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

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class TableClassInfoFake {

  public static final TableClassInfo PAIR = TableClassInfo.builder()
      .className(NamingFake.schemaIt("PAIR"))
      .columnAnnotationClassList(
          simple(TypeInfoFake.PAIR_ID),
          simple(TypeInfoFake.PAIR_NAME))
      .build();
  public static final TableClassInfo REVISION = TableClassInfo.builder()
      .className(NamingFake.schemaIt("REVISION"))
      .columnAnnotationClassList(
          simple(TypeInfoFake.REVISION_SEQ),
          simple(TypeInfoFake.REVISION_DATE),
          simple(TypeInfoFake.REVISION_DESCRIPTION))
      .build();
  public static final TableClassInfo SALARY = TableClassInfo.builder()
      .className(NamingFake.schemaIt("SALARY"))
      .columnAnnotationClassList(
          simple(TypeInfoFake.SALARY_EMP_NO),
          simple(TypeInfoFake.SALARY_SALARY_),
          simple(TypeInfoFake.SALARY_FROM_DATE),
          simple(TypeInfoFake.SALARY_TO_DATE))
      .build();

  private TableClassInfoFake() {
  }

  private static SimpleTypeInfo simple(TypeInfo typeInfo) {
    return typeInfo.toSimpleTypeInfo();
  }

}