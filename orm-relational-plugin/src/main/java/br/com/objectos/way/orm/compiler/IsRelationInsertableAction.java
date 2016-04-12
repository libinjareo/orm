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

import java.util.List;

import br.com.objectos.way.orm.compiler.OrmProperty;
import br.com.objectos.way.orm.compiler.TableInfoMapAction;
import br.com.objectos.way.schema.info.TableInfoAnnotationInfo;
import br.com.objectos.way.schema.info.TableName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
enum IsRelationInsertableAction implements TableInfoMapAction<RelationalInsertable> {

  INSTANCE;

  @Override
  public RelationalInsertable onEntry(TableInfoAnnotationInfo tableInfo, List<OrmProperty> propertyList) {
    TableName tableName = tableInfo.tableName();
    return new IsRelationalInsertable(tableName, propertyList);
  }

}