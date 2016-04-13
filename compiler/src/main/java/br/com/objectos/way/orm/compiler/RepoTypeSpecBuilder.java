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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Generated;
import javax.inject.Inject;
import javax.lang.model.element.Modifier;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class RepoTypeSpecBuilder {

  private static final AnnotationSpec GENERATED = AnnotationSpec.builder(Generated.class)
      .addMember("value", "$S", RepoCompiler.class.getName())
      .build();

  private final Set<OrmInject> injectSet = new HashSet<>();
  private final List<MethodSpec> methodList = new ArrayList<>();

  RepoTypeSpecBuilder() {
  }

  public RepoTypeSpecBuilder addInject(OrmInject inject) {
    injectSet.add(inject);
    return this;
  }

  public RepoTypeSpecBuilder addMethod(MethodSpec method) {
    methodList.add(method);
    return this;
  }

  public TypeSpec build(RepoType repoType) {
    return TypeSpec.classBuilder(repoType.simpleName())
        .addAnnotation(GENERATED)
        .addModifiers(Modifier.FINAL)
        .superclass(repoType.superTypeName())
        .addFields(injectSet.stream()
            .map(OrmInject::fieldSpec)
            .collect(Collectors.toList()))
        .addMethod(constructor())
        .addMethods(methodList)
        .build();
  }

  private MethodSpec constructor() {
    MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
        .addAnnotation(Inject.class);

    injectSet.forEach(inject -> inject.acceptRepoConstructor(constructor));

    return constructor.build();
  }

}