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

import br.com.objectos.collections.ImmutableList;
import br.com.objectos.collections.ImmutableMap;
import br.com.objectos.pojo.plugin.PojoInfo;
import br.com.objectos.schema.info.TableInfoAnnotationInfo;
import br.com.objectos.testable.Equality;
import br.com.objectos.testable.Testable;
import br.com.objectos.testable.Tester;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
abstract class TableInfoMap implements Testable {

  TableInfoMap() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public boolean containsPrimaryKey() {
    return false;
  }

  @Override
  public Equality isEqualTo(Object that) {
    return Tester.of(TableInfoMap.class)
        .add("values", o -> o.testableValues())
        .test(this, that);
  }

  public abstract <T> T onFirstEntry(TableInfoMapAction<T> action);

  public List<OrmProperty> primaryKeyPropertyList() {
    return ImmutableList.of();
  }

  public abstract QuerySelectExpression selectFrom();

  public abstract int size();

  public abstract OrmInsertable toOrmInsertable(PojoInfo pojoInfo);

  @Override
  public String toString() {
    return testableValues().toString();
  }

  abstract List<OrmProperty> testableValues();

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
      switch (propertyTableInfoMap.size()) {
      case 1:
        Entry<TableInfoAnnotationInfo, ImmutableList.Builder<OrmProperty>> entry = propertyTableInfoMap
            .entrySet()
            .iterator()
            .next();
        TableInfoAnnotationInfo tableInfo = entry.getKey();
        List<OrmProperty> propertyList = entry.getValue().build();
        return SingletonTableInfoMap.of(tableInfo, propertyList);
      default:
        ImmutableMap.Builder<TableInfoAnnotationInfo, List<OrmProperty>> map = ImmutableMap.builder();
        propertyTableInfoMap.forEach((k, v) -> map.put(k, v.build()));
        return new StandardTableInfoMap(map.build());
      }
    }

  }

}