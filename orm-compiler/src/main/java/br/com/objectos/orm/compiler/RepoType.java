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

import java.util.stream.Stream;

import javax.annotation.Generated;
import javax.inject.Inject;
import javax.lang.model.element.Modifier;

import br.com.objectos.code.Artifact;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.testable.Testable;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class RepoType implements Testable {

  private static final AnnotationSpec GENERATED = AnnotationSpec.builder(Generated.class)
      .addMember("value", "$S", RepoCompiler.class.getName())
      .build();

  abstract TypeName superTypeName();
  abstract ClassName repoClassName();

  RepoType() {
  }

  public static RepoType of(TypeInfo typeInfo) {
    return RepoType.builder()
        .superTypeName(typeInfo.typeName())
        .repoClassName(typeInfo.classNameSuffix("Repo"))
        .build();
  }

  private static RepoTypeBuilder builder() {
    return new RepoTypeBuilderPojo();
  }

  public Stream<Artifact> generate() {
    JavaFile file = JavaFile.builder(repoClassName().packageName(), type())
        .skipJavaLangImports(true)
        .build();
    Artifact artifact = Artifact.of(file);
    return Stream.of(artifact);
  }

  private TypeSpec type() {
    return TypeSpec.classBuilder(repoClassName().simpleName())
        .addAnnotation(GENERATED)
        .addModifiers(Modifier.FINAL)
        .superclass(superTypeName())
        .addMethod(constructor())
        .build();
  }

  private MethodSpec constructor() {
    MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
        .addAnnotation(Inject.class);

    return constructor.build();
  }

}