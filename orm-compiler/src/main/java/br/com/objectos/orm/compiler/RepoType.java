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

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import br.com.objectos.code.Artifact;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.collections.MoreCollectors;
import br.com.objectos.orm.Query;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.testable.Testable;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class RepoType implements Testable {

  abstract TypeName superTypeName();
  abstract ClassName repoClassName();
  abstract List<QueryMethod> queryMethodList();

  RepoType() {
  }

  public static RepoType of(TypeInfo typeInfo) {
    List<QueryMethod> queryMethodList = typeInfo.methodInfoStream()
        .filter(m -> m.hasAnnotation(Query.class))
        .map(QueryMethod::ofRepo)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(MoreCollectors.toImmutableList());
    return RepoType.builder()
        .superTypeName(typeInfo.typeName())
        .repoClassName(typeInfo.classNameSuffix("Repo"))
        .queryMethodList(queryMethodList)
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

  public String simpleName() {
    return repoClassName().simpleName();
  }

  private TypeSpec type() {
    RepoTypeSpecBuilder builder = new RepoTypeSpecBuilder();
    queryMethodList().forEach(method -> method.accept(builder));
    return builder.build(this);
  }

}