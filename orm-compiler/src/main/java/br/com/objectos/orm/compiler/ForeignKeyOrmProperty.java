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

import br.com.objectos.code.AnnotationInfo;
import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.pojo.plugin.Property;
import br.com.objectos.schema.info.TableInfoAnnotationInfo;
import br.com.objectos.schema.meta.ColumnAnnotationClassArray;
import br.com.objectos.schema.meta.ColumnSeq;

import com.squareup.javapoet.MethodSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class ForeignKeyOrmProperty extends OrmProperty {

  abstract AnnotationInfo foreignKeyAnnotationInfo();

  ForeignKeyOrmProperty() {
  }

  public static ForeignKeyOrmProperty of(Property property, AnnotationInfo foreignKeyAnnotationInfo) {
    List<SimpleTypeInfo> columnAnnotationClassList = foreignKeyAnnotationInfo
        .annotationInfo(ColumnAnnotationClassArray.class)
        .flatMap(ann -> ann.simpleTypeInfoArrayValue("value"))
        .get();
    return ForeignKeyOrmProperty.builder()
        .property(property)
        .tableClassInfo(TableInfoAnnotationInfo.of(foreignKeyAnnotationInfo))
        .columnAnnotationClassList(columnAnnotationClassList)
        .columnSeq(columnAnnotationClassList.get(0)
            .typeInfo()
            .flatMap(t -> t.annotationInfo(ColumnSeq.class))
            .flatMap(ann -> ann.annotationValueInfo("value"))
            .get()
            .intValue())
        .foreignKeyAnnotationInfo(foreignKeyAnnotationInfo)
        .build();
  }

  static ForeignKeyOrmPropertyBuilder builder() {
    return new ForeignKeyOrmPropertyBuilderPojo();
  }

  @Override
  public void acceptConstructor1(MethodSpec.Builder constructor) {
    String name = property().name();
    constructor
        .addParameter(property().returnTypeInfo().typeName(), name)
        .addStatement("this.$1L = $1L", name);
  }

}