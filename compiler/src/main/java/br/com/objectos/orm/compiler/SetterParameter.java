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

import java.util.List;
import java.util.Optional;

import br.com.objectos.code.AnnotationInfo;
import br.com.objectos.code.ParameterInfo;
import br.com.objectos.schema.meta.ColumnAnnotation;
import br.com.objectos.schema.meta.ForeignKeyAnnotation;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class SetterParameter {

  private final String name;
  private final OrmProperty referencedProperty;

  private SetterParameter(String name, OrmProperty referencedProperty) {
    this.name = name;
    this.referencedProperty = referencedProperty;
  }

  public static Optional<SetterParameter> of(OrmPojoInfo pojoInfo, ParameterInfo parameterInfo) {
    List<AnnotationInfo> annotationInfoList = parameterInfo.annotationInfoList();
    if (annotationInfoList.isEmpty()) {
      parameterInfo.compilationError("@SqlSetter parameter must have either a column or a foreign key annotation.");
      return Optional.empty();
    }

    AnnotationInfo annotationInfo = parameterInfo.annotationInfoAnnotatedWith(ColumnAnnotation.class)
        .orElseGet(() -> parameterInfo.annotationInfoAnnotatedWith(ForeignKeyAnnotation.class).orElse(null));

    if (annotationInfo == null) {
      parameterInfo.compilationError("@SqlSetter parameter must have either a column or a foreign key annotation.");
      return Optional.empty();
    }

    return pojoInfo.propertyAnnotatedWith(annotationInfo)
        .map(property -> SetterParameter.of(parameterInfo, property));
  }

  private static SetterParameter of(ParameterInfo parameterInfo, OrmProperty property) {
    return new SetterParameter(parameterInfo.name(), property);
  }

  public String name() {
    return name;
  }

  public OrmProperty referencedProperty() {
    return referencedProperty;
  }

}