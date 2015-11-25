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
import br.com.objectos.db.core.SqlRuntimeException;
import br.com.objectos.db.core.Transaction;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.pojo.plugin.Naming;
import br.com.objectos.pojo.plugin.PojoInfo;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
abstract class QueryMethod {

  private final MethodInfo methodInfo;
  private final QueryReturnType returnType;
  private final TypeInfo pojoTypeInfo;

  QueryMethod(MethodInfo methodInfo, QueryReturnType returnType, TypeInfo pojoTypeInfo) {
    this.methodInfo = methodInfo;
    this.returnType = returnType;
    this.pojoTypeInfo = pojoTypeInfo;
  }

  public static Optional<QueryMethod> ofRepo(MethodInfo methodInfo) {
    return of(methodInfo, RepoQueryMethod::new);
  }

  private static Optional<QueryMethod> of(MethodInfo methodInfo, Constructor constructor) {
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

    QueryMethod res = constructor.apply(methodInfo, returnType, pojoTypeInfo);
    return Optional.of(res);
  }

  public final void accept(RepoTypeSpecBuilder builder) {
    Optional<OrmPojoInfo> maybePojoInfo = pojoInfo();
    if (!maybePojoInfo.isPresent()) {
      return;
    }

    OrmPojoInfo pojoInfo = maybePojoInfo.get();
    builder
        .addInject(pojoInfo.inject())
        .addMethod(method(pojoInfo));
  }

  abstract CodeBlock collectCode(OrmPojoInfo pojoInfo);

  abstract CodeBlock selectFrom(OrmPojoInfo pojoInfo);

  private CodeBlock body(OrmPojoInfo pojoInfo) {
    OrmInject inject = pojoInfo.inject();
    return CodeBlock.builder()
        .beginControlFlow("try ($T trx = $L.startTransaction())", Transaction.class, inject.name())
        .add(selectFrom(pojoInfo))
        .add(OrderByInfo.of(methodInfo).get())
        .add(returnType.collect(collectCode(pojoInfo)))
        .nextControlFlow("catch ($T e)", Exception.class)
        .addStatement("throw new $T(e)", SqlRuntimeException.class)
        .endControlFlow()
        .build();
  }

  private MethodSpec method(OrmPojoInfo pojoInfo) {
    return methodInfo.overrideWriter()
        .addCode(body(pojoInfo))
        .write();
  }

  private Optional<OrmPojoInfo> pojoInfo() {
    PojoInfo pojoInfo = PojoInfo.of(pojoTypeInfo);
    return OrmPojoInfo.of(pojoInfo);
  }

  @FunctionalInterface
  private static interface Constructor {
    QueryMethod apply(MethodInfo methodInfo, QueryReturnType returnType, TypeInfo pojoTypeInfo);
  }

  private static class RepoQueryMethod extends QueryMethod {

    public RepoQueryMethod(MethodInfo methodInfo, QueryReturnType returnType, TypeInfo pojoTypeInfo) {
      super(methodInfo, returnType, pojoTypeInfo);
    }

    @Override
    CodeBlock collectCode(OrmPojoInfo pojoInfo) {
      Naming naming = pojoInfo.naming();
      OrmInject inject = pojoInfo.inject();
      return CodeBlock.builder()
          .add("$T.get($L)::load", naming.superClassSuffix("Orm"), inject.name())
          .build();
    }

    @Override
    CodeBlock selectFrom(OrmPojoInfo pojoInfo) {
      return pojoInfo.tableInfoMap().selectFrom();
    }

  }

}