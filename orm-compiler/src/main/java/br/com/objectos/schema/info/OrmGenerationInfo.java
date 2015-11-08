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
package br.com.objectos.schema.info;

import br.com.objectos.code.HasAnnotationInfoList;
import br.com.objectos.schema.meta.GeneratedValue;
import br.com.objectos.schema.meta.GenerationKind;
import br.com.objectos.testable.Equality;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
abstract class OrmGenerationInfo implements GenerationInfo {

  public static OrmGenerationInfo of(HasAnnotationInfoList hasAnnotation) {
    return hasAnnotation.annotationInfo(GeneratedValue.class)
        .flatMap(annotationInfo -> annotationInfo.annotationValueInfo("value"))
        .map(annotationValueInfo -> annotationValueInfo.enumValue(GenerationKind.class))
        .map(kind -> generationInfo(hasAnnotation, kind))
        .orElse(OrmNoGenerationInfo.get());
  }

  private static OrmGenerationInfo generationInfo(HasAnnotationInfoList hasAnnotation, GenerationKind kind) {
    switch (kind) {
    case AUTO_INCREMENT:
    default:
      return OrmAutoIncrementGenerationInfo.get();
    }
  }

  public boolean insertable() {
    return true;
  }

  @Override
  public Equality isEqualTo(Object that) {
    return Equality.instanceOf(that, OrmGenerationInfo.class);
  }

}