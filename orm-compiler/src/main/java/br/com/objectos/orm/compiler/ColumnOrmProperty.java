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

import br.com.objectos.code.AnnotationInfo;
import br.com.objectos.collections.ImmutableList;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.pojo.plugin.Property;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class ColumnOrmProperty extends OrmProperty {

  abstract AnnotationInfo columnAnnotationInfo();
  abstract GenerationType generationType();

  ColumnOrmProperty() {
  }

  public static ColumnOrmProperty of(Property property, AnnotationInfo columnAnnotationInfo) {
    return ColumnOrmProperty.builder()
        .property(property)
        .tableClassInfo(TableClassInfo.of(columnAnnotationInfo))
        .columnAnnotationClassList(ImmutableList.of(columnAnnotationInfo.simpleTypeInfo()))
        .columnAnnotationInfo(columnAnnotationInfo)
        .generationType(GenerationType.of(columnAnnotationInfo))
        .build();
  }

  @Override
  public boolean isGenerated() {
    return generationType().isGenerated();
  }

  static ColumnOrmPropertyBuilder builder() {
    return new ColumnOrmPropertyBuilderPojo();
  }

}