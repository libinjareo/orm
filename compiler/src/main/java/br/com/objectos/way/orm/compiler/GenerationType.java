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
package br.com.objectos.way.orm.compiler;

import br.com.objectos.code.AnnotationInfo;
import br.com.objectos.schema.meta.GeneratedValue;
import br.com.objectos.schema.meta.GenerationKind;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
enum GenerationType {

  NONE {
    @Override
    public <T> T adapt(GenerationTypeAdapter<T> adapter) {
      return adapter.onNone();
    }

    @Override
    public boolean isGenerated() {
      return false;
    }
  },

  AUTO_INCREMENT {
    @Override
    public <T> T adapt(GenerationTypeAdapter<T> adapter) {
      return adapter.onAutoIncrement();
    }
  };

  public static GenerationType of(AnnotationInfo columnAnnotationInfo) {
    return columnAnnotationInfo.annotationInfo(GeneratedValue.class)
        .flatMap(ann -> ann.enumConstantInfoValue("value"))
        .map(value -> value.getEnumValue(GenerationKind.class))
        .map(kind -> GenerationType.valueOf(kind.name()))
        .orElse(GenerationType.NONE);
  }

  public abstract <T> T adapt(GenerationTypeAdapter<T> adapter);

  public boolean isGenerated() {
    return true;
  }

}