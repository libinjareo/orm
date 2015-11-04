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
import java.util.stream.Stream;

import br.com.objectos.code.AnnotationInfo;
import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.pojo.plugin.Property;
import br.com.objectos.schema.meta.ColumnAnnotation;
import br.com.objectos.schema.meta.ColumnClass;
import br.com.objectos.schema.meta.ForeignKeyAnnotation;
import br.com.objectos.testable.Testable;

import com.squareup.javapoet.ClassName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
abstract class OrmProperty implements Testable {

  abstract Property property();
  abstract TableClassInfo tableClassInfo();
  abstract List<SimpleTypeInfo> columnAnnotationClassList();

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

  public void acceptIsOrmInsertableHelper(IsOrmInsertableHelper helper) {
    helper.addColumnClassNameStream(columnClassNameStream());
    helper.addValueName(property().name());
  }

  private Stream<ClassName> columnClassNameStream() {
    return columnAnnotationClassList().stream()
        .map(SimpleTypeInfo::typeInfo)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(typeInfo -> typeInfo.annotationInfo(ColumnClass.class).get())
        .map(ann -> ann.simpleTypeInfoValue("value").get())
        .map(SimpleTypeInfo::className);
  }

}