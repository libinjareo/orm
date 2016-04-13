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

import javax.annotation.processing.Processor;

import br.com.objectos.code.AbstractAnnotationProcessor;
import br.com.objectos.code.Artifact;
import br.com.objectos.code.Configuration;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.metainf.Services;
import br.com.objectos.orm.Repo;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Services(Processor.class)
public class RepoCompiler extends AbstractAnnotationProcessor {

  @Override
  protected Configuration configuration() {
    return Configuration.builder()
        .addAnnotationType(Repo.class)
        .addTypeInfoArtifactGenerator(this::generate)
        .build();
  }

  private Artifact generate(TypeInfo typeInfo) {
    return RepoType.of(typeInfo).generate();
  }

}