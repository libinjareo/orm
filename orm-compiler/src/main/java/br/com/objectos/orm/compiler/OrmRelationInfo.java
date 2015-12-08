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

import java.util.Iterator;
import java.util.List;

import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.collections.ImmutableList;

import com.squareup.javapoet.CodeBlock;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class OrmRelationInfo {

  private final List<String> propertyNameList;
  private final List<SimpleTypeInfo> referencesColumnAnnotationClassList;

  private OrmRelationInfo(List<String> propertyNameList, List<SimpleTypeInfo> referencesColumnAnnotationClassList) {
    this.propertyNameList = propertyNameList;
    this.referencesColumnAnnotationClassList = referencesColumnAnnotationClassList;
  }

  public static Builder builder() {
    return new Builder();
  }

  public QuerySelectExpression filter(QuerySelectExpression selectExpression) {
    return selectExpression.removeAll(referencesColumnAnnotationClassList);
  }

  public QueryWhereExpression whereExpression() {
    return new Where();
  }

  public static class Builder {

    final ImmutableList.Builder<String> propertyNameList = ImmutableList.builder();
    final ImmutableList.Builder<SimpleTypeInfo> referencesColumnAnnotationClassList = ImmutableList.builder();

    private Builder() {
    }

    public void add(ForeignKeyOrmProperty property) {
      property.referencedPropertyList().forEach(column -> propertyNameList.add(column.name()));
      referencesColumnAnnotationClassList.addAll(property.columnAnnotationClassList());
    }

    public OrmRelationInfo build() {
      return new OrmRelationInfo(
          propertyNameList.build(),
          referencesColumnAnnotationClassList.build());
    }

  }

  private class Where implements QueryWhereExpression {

    @Override
    public CodeBlock get() {
      CodeBlock.Builder expression = CodeBlock.builder();

      Iterator<String> iterator = propertyNameList.iterator();
      if (iterator.hasNext()) {
        String property = iterator.next();
        where(expression, property, "where");
        while (iterator.hasNext()) {
          property = iterator.next();
          where(expression, property, "and");
        }
      }

      return expression.build();
    }

    private void where(CodeBlock.Builder expression, String property, String keyword) {
      expression.add("    .$L($L)\n", keyword, property);
    }

  }

}