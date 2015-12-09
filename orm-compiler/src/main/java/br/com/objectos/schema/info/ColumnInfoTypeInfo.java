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
import java.util.Optional;

import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.collections.MoreCollectors;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.schema.meta.ColumnAnnotationClassArray;
import br.com.objectos.schema.meta.ColumnName;
import br.com.objectos.schema.meta.Nullable;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class ColumnInfoTypeInfo implements ColumnInfo {

  @Override
  public abstract OrmGenerationInfo generationInfo();
  abstract SimpleTypeInfo simpleTypeInfo();

  ColumnInfoTypeInfo() {
  }

  public static List<ColumnInfoTypeInfo> listOf(TableName tableName, TypeInfo typeInfo) {
    return typeInfo.annotationInfo(ColumnAnnotationClassArray.class)
        .flatMap(ann -> ann.simpleTypeInfoArrayValue("value"))
        .get().stream()
        .map(SimpleTypeInfo::typeInfo)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(it -> of(tableName, it))
        .collect(MoreCollectors.toImmutableList());
  }

  public static ColumnInfoTypeInfo of(TableName tableName, TypeInfo typeInfo) {
    return ColumnInfoTypeInfo.builder()
        .tableName(tableName)
        .simpleName(stringValue(typeInfo, ColumnName.class))
        .nullable(typeInfo.hasAnnotation(Nullable.class))
        .generationInfo(OrmGenerationInfo.of(typeInfo))
        .simpleTypeInfo(typeInfo.toSimpleTypeInfo())
        .build();
  }

  static ColumnInfoTypeInfoBuilder builder() {
    return new ColumnInfoTypeInfoBuilderPojo();
  }

  private static String stringValue(TypeInfo typeInfo, Class<? extends Annotation> type) {
    return typeInfo.annotationInfo(type)
        .flatMap(annotationInfo -> annotationInfo.stringValue("value"))
        .get();
  }

  public String identifier() {
    return simpleTypeInfo().simpleName();
  }

  @Override
  public String toString() {
    return simpleName();
  }

}