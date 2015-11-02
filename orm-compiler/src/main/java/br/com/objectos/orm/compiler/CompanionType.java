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
package br.com.objectos.orm.compiler;

import javax.annotation.Generated;
import javax.lang.model.element.Modifier;

import br.com.objectos.code.Artifact;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.testable.Testable;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class CompanionType implements Testable {

  private static final AnnotationSpec GENERATED = AnnotationSpec.builder(Generated.class)
      .addMember("value", "$S", CompanionTypePlugin.class.getName())
      .build();

  abstract ClassName className();
  abstract OrmInsertable insertable();

  CompanionType() {
  }

  public static CompanionType of(OrmPojoInfo pojoInfo) {
    return CompanionType.builder()
        .className(pojoInfo.classNameSuffix("Orm"))
        .insertable(pojoInfo.insertable())
        .build();
  }

  static CompanionTypeBuilder builder() {
    return new CompanionTypeBuilderPojo();
  }

  public Artifact execute() {
    return Artifact.of(javaFile());
  }

  private JavaFile javaFile() {
    return JavaFile.builder(className().packageName(), type())
        .skipJavaLangImports(true)
        .build();
  }

  private TypeSpec type() {
    return TypeSpec.classBuilder(className().simpleName())
        .addAnnotation(GENERATED)
        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
        .build();
  }

}