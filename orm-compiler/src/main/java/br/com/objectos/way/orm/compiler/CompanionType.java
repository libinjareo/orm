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
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.Generated;
import javax.lang.model.element.Modifier;

import br.com.objectos.core.collections.ImmutableSet;
import br.com.objectos.way.code.Artifact;
import br.com.objectos.way.pojo.plugin.Naming;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class CompanionType {

  private static final AnnotationSpec GENERATED = AnnotationSpec.builder(Generated.class)
      .addMember("value", "$S", CompanionTypePlugin.class.getName())
      .build();

  private static final Set<Exe> EXE_SET = ImmutableSet.<Exe> builder()
      .add(CompanionTypeInject::of)
      .add(CompanionTypeFactory::of)
      .add(info -> info.insertable())
      .add(CompanionTypeFind::of)
      .add(CompanionTypeLoad::of)
      .build();

  private final OrmPojoInfo pojoInfo;
  private final ClassName className;

  private final List<FieldSpec> fieldSpecList = new ArrayList<>();
  private final List<MethodSpec> methodSpecList = new ArrayList<>();

  private CompanionType(OrmPojoInfo pojoInfo, ClassName className) {
    this.pojoInfo = pojoInfo;
    this.className = className;
  }

  public static CompanionType of(OrmPojoInfo pojoInfo) {
    Naming naming = pojoInfo.naming();
    ClassName className = naming.superClassSuffix("Orm");
    return new CompanionType(
        pojoInfo,
        className);
  }

  public CompanionType addField(FieldSpec fieldSpec) {
    fieldSpecList.add(fieldSpec);
    return this;
  }

  public CompanionType addMethod(MethodSpec methodSpec) {
    methodSpecList.add(methodSpec);
    return this;
  }

  public CompanionType addMethods(Stream<MethodSpec> methodStream) {
    methodStream.forEach(methodSpecList::add);
    return this;
  }

  public ClassName className() {
    return className;
  }

  public Artifact execute() {
    return Artifact.of(javaFile());
  }

  public OrmInject inject() {
    return pojoInfo.inject();
  }

  public Naming naming() {
    return pojoInfo.naming();
  }

  private JavaFile javaFile() {
    return JavaFile.builder(className.packageName(), type())
        .skipJavaLangImports(true)
        .build();
  }

  private TypeSpec type() {
    EXE_SET.stream()
        .map(exe -> exe.get(pojoInfo))
        .forEach(exe -> exe.acceptCompanionType(this));

    return TypeSpec.classBuilder(className.simpleName())
        .addAnnotation(GENERATED)
        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
        .addFields(fieldSpecList)
        .addMethods(methodSpecList)
        .build();
  }

  @FunctionalInterface
  private static interface Exe {

    CompanionTypeExe get(OrmPojoInfo pojoInfo);

  }

}