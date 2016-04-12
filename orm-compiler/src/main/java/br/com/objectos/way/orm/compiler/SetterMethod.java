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
package br.com.objectos.way.orm.compiler;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.objectos.way.code.AccessInfo;
import br.com.objectos.way.code.MethodInfo;
import br.com.objectos.way.code.ModifierInfo;
import br.com.objectos.way.code.SimpleTypeInfo;
import br.com.objectos.way.pojo.plugin.Contribution;
import br.com.objectos.way.pojo.plugin.Naming;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class SetterMethod {

  private final OrmPojoInfo pojoInfo;
  private final MethodInfo methodInfo;
  private final Map<OrmProperty, SetterParameter> parameterMap;

  private SetterMethod(OrmPojoInfo pojoInfo, MethodInfo methodInfo, Map<OrmProperty, SetterParameter> parameterMap) {
    this.pojoInfo = pojoInfo;
    this.methodInfo = methodInfo;
    this.parameterMap = parameterMap;
  }

  public static Optional<SetterMethod> of(OrmPojoInfo pojoInfo, MethodInfo methodInfo) {
    AccessInfo accessInfo = methodInfo.accessInfo();
    if (AccessInfo.PRIVATE.equals(accessInfo)) {
      methodInfo.compilationError("@Setter method must not be private.");
      return Optional.empty();
    }

    if (!methodInfo.hasModifierInfo(ModifierInfo.ABSTRACT)) {
      methodInfo.compilationError("@Setter method must be abstract.");
      return Optional.empty();
    }

    if (methodInfo.hasParameterInfoListSize(0)) {
      methodInfo.compilationError("@Setter method must have parameters");
      return Optional.empty();
    }

    SimpleTypeInfo returnTypeInfo = methodInfo.returnTypeInfo();
    Naming naming = pojoInfo.naming();
    ClassName superClassName = naming.superClass();
    if (!returnTypeInfo.className().equals(superClassName)) {
      methodInfo.compilationError("@Setter method must return %s", superClassName.simpleName());
      return Optional.empty();
    }

    Map<OrmProperty, SetterParameter> parameterMap = methodInfo.parameterInfoStream()
        .map(param -> SetterParameter.of(pojoInfo, param))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toMap(SetterParameter::referencedProperty, Function.identity()));

    if (!methodInfo.hasParameterInfoListSize(parameterMap.size())) {
      return Optional.empty();
    }

    SetterMethod method = new SetterMethod(
        pojoInfo,
        methodInfo,
        parameterMap);
    return Optional.of(method);
  }

  public Contribution get() {
    return Contribution.builder()
        .addMethod(method())
        .build();
  }

  private CodeBlock body() {
    CodeBlock.Builder body = CodeBlock.builder()
        .add("return new $T(", pojoInfo.naming().pojo())
        .add("\n    $L", pojoInfo.inject().name());

    pojoInfo.propertyList().stream()
        .sorted()
        .forEach(property -> property.acceptSetterMethodBody(body, parameterMap));

    return body
        .addStatement(")")
        .build();
  }

  private MethodSpec method() {
    return methodInfo.overrideWriter()
        .addCode(body())
        .write();
  }

}