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

import javax.lang.model.element.Modifier;

import br.com.objectos.code.AnnotationInfo;
import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.pojo.plugin.PojoProperty;
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

  ColumnSqlPojoMethod() {
  }

  public static ColumnSqlPojoMethod of(Property property) {
    AnnotationInfo columnAnnotationInfo = property.annotationInfoAnnotatedWith(ColumnAnnotation.class).get();
    return ColumnSqlPojoMethod.builder()
        .property(property)
        .columnAnnotationInfo(columnAnnotationInfo)
        .columnClassName(columnAnnotationInfo
            .annotationInfo(ColumnClass.class)
            .flatMap(annotationInfo -> annotationInfo.simpleTypeInfoValue("value"))
            .flatMap(typeInfo -> typeInfo.typeInfo())
            .get()
            .className())
        .build();
  }

  private static ColumnSqlPojoMethodBuilder builder() {
    return new ColumnSqlPojoMethodBuilderPojo();
  }

  @Override
  PojoProperty constructorStatement() {
    return PojoProperty.constructorStatementBuilder(property())
        .add("$L = $T.get().$L($L)")
        .setPropertyName()
        .set(tableClassName())
        .set(columnAnnotationInfo().simpleName())
        .setBuilderGet()
        .build();
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
    return PojoProperty.overridingMethodBuilder(property())
        .statement("return $L.get()", property().name())
        .build();
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

}