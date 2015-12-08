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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.sql.query.Sql;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class SingletonQuerySelectExpression implements QuerySelectExpression {

  private final ClassName tableClassName;
  private final List<OrmProperty> propertyList;

  public SingletonQuerySelectExpression(ClassName tableClassName, List<OrmProperty> propertyList) {
    this.tableClassName = tableClassName;
    this.propertyList = propertyList;
  }

  @Override
  public QuerySelectExpression removeAll(List<SimpleTypeInfo> referencesList) {
    return new Sub(tableClassName, propertyList, referencesList);
  }

  @Override
  public CodeBlock get() {
    String tableVarName = tableClassName.simpleName();
    return CodeBlock.builder()
        .addStatement("$T $L = $L.get()", tableClassName, tableVarName, tableClassName)
        .add("return $T.select(", Sql.class)
        .add(columnAnnotationClassStream()
            .map(col -> String.format("%s.%s()", tableVarName, col.simpleName()))
            .collect(Collectors.joining(", ")))
        .add(")\n")
        .add("    .from($L)\n", tableVarName)
        .build();
  }

  Stream<SimpleTypeInfo> columnAnnotationClassStream() {
    return propertyList.stream()
        .sorted()
        .flatMap(OrmProperty::columnAnnotationClassStream);
  }

  private static class Sub extends SingletonQuerySelectExpression {

    private final List<SimpleTypeInfo> referencesList;

    public Sub(ClassName tableClassName, List<OrmProperty> propertyList, List<SimpleTypeInfo> referencesList) {
      super(tableClassName, propertyList);
      this.referencesList = referencesList;
    }

    @Override
    Stream<SimpleTypeInfo> columnAnnotationClassStream() {
      return super.columnAnnotationClassStream()
          .filter(column -> !referencesList.contains(column));
    }

  }

}