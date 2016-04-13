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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.core.util.MoreCollectors;
import br.com.objectos.orm.Insertable;
import br.com.objectos.pojo.plugin.PojoInfo;
import br.com.objectos.schema.info.TableInfoAnnotationInfo;

import com.squareup.javapoet.ClassName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class SingletonTableInfoMap extends TableInfoMap {

  private final TableInfoAnnotationInfo tableInfo;
  private final List<OrmProperty> propertyList;
  private final List<OrmProperty> primaryKeyPropertyList;

  private SingletonTableInfoMap(TableInfoAnnotationInfo tableInfo,
                                List<OrmProperty> propertyList,
                                List<OrmProperty> primaryKeyPropertyList) {
    this.tableInfo = tableInfo;
    this.propertyList = propertyList;
    this.primaryKeyPropertyList = primaryKeyPropertyList;
  }

  public static TableInfoMap of(TableInfoAnnotationInfo tableInfo, List<OrmProperty> propertyList) {
    Set<ClassName> pkNameSet = tableInfo.primaryKeyClassNameSet();
    return new SingletonTableInfoMap(
        tableInfo,
        propertyList,
        propertyList.stream()
            .filter(col -> col.matchesAny(pkNameSet))
            .collect(MoreCollectors.toImmutableList()));
  }

  @Override
  public boolean containsPrimaryKey() {
    Set<ClassName> pkNameSet = tableInfo.primaryKeyClassNameSet();
    return !pkNameSet.isEmpty() && pkNameSet.size() == primaryKeyPropertyList.size();
  }

  @Override
  public <T> T onFirstEntry(TableInfoMapAction<T> action) {
    return action.onEntry(tableInfo, propertyList);
  }

  @Override
  public List<OrmProperty> primaryKeyPropertyList() {
    return primaryKeyPropertyList;
  }

  @Override
  public QuerySelectExpression selectExpression() {
    return new SingletonQuerySelectExpression(
        tableInfo.className(),
        propertyList);
  }

  @Override
  public int size() {
    return 1;
  }

  @Override
  public OrmInsertable toOrmInsertable(PojoInfo pojoInfo) {
    if (!pojoInfo.instanceOf(Insertable.class)) {
      return NotOrmInsertable.INSTANCE;
    }

    List<SimpleTypeInfo> columnAnnotationClassList = propertyList.stream()
        .filter(property -> !property.isGenerated())
        .flatMap(m -> m.columnAnnotationClassList().stream())
        .collect(Collectors.toList());
    return tableInfo.containsAll(columnAnnotationClassList)
        ? IsOrmInsertable.of(tableInfo, propertyList)
        : NotOrmInsertable.INSTANCE;
  }

  @Override
  List<OrmProperty> testableValues() {
    return propertyList;
  }

}