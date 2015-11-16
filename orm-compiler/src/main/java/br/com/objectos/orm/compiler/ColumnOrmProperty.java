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

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import br.com.objectos.code.AnnotationInfo;
import br.com.objectos.code.AnnotationValueInfo;
import br.com.objectos.collections.ImmutableList;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.pojo.plugin.Property;
import br.com.objectos.schema.info.TableInfoAnnotationInfo;
import br.com.objectos.schema.meta.ColumnClass;
import br.com.objectos.schema.meta.ColumnSeq;

import com.squareup.javapoet.ClassName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class ColumnOrmProperty extends OrmProperty {

  abstract AnnotationInfo columnAnnotationInfo();
  abstract ClassName columnClassName();
  abstract GenerationType generationType();

  ColumnOrmProperty() {
  }

  public static ColumnOrmProperty of(Property property, AnnotationInfo columnAnnotationInfo) {
    int columnSeq = 0;
    Optional<AnnotationValueInfo> value = columnAnnotationInfo
        .annotationInfo(ColumnSeq.class)
        .flatMap(ann -> ann.annotationValueInfo("value"));
    if (value.isPresent()) {
      columnSeq = value.get().intValue();
    }

    return ColumnOrmProperty.builder()
        .property(property)
        .tableClassInfo(TableInfoAnnotationInfo.of(columnAnnotationInfo))
        .columnAnnotationClassList(ImmutableList.of(columnAnnotationInfo.simpleTypeInfo()))
        .columnSeq(columnSeq)
        .columnAnnotationInfo(columnAnnotationInfo)
        .columnClassName(columnAnnotationInfo
            .annotationInfo(ColumnClass.class)
            .flatMap(annotationInfo -> annotationInfo.simpleTypeInfoValue("value"))
            .flatMap(typeInfo -> typeInfo.typeInfo())
            .get()
            .className())
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
  public void acceptOrmPojoInfoHelper(OrmPojoInfoHelper helper) {
    helper.addColumnOrmProperty(this);
  }

  @Override
  public boolean isGenerated() {
    return generationType().isGenerated();
  }

  @Override
  public String rowConstructorParameterName(AtomicInteger i) {
    return "row.column" + i.getAndIncrement() + "()";
  }

}