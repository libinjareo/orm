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
import br.com.objectos.collections.MoreCollectors;
import br.com.objectos.orm.Insertable;
import br.com.objectos.pojo.plugin.PojoInfo;
import br.com.objectos.schema.info.TableInfoAnnotationInfo;
import br.com.objectos.sql.query.Sql;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class SingletonTableInfoMap extends TableInfoMap {

  private final TableInfoAnnotationInfo tableInfo;
  private final List<OrmProperty> propertyList;

  public SingletonTableInfoMap(TableInfoAnnotationInfo tableInfo, List<OrmProperty> propertyList) {
    this.tableInfo = tableInfo;
    this.propertyList = propertyList;
  }

  @Override
  public boolean containsPrimaryKey() {
    Set<ClassName> pkNameSet = tableInfo.primaryKeyClassNameSet();

    List<OrmProperty> pkColumnList = propertyList.stream()
        .filter(col -> col.matchesAny(pkNameSet))
        .collect(MoreCollectors.toImmutableList());

    return !pkNameSet.isEmpty() && pkNameSet.size() == pkColumnList.size();
  }

  @Override
  public <T> T onFirstEntry(TableInfoMapAction<T> action) {
    return action.onEntry(tableInfo, propertyList);
  }

  @Override
  public CodeBlock selectFrom() {
    ClassName tableClassName = tableInfo.className();
    String tableVarName = tableClassName.simpleName();
    return CodeBlock.builder()
        .addStatement("$T $L = $L.get()", tableClassName, tableVarName, tableClassName)
        .add("return $T.select(", Sql.class)
        .add(propertyList.stream()
            .flatMap(property -> property.columnAnnotationClassList().stream())
            .map(col -> String.format("%s.%s()", tableVarName, col.simpleName()))
            .collect(Collectors.joining(", ")))
        .add(")\n")
        .add("    .from($L)\n", tableVarName)
        .build();
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