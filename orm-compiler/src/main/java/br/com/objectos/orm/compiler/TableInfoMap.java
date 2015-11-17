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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.collections.ImmutableList;
import br.com.objectos.collections.ImmutableMap;
import br.com.objectos.orm.Insertable;
import br.com.objectos.pojo.plugin.PojoInfo;
import br.com.objectos.schema.info.TableInfoAnnotationInfo;
import br.com.objectos.testable.Equality;
import br.com.objectos.testable.Testable;
import br.com.objectos.testable.Tester;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class TableInfoMap implements Testable {

  private final Map<TableInfoAnnotationInfo, List<OrmProperty>> map;

  private TableInfoMap(Map<TableInfoAnnotationInfo, List<OrmProperty>> map) {
    this.map = map;
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public Equality isEqualTo(Object that) {
    return Tester.of(TableInfoMap.class)
        // .add("keySet", o -> o.map.keySet())
        .add("values", o -> o.testableValues())
        .test(this, that);
  }

  public <T> T onFirstEntry(TableInfoMapAction<T> action) {
    Entry<TableInfoAnnotationInfo, List<OrmProperty>> entry = firstEntry();
    return action.onEntry(entry.getKey(), entry.getValue());
  }

  public int size() {
    return map.size();
  }

  public OrmInsertable toOrmInsertable(PojoInfo pojoInfo) {
    if (!pojoInfo.instanceOf(Insertable.class)) {
      return NotOrmInsertable.INSTANCE;
    }

    if (map.size() != 1) {
      return NotOrmInsertable.INSTANCE;
    }

    Entry<TableInfoAnnotationInfo, List<OrmProperty>> entry = firstEntry();
    return toOrmInsertable0(entry.getKey(), entry.getValue());
  }

  private Entry<TableInfoAnnotationInfo, List<OrmProperty>> firstEntry() {
    Set<Entry<TableInfoAnnotationInfo, List<OrmProperty>>> entrySet = map.entrySet();
    return entrySet.iterator().next();
  }

  private OrmInsertable toOrmInsertable0(TableInfoAnnotationInfo tableInfo, List<OrmProperty> propertyList) {
    List<SimpleTypeInfo> columnAnnotationClassList = propertyList.stream()
        .filter(property -> !property.isGenerated())
        .flatMap(m -> m.columnAnnotationClassList().stream())
        .collect(Collectors.toList());
    return tableInfo.containsAll(columnAnnotationClassList)
        ? IsOrmInsertable.of(tableInfo, propertyList)
        : NotOrmInsertable.INSTANCE;
  }

  private List<OrmProperty> testableValues() {
    return map.values().stream()
        .flatMap(list -> list.stream())
        .collect(Collectors.toList());
  }

  public static class Builder {

    private final Map<TableInfoAnnotationInfo, ImmutableList.Builder<OrmProperty>> propertyTableInfoMap = new LinkedHashMap<>();

    private Builder() {
    }

    public Builder add(OrmProperty property) {
      TableInfoAnnotationInfo tableInfo = property.tableInfo();
      propertyTableInfoMap
          .computeIfAbsent(tableInfo, (ti) -> ImmutableList.builder())
          .add(property);
      return this;
    }

    public TableInfoMap build() {
      ImmutableMap.Builder<TableInfoAnnotationInfo, List<OrmProperty>> map = ImmutableMap.builder();
      propertyTableInfoMap.forEach((k, v) -> map.put(k, v.build()));
      return new TableInfoMap(map.build());
    }

  }

}