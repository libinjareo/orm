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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.objectos.way.pojo.plugin.PojoInfo;
import br.com.objectos.way.schema.info.TableInfoAnnotationInfo;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class StandardTableInfoMap extends TableInfoMap {

  private final Map<TableInfoAnnotationInfo, List<OrmProperty>> map;

  public StandardTableInfoMap(Map<TableInfoAnnotationInfo, List<OrmProperty>> map) {
    this.map = map;
  }

  @Override
  public <T> T onFirstEntry(TableInfoMapAction<T> action) {
    Entry<TableInfoAnnotationInfo, List<OrmProperty>> entry = firstEntry();
    return action.onEntry(entry.getKey(), entry.getValue());
  }

  @Override
  public QuerySelectExpression selectExpression() {
    return StandardQuerySelectExpression.INSTANCE;
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public OrmInsertable toOrmInsertable(PojoInfo pojoInfo) {
    return NotOrmInsertable.INSTANCE;
  }

  @Override
  List<OrmProperty> testableValues() {
    return map.values().stream()
        .flatMap(list -> list.stream())
        .collect(Collectors.toList());
  }

  private Entry<TableInfoAnnotationInfo, List<OrmProperty>> firstEntry() {
    Set<Entry<TableInfoAnnotationInfo, List<OrmProperty>>> entrySet = map.entrySet();
    return entrySet.iterator().next();
  }

}