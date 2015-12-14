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

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class OrmRelationInfo {

  private final List<Item> itemList;
  private final List<SimpleTypeInfo> referencesColumnAnnotationClassList;

  private OrmRelationInfo(List<Item> itemList, List<SimpleTypeInfo> referencesColumnAnnotationClassList) {
    this.itemList = itemList;
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

    final ImmutableList.Builder<Item> itemList = ImmutableList.builder();
    final ImmutableList.Builder<SimpleTypeInfo> referencesColumnAnnotationClassList = ImmutableList.builder();

    private Builder() {
    }

    public void add(ForeignKeyOrmProperty property) {
      ClassName tableClassName = property.tableInfo().className();

      Iterator<SimpleTypeInfo> columnAnnotationIterator = property.columnAnnotationClassList().iterator();
      Iterator<ColumnOrmProperty> referencedIterator = property.referencedPropertyList().iterator();
      while (columnAnnotationIterator.hasNext()) {
        SimpleTypeInfo columnAnnotation = columnAnnotationIterator.next();
        referencesColumnAnnotationClassList.add(columnAnnotation);

        ColumnOrmProperty referenced = referencedIterator.next();
        Item item = Item.of(tableClassName, columnAnnotation, referenced);
        itemList.add(item);
      }
    }

    public OrmRelationInfo build() {
      return new OrmRelationInfo(
          itemList.build(),
          referencesColumnAnnotationClassList.build());
    }

  }

  private static class Item {

    private final ClassName tableClassName;
    private final String columnAnnotationSimpleName;
    private final String propertyName;

    private Item(ClassName tableClassName, String columnAnnotationSimpleName, String propertyName) {
      this.tableClassName = tableClassName;
      this.columnAnnotationSimpleName = columnAnnotationSimpleName;
      this.propertyName = propertyName;
    }

    public static Item of(ClassName tableClassName, SimpleTypeInfo columnAnnotation, ColumnOrmProperty referenced) {
      return new Item(tableClassName, columnAnnotation.simpleName(), referenced.name());
    }

    public void acceptExpression(CodeBlock.Builder expression, String keyword) {
      expression.add("    .$L($T.$L()).eq($L)\n",
          keyword, tableClassName, columnAnnotationSimpleName, propertyName);
    }

  }

  private class Where implements QueryWhereExpression {

    @Override
    public CodeBlock get() {
      CodeBlock.Builder expression = CodeBlock.builder();

      Iterator<Item> itemIterator = itemList.iterator();
      if (itemIterator.hasNext()) {
        Item item = itemIterator.next();
        item.acceptExpression(expression, "where");
        while (itemIterator.hasNext()) {
          item = itemIterator.next();
          item.acceptExpression(expression, "and");
        }
      }

      return expression.build();
    }

  }

}