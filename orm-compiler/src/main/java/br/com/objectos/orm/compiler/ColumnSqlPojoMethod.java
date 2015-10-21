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

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

import br.com.objectos.code.AnnotationInfo;
import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.pojo.plugin.PojoProperty;
import br.com.objectos.pojo.plugin.PojoPropertyConstructorStatementBuilder.Add;
import br.com.objectos.pojo.plugin.Property;
import br.com.objectos.schema.meta.ColumnAnnotation;
import br.com.objectos.schema.meta.ColumnClass;

import com.squareup.javapoet.ClassName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class ColumnSqlPojoMethod extends SqlPojoMethod {

  abstract AnnotationInfo columnAnnotationInfo();
  abstract ClassName columnClassName();
  abstract ColumnSqlPojoBindType bindType();

  ColumnSqlPojoMethod() {
  }

  public static ColumnSqlPojoMethod of(Property property) {
    AnnotationInfo columnAnnotationInfo = property.annotationInfoAnnotatedWith(ColumnAnnotation.class).get();
    SimpleTypeInfo returnTypeInfo = property.returnTypeInfo();
    TypeInfo columnClassTypeInfo = columnAnnotationInfo
        .annotationInfo(ColumnClass.class)
        .flatMap(annotationInfo -> annotationInfo.simpleTypeInfoValue("value"))
        .flatMap(typeInfo -> typeInfo.typeInfo())
        .get();
    SqlPojoReturnType returnType = SqlPojoReturnType.of(returnTypeInfo);
    return ColumnSqlPojoMethod.builder()
        .property(property)
        .columnAnnotationInfo(columnAnnotationInfo)
        .columnClassName(columnAnnotationInfo
            .annotationInfo(ColumnClass.class)
            .flatMap(annotationInfo -> annotationInfo.simpleTypeInfoValue("value"))
            .flatMap(typeInfo -> typeInfo.typeInfo())
            .get()
            .className())
        .bindType(returnType.bindType(returnTypeInfo, columnClassTypeInfo))
        .build();
  }

  private static ColumnSqlPojoMethodBuilder builder() {
    return new ColumnSqlPojoMethodBuilderPojo();
  }

  public ConstructorStatementWriter constructorStatementWriter(String statement) {
    Add builder = PojoProperty.constructorStatementBuilder(property()).add(statement);
    return new ConstructorStatementWriter(builder);
  }

  public MethodWriter methodWriter(String statement) {
    return new MethodWriter(statement);
  }

  @Override
  PojoProperty constructorStatement() {
    return bindType().constructorStatement(this);
  }

  @Override
  PojoProperty field() {
    return PojoProperty.fieldBuilder(property())
        .modifiers(Modifier.PRIVATE, Modifier.FINAL)
        .type(columnClassName())
        .build();
  }

  @Override
  PojoProperty method() {
    return bindType().method(this);
  }

  private ClassName tableClassName() {
    return columnAnnotationInfo()
        .annotationInfo(ColumnClass.class)
        .flatMap(ann -> ann.simpleTypeInfoValue("value"))
        .flatMap(SimpleTypeInfo::typeInfo)
        .flatMap(TypeInfo::enclosingTypeInfo)
        .get()
        .className();
  }

  public class ConstructorStatementWriter {

    private final Add builder;

    private ConstructorStatementWriter(Add builder) {
      this.builder = builder;
    }

    public PojoProperty build() {
      return builder.build();
    }

    public ConstructorStatementWriter setBuilderGet() {
      builder.setBuilderGet();
      return this;
    }

    public ConstructorStatementWriter setColumnAnnotationSimpleName() {
      builder.set(columnAnnotationInfo().simpleName());
      return this;
    }

    public ConstructorStatementWriter setPropertyName() {
      builder.setPropertyName();
      return this;
    }

    public ConstructorStatementWriter setTableClassName() {
      builder.set(tableClassName());
      return this;
    }

  }

  public class MethodWriter {

    private final String template;
    private final List<Object> argList = new ArrayList<>();

    private MethodWriter(String template) {
      this.template = template;
    }

    public PojoProperty build() {
      return PojoProperty.overridingMethodBuilder(property())
          .statement(template, argList.toArray())
          .build();
    }

    public MethodWriter setPropertyName() {
      argList.add(property().name());
      return this;
    }

    public MethodWriter setReturnTypeName() {
      SimpleTypeInfo returnTypeInfo = property().returnTypeInfo();
      argList.add(returnTypeInfo.typeName());
      return this;
    }

  }

}