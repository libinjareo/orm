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

import java.util.Objects;

import javax.annotation.Generated;
import javax.inject.Inject;
import javax.lang.model.element.Modifier;

import br.com.objectos.code.Artifact;
import br.com.objectos.orm.Orm;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.pojo.plugin.Naming;
import br.com.objectos.testable.Testable;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class CompanionType implements Testable {

  private static final MethodSpec CONSTRUCTOR = MethodSpec.constructorBuilder()
      .addAnnotation(Inject.class)
      .addParameter(Orm.class, "orm")
      .addStatement("this.orm = orm")
      .build();
  private static final FieldSpec FIELD_ORM = FieldSpec.builder(Orm.class, "orm")
      .addModifiers(Modifier.FINAL)
      .build();
  private static final AnnotationSpec GENERATED = AnnotationSpec.builder(Generated.class)
      .addMember("value", "$S", CompanionTypePlugin.class.getName())
      .build();

  abstract ClassName companionTypeClassName();

  abstract TypeName superClassTypeName();
  abstract TypeName pojoTypeName();

  abstract OrmInsertable insertable();

  CompanionType() {
  }

  public static CompanionType of(OrmPojoInfo pojoInfo) {
    Naming naming = pojoInfo.naming();
    ClassName companionTypeClassName = naming.superClassSuffix("Orm");
    ClassName superClassName = naming.superClass();
    ClassName pojoClassName = naming.pojo();
    return CompanionType.builder()
        .companionTypeClassName(companionTypeClassName)
        .superClassTypeName(naming.typeVariableNameRawListTo(superClassName))
        .pojoTypeName(naming.typeVariableNameRawListTo(pojoClassName))
        .insertable(pojoInfo.insertable())
        .build();
  }

  static CompanionTypeBuilder builder() {
    return new CompanionTypeBuilderPojo();
  }

  public Artifact execute() {
    return Artifact.of(javaFile());
  }

  public MethodSpec insertAll() {
    MethodSpec.Builder insertAll = MethodSpec.methodBuilder("insertAll")
        .addModifiers(Modifier.PUBLIC)
        .addParameter(OrmNaming.iterableOf(superClassTypeName()), "entities")
        .addStatement("$T iterator = entities.iterator()", OrmNaming.iteratorOf(superClassTypeName()))
        .addCode(CodeBlock.builder()
            .beginControlFlow("if (!iterator.hasNext())")
            .addStatement("return")
            .endControlFlow()
            .build())
        .addStatement("$1T pojo = ($1T) iterator.next()", pojoTypeName());

    insertable().acceptInsertAll(insertAll);

    return insertAll
        .addCode(CodeBlock.builder()
            .beginControlFlow("while(iterator.hasNext())")
            .addStatement("pojo = ($T) iterator.next()", pojoTypeName())
            .addStatement("insert = pojo.bindInsertableRow(insert)")
            .endControlFlow()
            .build())
        .addStatement("orm.executeUnchecked(insert)")
        .build();
  }

  private JavaFile javaFile() {
    return JavaFile.builder(packageName(), type())
        .skipJavaLangImports(true)
        .build();
  }

  private String packageName() {
    return companionTypeClassName().packageName();
  }

  private String simpleName() {
    return companionTypeClassName().simpleName();
  }

  private TypeSpec type() {
    TypeSpec.Builder type = TypeSpec.classBuilder(simpleName())
        .addAnnotation(GENERATED)
        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
        .addField(FIELD_ORM)
        .addMethod(CONSTRUCTOR)
        .addMethod(typeStaticFactory());

    insertable().acceptCompanionType(this, type);

    return type.build();
  }

  private MethodSpec typeStaticFactory() {
    return MethodSpec.methodBuilder("get")
        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
        .addParameter(Orm.class, "orm")
        .returns(companionTypeClassName())
        .addStatement("$T.requireNonNull(orm)", Objects.class)
        .addStatement("return new $T(orm)", companionTypeClassName())
        .build();
  }

}