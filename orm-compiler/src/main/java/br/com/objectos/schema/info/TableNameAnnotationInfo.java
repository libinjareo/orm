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
package br.com.objectos.schema.info;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.collections.ImmutableList;
import br.com.objectos.schema.meta.MigrationPrefix;
import br.com.objectos.schema.meta.PrimaryKeyClassArray;

import com.squareup.javapoet.ClassName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class TableNameAnnotationInfo extends TableName {

  private static final Map<TypeInfo, TableNameAnnotationInfo> CACHE = new ConcurrentHashMap<>();

  private final TypeInfo typeInfo;
  private final TableName tableName;

  private TableNameAnnotationInfo(TypeInfo typeInfo, TableName tableName) {
    this.typeInfo = typeInfo;
    this.tableName = tableName;
  }

  public static void clear() {
    CACHE.clear();
  }

  public static TableNameAnnotationInfo of(TypeInfo typeInfo) {
    return CACHE.computeIfAbsent(typeInfo, it -> new Builder(it).get());
  }

  public List<ColumnInfo> columnInfoList() {
    return ImmutableList.of();
  }

  public List<ForeignKeyInfo> foreignKeyInfoList() {
    return ImmutableList.of();
  }

  public List<SimpleTypeInfo> primaryKeyClassList() {
    return typeInfo.annotationInfo(PrimaryKeyClassArray.class)
        .flatMap(ann -> ann.simpleTypeInfoArrayValue("value"))
        .orElse(ImmutableList.of());
  }

  public ClassName tableClassName() {
    return typeInfo.className();
  }

  @Override
  SchemaName schemaName() {
    return tableName.schemaName();
  }

  @Override
  String simpleName() {
    return tableName.simpleName();
  }

  @Override
  MigrationVersion migrationVersion() {
    return tableName.migrationVersion();
  }

  private static class Builder implements Supplier<TableNameAnnotationInfo> {

    private final TypeInfo typeInfo;

    public Builder(TypeInfo typeInfo) {
      this.typeInfo = typeInfo;
    }

    @Override
    public TableNameAnnotationInfo get() {
      TableName tableName = TableName.builder()
          .schemaName(schemaName())
          .simpleName(stringValue(br.com.objectos.schema.meta.TableName.class))
          .migrationVersion(migrationVersion())
          .build();
      return new TableNameAnnotationInfo(typeInfo, tableName);
    }

    private MigrationVersion migrationVersion() {
      return MigrationVersion.builder()
          .prefix(stringValue(MigrationPrefix.class))
          .build();
    }

    private SchemaName schemaName() {
      return SchemaName.builder()
          .simpleName(stringValue(br.com.objectos.schema.meta.SchemaName.class))
          .build();
    }

    private String stringValue(Class<? extends Annotation> type) {
      return typeInfo.annotationInfo(type)
          .flatMap(ann -> ann.stringValue("value"))
          .get();
    }

  }

}