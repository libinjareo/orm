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
package br.com.objectos.way.orm.compiler;

import java.util.Iterator;
import java.util.List;

import br.com.objectos.core.collections.ImmutableList;
import br.com.objectos.way.code.SimpleTypeInfo;
import br.com.objectos.way.pojo.plugin.Naming;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class OrmRelationInfo {

  private final OrmPojoInfo ownerPojoInfo;
  private final OrmPojoInfo pojoInfo;

  private final List<ReturnType> returnTypeList;
  private final List<ReferencedProperty> referencedPropertyList;
  private final List<SimpleTypeInfo> referencesColumnAnnotationClassList;

  private OrmRelationInfo(OrmPojoInfo ownerPojoInfo,
                          OrmPojoInfo pojoInfo,
                          List<ReturnType> returnTypeList,
                          List<ReferencedProperty> referencedPropertyList,
                          List<SimpleTypeInfo> referencesColumnAnnotationClassList) {
    this.ownerPojoInfo = ownerPojoInfo;
    this.pojoInfo = pojoInfo;
    this.returnTypeList = returnTypeList;
    this.referencedPropertyList = referencedPropertyList;
    this.referencesColumnAnnotationClassList = referencesColumnAnnotationClassList;
  }

  public static Builder builder(OrmPojoInfo ownerPojoInfo, OrmPojoInfo pojoInfo) {
    return new Builder(ownerPojoInfo, pojoInfo);
  }

  public QueryCollectExpression collectExpression(QueryReturnType returnType) {
    return new Collect(returnType);
  }

  public QuerySelectExpression filter(QuerySelectExpression selectExpression) {
    return selectExpression.removeAll(referencesColumnAnnotationClassList);
  }

  public QueryWhereExpression whereExpression() {
    return new Where();
  }

  public static class Builder {

    private final OrmPojoInfo ownerPojoInfo;
    private final OrmPojoInfo pojoInfo;

    final ImmutableList.Builder<ReturnType> returnTypeList = ImmutableList.builder();
    final ImmutableList.Builder<ReferencedProperty> referencedPropertyList = ImmutableList.builder();
    final ImmutableList.Builder<SimpleTypeInfo> referencesColumnAnnotationClassList = ImmutableList.builder();

    private Builder(OrmPojoInfo ownerPojoInfo, OrmPojoInfo pojoInfo) {
      this.ownerPojoInfo = ownerPojoInfo;
      this.pojoInfo = pojoInfo;
    }

    public void add(ForeignKeyOrmProperty property) {
      ReturnType returnType = property.returnType();
      returnTypeList.add(returnType);

      ClassName tableClassName = property.tableInfo().className();

      Iterator<SimpleTypeInfo> columnAnnotationIterator = property.columnAnnotationClassList().iterator();
      Iterator<ColumnOrmProperty> referencedIterator = property.referencedPropertyList().iterator();
      while (columnAnnotationIterator.hasNext()) {
        SimpleTypeInfo columnAnnotation = columnAnnotationIterator.next();
        referencesColumnAnnotationClassList.add(columnAnnotation);

        ColumnOrmProperty referenced = referencedIterator.next();
        ReferencedProperty referencedProperty = ReferencedProperty.of(tableClassName, columnAnnotation, referenced);
        referencedPropertyList.add(referencedProperty);
      }
    }

    public OrmRelationInfo build() {
      return new OrmRelationInfo(
          ownerPojoInfo,
          pojoInfo,
          returnTypeList.build(),
          referencedPropertyList.build(),
          referencesColumnAnnotationClassList.build());
    }

  }

  private static class ReferencedProperty {

    private final ClassName tableClassName;
    private final String columnAnnotationSimpleName;
    private final String propertyName;

    private ReferencedProperty(ClassName tableClassName, String columnAnnotationSimpleName, String propertyName) {
      this.tableClassName = tableClassName;
      this.columnAnnotationSimpleName = columnAnnotationSimpleName;
      this.propertyName = propertyName;
    }

    public static ReferencedProperty of(ClassName tableClassName, SimpleTypeInfo columnAnnotation,
        ColumnOrmProperty referenced) {
      return new ReferencedProperty(tableClassName, columnAnnotation.simpleName(), referenced.name());
    }

    public void acceptExpression(CodeBlock.Builder expression, String keyword) {
      expression.add("    .$L($T.$L()).eq($L)\n",
          keyword, tableClassName, columnAnnotationSimpleName, propertyName);
    }

  }

  private class Collect implements QueryCollectExpression {

    private final QueryReturnType returnType;

    public Collect(QueryReturnType returnType) {
      this.returnType = returnType;
    }

    @Override
    public CodeBlock get() {
      Naming naming = pojoInfo.naming();
      OrmInject inject = pojoInfo.inject();
      ClassName ownerClassName = ownerPojoInfo.naming().pojo();

      CodeBlock.Builder collectCode = CodeBlock.builder()
          .add("row -> $T.get($L).load(", naming.superClassSuffix("Orm"), inject.name());

      Iterator<ReturnType> retIterator = returnTypeList.iterator();
      while (retIterator.hasNext()) {
        ReturnType retType = retIterator.next();
        retType.acceptQueryCollectExpression(collectCode, ownerClassName);
        collectCode.add(", ");
      }

      return returnType.collect(collectCode
          .add("row)")
          .build());
    }

  }

  private class Where implements QueryWhereExpression {

    @Override
    public CodeBlock get() {
      CodeBlock.Builder expression = CodeBlock.builder();

      Iterator<ReferencedProperty> propIterator = referencedPropertyList.iterator();
      if (propIterator.hasNext()) {
        ReferencedProperty referencedProperty = propIterator.next();
        referencedProperty.acceptExpression(expression, "where");
        while (propIterator.hasNext()) {
          referencedProperty = propIterator.next();
          referencedProperty.acceptExpression(expression, "and");
        }
      }

      return expression.build();
    }

  }

}