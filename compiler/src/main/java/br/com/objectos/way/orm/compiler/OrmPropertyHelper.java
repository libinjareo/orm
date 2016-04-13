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

import br.com.objectos.core.util.ImmutableList;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class OrmPropertyHelper {

  private final ImmutableList.Builder<OrmProperty> propertyList = ImmutableList.builder();
  private final ImmutableList.Builder<ColumnOrmProperty> columnPropertyList = ImmutableList.builder();
  private final ImmutableList.Builder<ForeignKeyOrmProperty> foreignKeyPropertyList = ImmutableList.builder();
  private final TableInfoMap.Builder tableInfoMap = TableInfoMap.builder();

  private OrmPropertyHelper() {
  }

  public static OrmPropertyHelper get() {
    return new OrmPropertyHelper();
  }

  public void addColumnOrmProperty(ColumnOrmProperty property) {
    addOrmProperty(property);
    columnPropertyList.add(property);
  }

  public void addForeignKeyOrmProperty(ForeignKeyOrmProperty property) {
    addOrmProperty(property);
    foreignKeyPropertyList.add(property);
  }

  public List<ColumnOrmProperty> columnPropertyList() {
    return columnPropertyList.build();
  }

  public List<ForeignKeyOrmProperty> foreignKeyPropertyList() {
    return foreignKeyPropertyList.build();
  }

  public List<OrmProperty> propertyList() {
    return propertyList.build();
  }

  public TableInfoMap tableInfoMap() {
    return tableInfoMap.build();
  }

  private void addOrmProperty(OrmProperty property) {
    propertyList.add(property);
    tableInfoMap.add(property);
  }

}