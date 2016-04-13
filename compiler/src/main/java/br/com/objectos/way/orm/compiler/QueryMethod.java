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

import java.util.Optional;

import br.com.objectos.code.AccessInfo;
import br.com.objectos.code.MethodInfo;
import br.com.objectos.code.ModifierInfo;
import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.pojo.Pojo;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class QueryMethod {

  private QueryMethod() {
  }

  public static <T> Optional<T> of(MethodInfo methodInfo, Constructor<T> constructor) {
    if (!methodInfo.hasModifierInfo(ModifierInfo.ABSTRACT)) {
      methodInfo.compilationError("@Query method must be abstract");
      return Optional.empty();
    }

    if (methodInfo.hasAccessInfo(AccessInfo.PRIVATE)) {
      methodInfo.compilationError("@Query must not be private");
      return Optional.empty();
    }

    SimpleTypeInfo returnTypeInfo = methodInfo.returnTypeInfo();
    QueryReturnType returnType = QueryReturnType.of(returnTypeInfo);
    TypeInfo pojoTypeInfo = returnType.pojoTypeInfo(returnTypeInfo);

    if (!pojoTypeInfo.hasAnnotation(Pojo.class)) {
      methodInfo.compilationError("@Query must return a List of, an Optional of or an instance of a @Pojo.");
      return Optional.empty();
    }

    T method = constructor.apply(methodInfo, returnType, pojoTypeInfo);
    return Optional.of(method);
  }

  @FunctionalInterface
  public static interface Constructor<T> {
    T apply(MethodInfo methodInfo, QueryReturnType returnType, TypeInfo pojoTypeInfo);
  }

}