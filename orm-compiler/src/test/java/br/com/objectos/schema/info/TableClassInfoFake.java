/*
 * Copyright 2015 Objectos, Fábrica de Software LTDA.
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

import static br.com.objectos.schema.info.PackageInfoFake.BR_COM_OBJECTOS_SCHEMA_IT;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class TableClassInfoFake {

  public static final TableClassInfo PAIR = TableClassInfo.builder()
      .tableName(TableNameFake.PAIR_V001)
      .tableClassName(BR_COM_OBJECTOS_SCHEMA_IT.className("PAIR"))
      .columnAnnotationClassList(SimpleTypeInfoFake.PAIR_ID, SimpleTypeInfoFake.PAIR_NAME)
      .primaryKeyList()
      .build();

  private TableClassInfoFake() {
  }

}