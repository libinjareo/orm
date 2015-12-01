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

import java.util.Optional;

import br.com.objectos.code.AccessInfo;
import br.com.objectos.code.MethodInfo;
import br.com.objectos.code.ModifierInfo;
import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.pojo.plugin.PojoInfo;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class RepoQueryMethod {

  private final MethodInfo methodInfo;
  private final QueryReturnType returnType;
  private final TypeInfo pojoTypeInfo;

  RepoQueryMethod(MethodInfo methodInfo, QueryReturnType returnType, TypeInfo pojoTypeInfo) {
    this.methodInfo = methodInfo;
    this.returnType = returnType;
    this.pojoTypeInfo = pojoTypeInfo;
  }

  public static Optional<RepoQueryMethod> ofRepo(MethodInfo methodInfo) {
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

    RepoQueryMethod res = new RepoQueryMethod(methodInfo, returnType, pojoTypeInfo);
    return Optional.of(res);
  }

  public void accept(RepoTypeSpecBuilder builder) {
    Optional<OrmPojoInfo> maybePojoInfo = pojoInfo();
    if (!maybePojoInfo.isPresent()) {
      return;
    }

    OrmPojoInfo pojoInfo = maybePojoInfo.get();
    builder
        .addInject(pojoInfo.inject())
        .addMethod(method(pojoInfo));
  }

  private MethodSpec method(OrmPojoInfo pojoInfo) {
    Body body = new Body(pojoInfo, returnType);
    return methodInfo.overrideWriter()
        .addCode(body.get())
        .write();
  }

  private Optional<OrmPojoInfo> pojoInfo() {
    PojoInfo pojoInfo = PojoInfo.of(pojoTypeInfo);
    return OrmPojoInfo.of(pojoInfo);
  }

  @FunctionalInterface
  private static interface Constructor {
    RepoQueryMethod apply(MethodInfo methodInfo, QueryReturnType returnType, TypeInfo pojoTypeInfo);
  }

  private class Body extends QueryMethodBody {

    public Body(OrmPojoInfo pojoInfo, QueryReturnType returnType) {
      super(pojoInfo, returnType);
    }

    @Override
    CodeBlock orderBy() {
      return OrderByInfo.of(methodInfo).get();
    }

  }

}