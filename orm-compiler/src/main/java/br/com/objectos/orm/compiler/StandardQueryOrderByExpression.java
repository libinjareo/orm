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
package br.com.objectos.orm.compiler;

import java.util.List;
import java.util.stream.Collectors;

import br.com.objectos.code.AnnotationInfo;
import br.com.objectos.code.MethodInfo;
import br.com.objectos.collections.MoreCollectors;
import br.com.objectos.db.query.SortOrder;
import br.com.objectos.schema.meta.ColumnAnnotation;

import com.squareup.javapoet.CodeBlock;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class StandardQueryOrderByExpression implements QueryOrderByExpression {

  private final List<Column> columnList;

  private StandardQueryOrderByExpression(List<Column> columnList) {
    this.columnList = columnList;
  }

  public static StandardQueryOrderByExpression of(MethodInfo methodInfo) {
    return new StandardQueryOrderByExpression(
        methodInfo.annotationInfoListAnnotatedWith(ColumnAnnotation.class)
            .stream()
            .map(Column::of)
            .collect(MoreCollectors.toImmutableList()));
  }

  @Override
  public CodeBlock get() {
    return columnList.isEmpty()
        ? CodeBlocks.empty()
        : CodeBlock.builder()
            .add("    .orderBy($L)\n", columnList.stream()
                .map(Column::toSql)
                .collect(Collectors.joining(", ")))
            .build();
  }

  private static class Column {

    private final String tableClassSimpleName;
    private final String columnAnnotationSimpleName;
    private final SortOrder sortOrder;

    private Column(String tableClassSimpleName, String columnAnnotationSimpleName, SortOrder sortOrder) {
      this.tableClassSimpleName = tableClassSimpleName;
      this.columnAnnotationSimpleName = columnAnnotationSimpleName;
      this.sortOrder = sortOrder;
    }

    public static Column of(AnnotationInfo annotationInfo) {
      return new Column(
          annotationInfo.enclosingTypeInfo().get().simpleName(),
          annotationInfo.simpleName(),
          annotationInfo.annotationValueInfo("orderBy")
              .map(value -> value.enumValue(SortOrder.class))
              .orElse(SortOrder.ASC));
    }

    public String toSql() {
      return String.format("%s.%s().%s()",
          tableClassSimpleName, columnAnnotationSimpleName, sortOrder.lowerCase());
    }

  }

}