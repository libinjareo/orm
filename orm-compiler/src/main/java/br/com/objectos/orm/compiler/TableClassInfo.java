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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.com.objectos.code.AnnotationInfo;
import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.schema.meta.ColumnAnnotationClassArray;
import br.com.objectos.testable.Testable;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class TableClassInfo implements Testable {

  private static final Map<String, TableClassInfo> CACHE = new ConcurrentHashMap<>();

  abstract List<SimpleTypeInfo> columnAnnotationClassList();

  TableClassInfo() {
  }

  public static void invalidate() {
    CACHE.clear();
  }

  public static TableClassInfo of(AnnotationInfo columnAnnotationInfo) {
    TypeInfo tableTypeInfo = columnAnnotationInfo
        .enclosingTypeInfo()
        .get();
    String qualifiedName = tableTypeInfo.qualifiedName();
    return CACHE.computeIfAbsent(qualifiedName, qname -> of0(tableTypeInfo));
  }

  static TableClassInfoBuilder builder() {
    return new TableClassInfoBuilderPojo();
  }

  private static TableClassInfo of0(TypeInfo tableTypeInfo) {
    return TableClassInfo.builder()
        .columnAnnotationClassList(tableTypeInfo.annotationInfo(ColumnAnnotationClassArray.class)
            .flatMap(ann -> ann.simpleTypeInfoArrayValue("value"))
            .get())
        .build();
  }

}