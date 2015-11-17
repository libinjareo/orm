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
package br.com.objectos.orm.compiler;

import br.com.objectos.pojo.plugin.Contribution;
import br.com.objectos.pojo.plugin.PojoInfo;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
interface RelationalInsertable {

  static RelationalInsertable of(PojoInfo pojoInfo) {
    return OrmPojoInfo.of(pojoInfo)
        .map(RelationalInsertable::of0)
        .orElse(NotRelationalInsertable.INSTANCE);
  }

  static RelationalInsertable of0(OrmPojoInfo pojoInfo) {
    TableInfoMap tableInfoMap = pojoInfo.tableInfoMap();

    if (tableInfoMap.size() != 1) {
      return NotRelationalInsertable.INSTANCE;
    }

    return tableInfoMap.onFirstEntry(IsRelationInsertableAction.INSTANCE);
  }

  Contribution execute();

}