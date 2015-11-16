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

import br.com.objectos.collections.ImmutableList;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class OrmPojoInfoHelper {

  private final ImmutableList.Builder<OrmProperty> propertyList = ImmutableList.builder();
  private final ImmutableList.Builder<ColumnOrmProperty> columnPropertyList = ImmutableList.builder();
  private final ImmutableList.Builder<ForeignKeyOrmProperty> foreignKeyPropertyList = ImmutableList.builder();

  private OrmPojoInfoHelper() {
  }

  public static OrmPojoInfoHelper get() {
    return new OrmPojoInfoHelper();
  }

  public void addColumnOrmProperty(ColumnOrmProperty property) {
    propertyList.add(property);
    columnPropertyList.add(property);
  }

  public void addForeignKeyOrmProperty(ForeignKeyOrmProperty property) {
    propertyList.add(property);
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

}