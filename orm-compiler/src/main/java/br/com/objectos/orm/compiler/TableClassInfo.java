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
import java.util.stream.Collectors;

import br.com.objectos.code.AnnotationInfo;
import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.schema.meta.ColumnAnnotationClassArray;
import br.com.objectos.testable.Testable;

import com.squareup.javapoet.ClassName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class TableClassInfo implements Testable {

  private static final Map<ClassName, TableClassInfo> CACHE = new ConcurrentHashMap<>();

  abstract ClassName className();
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
    ClassName className = tableTypeInfo.className();
    return CACHE.computeIfAbsent(className, cname -> of0(cname, tableTypeInfo));
  }

  static TableClassInfoBuilder builder() {
    return new TableClassInfoBuilderPojo();
  }

  private static TableClassInfo of0(ClassName className, TypeInfo tableTypeInfo) {
    return TableClassInfo.builder()
        .className(className)
        .columnAnnotationClassList(tableTypeInfo.annotationInfo(ColumnAnnotationClassArray.class)
            .flatMap(ann -> ann.simpleTypeInfoArrayValue("value"))
            .get())
        .build();
  }

  public boolean containsAll(List<SimpleTypeInfo> columnAnnotationClassList) {
    return columnAnnotationClassList().containsAll(columnAnnotationClassList);
  }

  public String columnMethodList(String varName) {
    return columnAnnotationClassList().stream()
        .map(ann -> String.format("%s.%s()", varName, ann.simpleName()))
        .collect(Collectors.joining(", "));
  }

}