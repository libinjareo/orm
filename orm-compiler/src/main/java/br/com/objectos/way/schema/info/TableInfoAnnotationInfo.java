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
package br.com.objectos.way.schema.info;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import br.com.objectos.core.collections.ImmutableList;
import br.com.objectos.core.collections.ImmutableSet;
import br.com.objectos.core.collections.MoreCollectors;
import br.com.objectos.way.code.AnnotationInfo;
import br.com.objectos.way.code.SimpleTypeInfo;
import br.com.objectos.way.code.TypeInfo;
import br.com.objectos.way.pojo.Pojo;
import br.com.objectos.way.schema.meta.PrimaryKeyClassArray;
import br.com.objectos.way.sql.Sql;
import br.com.objectos.way.testable.Equality;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
public abstract class TableInfoAnnotationInfo extends TableInfo {

  private static final Map<ClassName, TableInfoAnnotationInfo> CACHE = new ConcurrentHashMap<>();

  public abstract ClassName className();
  @Override
  public abstract TableName tableName();
  @Override
  public abstract List<ColumnInfoTypeInfo> columnInfoList();

  public abstract Set<ClassName> primaryKeyClassNameSet();

  TableInfoAnnotationInfo() {
  }

  public static void invalidate() {
    CACHE.clear();
  }

  public static TableInfoAnnotationInfo of(AnnotationInfo columnAnnotationInfo) {
    TypeInfo tableTypeInfo = columnAnnotationInfo
        .enclosingTypeInfo()
        .get();
    ClassName className = tableTypeInfo.className();
    return CACHE.computeIfAbsent(className, cname -> of0(cname, tableTypeInfo));
  }

  static TableInfoAnnotationInfoBuilder thisBuilder() {
    return new TableInfoAnnotationInfoBuilderPojo();
  }

  private static TableInfoAnnotationInfo of0(ClassName className, TypeInfo tableTypeInfo) {
    TableName tableName = TableNameAnnotationInfo.of(tableTypeInfo);
    return TableInfoAnnotationInfo.thisBuilder()
        .className(className)
        .tableName(tableName)
        .columnInfoList(ColumnInfoTypeInfo.listOf(tableName, tableTypeInfo))
        .primaryKeyClassNameSet(tableTypeInfo.annotationInfo(PrimaryKeyClassArray.class)
            .flatMap(ann -> ann.simpleTypeInfoArrayValue("value"))
            .map(list -> list.stream()
                .map(SimpleTypeInfo::className)
                .collect(MoreCollectors.toImmutableSet()))
            .orElse(ImmutableSet.of()))
        .build();
  }

  public boolean containsAll(List<SimpleTypeInfo> columnAnnotationClassList) {
    return columnInfoList().stream()
        .map(ColumnInfoTypeInfo::simpleTypeInfo)
        .collect(MoreCollectors.toImmutableList())
        .containsAll(columnAnnotationClassList);
  }

  public CodeBlock insertIntoCode() {
    String columnList = columnInfoList().stream()
        .map(ann -> String.format("%s.%s()", simpleName(), ann.identifier()))
        .collect(Collectors.joining(", "));
    return CodeBlock.builder()
        .add("$T\n", Sql.class)
        .add("    .insertInto($L)\n", simpleName())
        .add("    .$$($L)", columnList)
        .build();
  }

  @Override
  public abstract Equality isEqualTo(Object that);

  public CodeBlock tableGetCode() {
    return CodeBlock.builder()
        .addStatement("$T $L = $L.get()", className(), simpleName(), className())
        .build();
  }

  public String tableSimpleName() {
    return tableName().simpleName();
  }

  @Override
  public String toString() {
    return simpleName();
  }

  @Override
  Optional<? extends PrimaryKeyInfo> primaryKeyInfo() {
    return Optional.empty();
  }

  @Override
  List<? extends ForeignKeyInfo> foreignKeyInfoList() {
    return ImmutableList.of();
  }

}