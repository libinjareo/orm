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
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import br.com.objectos.code.AnnotationInfo;
import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.pojo.plugin.Property;
import br.com.objectos.schema.info.TableInfoAnnotationInfo;
import br.com.objectos.schema.meta.ColumnAnnotation;
import br.com.objectos.schema.meta.ColumnClass;
import br.com.objectos.schema.meta.ForeignKeyAnnotation;
import br.com.objectos.testable.Testable;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
abstract class OrmProperty implements Comparable<OrmProperty>, Testable {

  abstract Property property();
  abstract ReturnType returnType();
  abstract TableInfoAnnotationInfo tableInfo();
  abstract List<SimpleTypeInfo> columnAnnotationClassList();
  abstract int columnSeq();

  OrmProperty() {
  }

  public static Optional<OrmProperty> of(Property property) {
    Optional<AnnotationInfo> columnAnnotation = property.annotationInfoAnnotatedWith(ColumnAnnotation.class);

    if (columnAnnotation.isPresent()) {
      return columnAnnotation.map(ann -> ColumnOrmProperty.of(property, ann));
    }

    Optional<AnnotationInfo> foreignKey = property.annotationInfoAnnotatedWith(ForeignKeyAnnotation.class);

    if (foreignKey.isPresent()) {
      return foreignKey.map(ann -> ForeignKeyOrmProperty.of(property, ann));
    }

    return Optional.empty();
  }

  public abstract void acceptColumnsConstructor(ColumnsConstructor constructor);

  public abstract void acceptForeignKeyColumnsConstructor(ForeignKeyColumnsConstructor constructor);

  public void acceptIsOrmInsertableHelper(IsOrmInsertableHelper helper) {
    if (!isGenerated()) {
      helper.addColumnClassNameStream(columnClassNameStream());
      helper.addValueName(property().name());
    } else {
      helper.addGeneratedKeyListenerName(property().name());
    }
  }

  public abstract void acceptOrmPropertyHelper(OrmPropertyHelper helper);

  public abstract <T> T adapt(OrmPropertyAdapter<T> adapter);

  @Override
  public int compareTo(OrmProperty o) {
    return Integer.compare(columnSeq(), o.columnSeq());
  }

  public boolean isGenerated() {
    return false;
  }

  public boolean matchesAny(Set<ClassName> pkNameSet) {
    return false;
  }

  public String name() {
    return property().name();
  }

  public ParameterSpec parameterSpec() {
    String name = property().name();
    return parameterSpec(name);
  }

  public ParameterSpec parameterSpec(String name) {
    SimpleTypeInfo returnTypeInfo = property().returnTypeInfo();
    TypeName typeName = returnTypeInfo.typeName();
    return ParameterSpec.builder(typeName, name).build();
  }

  public abstract String rowConstructorParameterName(AtomicInteger i);

  @Override
  public String toString() {
    return property().name();
  }

  Stream<ClassName> columnClassNameStream() {
    return columnAnnotationClassList().stream()
        .map(SimpleTypeInfo::typeInfo)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(typeInfo -> typeInfo.annotationInfo(ColumnClass.class).get())
        .map(ann -> ann.simpleTypeInfoValue("value").get())
        .map(SimpleTypeInfo::className);
  }

}