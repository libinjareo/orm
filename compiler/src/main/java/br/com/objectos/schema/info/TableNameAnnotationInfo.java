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
import java.util.function.Supplier;

import br.com.objectos.code.TypeInfo;
import br.com.objectos.schema.meta.MigrationPrefix;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class TableNameAnnotationInfo implements Supplier<TableName> {

  private final TypeInfo typeInfo;

  private TableNameAnnotationInfo(TypeInfo typeInfo) {
    this.typeInfo = typeInfo;
  }

  public static TableName of(TypeInfo typeInfo) {
    return new TableNameAnnotationInfo(typeInfo).get();
  }

  @Override
  public TableName get() {
    return TableName.builder()
        .schemaName(schemaName())
        .simpleName(stringValue(br.com.objectos.schema.meta.TableName.class))
        .migrationVersion(migrationVersion())
        .build();
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