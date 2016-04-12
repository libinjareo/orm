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

import br.com.objectos.way.code.Artifact;
import br.com.objectos.way.metainf.Services;
import br.com.objectos.way.orm.compiler.OrmPojoInfo;
import br.com.objectos.way.pojo.plugin.AbstractPlugin;
import br.com.objectos.way.pojo.plugin.ArtifactAction;
import br.com.objectos.way.pojo.plugin.Plugin;
import br.com.objectos.way.pojo.plugin.PojoInfo;
import br.com.objectos.way.relational.Loader;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Services(Plugin.class)
public class RelationalLoaderPlugin extends AbstractPlugin implements ArtifactAction {

  @Override
  protected void configure() {
    when(pojo(hasAnnotation(Loader.class))).execute(this);
  }

  @Override
  public Artifact execute(PojoInfo pojoInfo) {
    return OrmPojoInfo.of(pojoInfo)
        .map(this::execute0)
        .orElse(Artifact.empty());
  }

  private Artifact execute0(OrmPojoInfo pojoInfo) {
    return IsRelationalLoader.of(pojoInfo).execute();
  }

}