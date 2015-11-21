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

import java.util.stream.Collectors;

import javax.annotation.Generated;
import javax.lang.model.element.Modifier;

import br.com.objectos.code.Artifact;
import br.com.objectos.pojo.plugin.Naming;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class IsRelationalLoader implements RelationalLoader {

  private static final AnnotationSpec GENERATED = AnnotationSpec.builder(Generated.class)
      .addMember("value", "$S", RelationalLoaderPlugin.class.getName())
      .build();

  private final OrmPojoInfo pojoInfo;
  private final OrmInject inject;
  private final Naming naming;

  private IsRelationalLoader(OrmPojoInfo pojoInfo, OrmInject inject, Naming naming) {
    this.pojoInfo = pojoInfo;
    this.inject = inject;
    this.naming = naming;
  }

  public static IsRelationalLoader of(OrmPojoInfo pojoInfo) {
    return new IsRelationalLoader(
        pojoInfo,
        pojoInfo.inject(),
        pojoInfo.naming());
  }

  @Override
  public Artifact execute() {
    return naming.toArtifact(type());
  }

  private TypeSpec type() {
    ClassName superClass = naming.superClass();
    return TypeSpec.classBuilder("Abstract" + superClass.simpleName() + "Loader")
        .addAnnotation(GENERATED)
        .addModifiers(Modifier.ABSTRACT)
        .addField(inject.fieldSpec())
        .addMethods(pojoInfo.constructorContextList()
            .stream()
            .map(IsRelationalLoaderConstructor::of)
            .map(IsRelationalLoaderConstructor::execute)
            .collect(Collectors.toList()))
        .build();
  }

}