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

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.com.objectos.code.AnnotationInfo;
import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.collections.ImmutableList;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.schema.meta.ColumnAnnotation;
import br.com.objectos.schema.meta.ColumnAnnotationClassArray;
import br.com.objectos.schema.meta.ForeignKeyAnnotation;
import br.com.objectos.schema.meta.PrimaryKeyClassArray;
import br.com.objectos.testable.Testable;

import com.squareup.javapoet.ClassName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class TableClassInfo implements Testable {

  private static final Map<String, TableClassInfo> qualifiedNameMap = new ConcurrentHashMap<>();

  abstract TableName tableName();
  abstract ClassName tableClassName();
  abstract List<SimpleTypeInfo> columnAnnotationClassList();
  abstract List<SimpleTypeInfo> primaryKeyList();

  TableClassInfo() {
  }

  public static TableClassInfoBuilder builder() {
    return new TableClassInfoBuilderPojo();
  }

  public static void clear() {
    qualifiedNameMap.clear();
  }

  public static TableClassInfo of(AnnotationInfo columnAnnotationInfo) {
    Preconditions.checkArgument(
        columnAnnotationInfo.hasAnnotation(ColumnAnnotation.class)
            || columnAnnotationInfo.hasAnnotation(ForeignKeyAnnotation.class));
    TypeInfo tableTypeInfo = columnAnnotationInfo
        .enclosingTypeInfo()
        .get();
    return qualifiedNameMap.computeIfAbsent(
        tableTypeInfo.qualifiedName(),
        qname -> {
          return TableClassInfo.builder()
              .tableName(TableNameAnnotationInfo.of(tableTypeInfo))
              .tableClassName(columnAnnotationInfo
                  .enclosingSimpleTypeInfo()
                  .map(SimpleTypeInfo::className)
                  .get())
              .columnAnnotationClassList(tableTypeInfo.annotationInfo(ColumnAnnotationClassArray.class)
                  .flatMap(ann -> ann.simpleTypeInfoArrayValue("value"))
                  .get())
              .primaryKeyList(tableTypeInfo.annotationInfo(PrimaryKeyClassArray.class)
                  .flatMap(ann -> ann.simpleTypeInfoArrayValue("value"))
                  .orElse(ImmutableList.of()))
              .build();
        });
  }

}