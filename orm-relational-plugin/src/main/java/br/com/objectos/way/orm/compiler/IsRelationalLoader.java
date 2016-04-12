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

import java.sql.ResultSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;
import javax.lang.model.element.Modifier;

import br.com.objectos.way.code.Artifact;
import br.com.objectos.way.code.ParameterInfo;
import br.com.objectos.way.orm.compiler.ColumnOrmProperty;
import br.com.objectos.way.orm.compiler.ConstructorContext;
import br.com.objectos.way.orm.compiler.ForeignKeyOrmProperty;
import br.com.objectos.way.orm.compiler.OrmInject;
import br.com.objectos.way.orm.compiler.OrmPojoInfo;
import br.com.objectos.way.orm.compiler.OrmPropertyAdapter;
import br.com.objectos.way.pojo.plugin.Naming;
import br.com.objectos.way.relational.ResultSetLoader;
import br.com.objectos.way.relational.ResultSetWrapper;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class IsRelationalLoader
    implements
    RelationalLoader,
    OrmPropertyAdapter<Stream<MethodSpec>> {

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

  @Override
  public Stream<MethodSpec> onColumn(ColumnOrmProperty property) {
    return ColumnIsRelationalLoaderProperty.of(property).execute();
  }

  @Override
  public Stream<MethodSpec> onForeignKey(ForeignKeyOrmProperty property) {
    return ForeignKeyIsRelationalLoaderProperty.of(property).execute();
  }

  private TypeSpec type() {
    ClassName superClass = naming.superClass();
    return TypeSpec.classBuilder("Abstract" + superClass.simpleName() + "Loader")
        .addAnnotation(GENERATED)
        .addModifiers(Modifier.ABSTRACT)
        .addSuperinterface(ParameterizedTypeName.get(ClassName.get(ResultSetLoader.class), superClass))
        .addField(inject.fieldSpec())
        .addMethods(pojoInfo.constructorContextList()
            .stream()
            .map(IsRelationalLoaderConstructor::of)
            .map(IsRelationalLoaderConstructor::execute)
            .collect(Collectors.toList()))
        .addMethod(load())
        .addMethods(pojoInfo.propertyList()
            .stream()
            .flatMap(property -> property.adapt(this))
            .collect(Collectors.toList()))
        .build();
  }

  private MethodSpec load() {
    CodeBlock.Builder body = CodeBlock.builder()
        .addStatement("$1T rs = new $1T($2S, resultSet)", ResultSetWrapper.class, tableSimpleName())
        .add("return new $T(", naming.pojo())
        .add("\n    $L", inject.name());

    pojoInfo.constructorContextList()
        .stream()
        .flatMap(ConstructorContext::parameterInfoStream)
        .map(ParameterInfo::name)
        .forEach(name -> body.add(",\n    $L", name));

    pojoInfo.propertyList()
        .stream()
        .sorted()
        .map(property -> property.adapt(IsRelationalLoaderLoadMethod.INSTANCE))
        .forEach(body::add);

    body.add(");\n");

    return MethodSpec.methodBuilder("load")
        .addAnnotation(Override.class)
        .addModifiers(Modifier.PUBLIC)
        .addParameter(ResultSet.class, "resultSet")
        .returns(naming.superClass())
        .addCode(body.build())
        .build();
  }

  private String tableSimpleName() {
    return pojoInfo.tableInfoMap().onFirstEntry((info, list) -> info.tableSimpleName());
  }

}