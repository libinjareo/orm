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

import java.lang.annotation.Annotation;
import java.util.function.Function;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import br.com.objectos.code.AbstractTestingProcessor;
import br.com.objectos.code.Testing;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.orm.Insertable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class TestingProcessor extends AbstractTestingProcessor {

  @Override
  protected Class<? extends Annotation> annotationType() {
    return Testing.class;
  }

  @Override
  protected Function<TypeElement, TypeInfo> typeElementMapper() {
    return new ProcessingEnvironmentWrapper(processingEnv)::toTypeInfo;
  }

  private static class ProcessingEnvironmentWrapper extends br.com.objectos.code.ProcessingEnvironmentWrapper {

    private final Multimap<Class<?>, String> subTypeMap = ImmutableMultimap.<Class<?>, String> builder()
        .put(Insertable.class, "br.com.objectos.schema.it.Pair")
        .put(Insertable.class, "br.com.objectos.schema.it.Revision")
        .build();

    public ProcessingEnvironmentWrapper(ProcessingEnvironment environment) {
      super(environment);
    }

    @Override
    public boolean isSubType(TypeMirror t1, Class<?> maybeSuperType) {
      TypeMirror rawT1 = rawTypeMirror(t1);
      return subTypeMap.containsKey(maybeSuperType)
          ? subTypeMap.get(maybeSuperType).contains(rawT1.toString())
          : super.isSubType(t1, maybeSuperType);
    }

  }

}