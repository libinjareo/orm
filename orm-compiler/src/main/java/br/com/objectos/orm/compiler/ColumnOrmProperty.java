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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import br.com.objectos.code.AnnotationInfo;
import br.com.objectos.code.AnnotationValueInfo;
import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.collections.ImmutableList;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.pojo.plugin.PojoProperty;
import br.com.objectos.pojo.plugin.PojoPropertyConstructorStatementBuilder.Add;
import br.com.objectos.pojo.plugin.Property;
import br.com.objectos.schema.info.TableInfoAnnotationInfo;
import br.com.objectos.schema.meta.ColumnAnnotation;
import br.com.objectos.schema.meta.ColumnClass;
import br.com.objectos.schema.meta.ColumnName;
import br.com.objectos.schema.meta.ColumnSeq;

import com.squareup.javapoet.ClassName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class ColumnOrmProperty extends OrmProperty {

  private static final Map<Property, ColumnOrmProperty> CACHE = new ConcurrentHashMap<>();

  abstract AnnotationInfo columnAnnotationInfo();
  abstract ClassName columnClassName();
  abstract String columnSimpleName();
  abstract BindType bindType();
  abstract GenerationType generationType();

  ColumnOrmProperty() {
  }

  public static ColumnOrmProperty get(Property property) {
    return CACHE.computeIfAbsent(property, prop -> {
      AnnotationInfo columnAnnotationInfo = prop.annotationInfoAnnotatedWith(ColumnAnnotation.class).get();
      return of0(prop, columnAnnotationInfo);
    });
  }

  public static void invalidate() {
    CACHE.clear();
  }

  public static ColumnOrmProperty of(Property property, AnnotationInfo columnAnnotationInfo) {
    return CACHE.computeIfAbsent(property, prop -> of0(prop, columnAnnotationInfo));
  }

  private static ColumnOrmProperty of0(Property property, AnnotationInfo columnAnnotationInfo) {
    SimpleTypeInfo returnTypeInfo = property.returnTypeInfo();
    ReturnTypeHelper returnTypeHelper = ReturnTypeHelper.of(returnTypeInfo);

    int columnSeq = 0;
    Optional<AnnotationValueInfo> value = columnAnnotationInfo
        .annotationInfo(ColumnSeq.class)
        .flatMap(ann -> ann.annotationValueInfo("value"));
    if (value.isPresent()) {
      columnSeq = value.get().intValue();
    }

    TypeInfo columnClassTypeInfo = columnAnnotationInfo
        .annotationInfo(ColumnClass.class)
        .flatMap(annotationInfo -> annotationInfo.simpleTypeInfoValue("value"))
        .flatMap(typeInfo -> typeInfo.typeInfo())
        .get();

    return ColumnOrmProperty.builder()
        .property(property)
        .returnType(returnTypeHelper.returnType())
        .tableInfo(TableInfoAnnotationInfo.of(columnAnnotationInfo))
        .columnAnnotationClassList(ImmutableList.of(columnAnnotationInfo.simpleTypeInfo()))
        .columnSeq(columnSeq)
        .columnAnnotationInfo(columnAnnotationInfo)
        .columnClassName(columnClassTypeInfo.className())
        .columnSimpleName(columnAnnotationInfo
            .annotationInfo(ColumnName.class)
            .flatMap(ann -> ann.stringValue("value"))
            .get())
        .bindType(returnTypeHelper.bindType(columnClassTypeInfo))
        .generationType(GenerationType.of(columnAnnotationInfo))
        .build();
  }

  static ColumnOrmPropertyBuilder builder() {
    return new ColumnOrmPropertyBuilderPojo();
  }

  @Override
  public void acceptColumnsConstructor(ColumnsConstructor constructor) {
    constructor.set(columnClassName(), property().name());
  }

  @Override
  public void acceptOrmPropertyHelper(OrmPropertyHelper helper) {
    helper.addColumnOrmProperty(this);
  }

  @Override
  public void acceptForeignKeyColumnsConstructor(ForeignKeyColumnsConstructor constructor) {
    constructor
        .addParameter(columnClassName(), name())
        .addCode(", " + name());
  }

  @Override
  public <T> T adapt(OrmPropertyAdapter<T> adapter) {
    return adapter.onColumn(this);
  }

  public boolean columnAnnotationMatches(SimpleTypeInfo annotationTypeInfo) {
    return columnAnnotationInfo().simpleTypeInfo().equals(annotationTypeInfo);
  }

  public ConstructorStatementWriter constructorStatementWriter(String statement) {
    Add builder = PojoProperty.constructorStatementBuilder(property()).add(statement);
    return new ConstructorStatementWriter(builder);
  }

  public PojoProperty executePojoProperty() {
    return returnType().executePojoProperty(this);
  }

  public String foreignKeyColumnsConstructor(String fieldName) {
    return fieldName + "." + columnClassName().simpleName() + "()";
  }

  @Override
  public boolean isGenerated() {
    return generationType().isGenerated();
  }

  @Override
  public boolean matchesAny(Set<ClassName> pkNameSet) {
    ClassName className = columnAnnotationInfo().simpleTypeInfo().className();
    return pkNameSet.contains(className);
  }

  public MethodWriter methodWriter(String template) {
    return new MethodWriter(template);
  }

  public PojoProperty optionalConstructorStatement() {
    return bindType().optionalConstructorStatement(this);
  }

  public PojoProperty optionalMethod() {
    return bindType().optionalMethod(this);
  }

  @Override
  public String rowConstructorParameterName(AtomicInteger i) {
    return "row.column" + i.getAndIncrement() + "()";
  }

  public PojoProperty standardConstructorStatement() {
    return bindType().standardConstructorStatement(this);
  }

  public PojoProperty standardMethod() {
    return bindType().standardMethod(this);
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
      builder.set(tableInfo().className());
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
      argList.add(returnType().typeName());
      return this;
    }

  }

}