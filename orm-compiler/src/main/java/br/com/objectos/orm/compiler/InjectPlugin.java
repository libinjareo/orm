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

import br.com.objectos.metainf.Services;
import br.com.objectos.orm.Orm;
import br.com.objectos.pojo.plugin.AbstractPlugin;
import br.com.objectos.pojo.plugin.Contribution;
import br.com.objectos.pojo.plugin.Plugin;
import br.com.objectos.pojo.plugin.PojoAction;
import br.com.objectos.pojo.plugin.PojoInfo;

import com.squareup.javapoet.ClassName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Services(Plugin.class)
public class InjectPlugin extends AbstractPlugin implements PojoAction {

  @Override
  protected void configure() {
    when(pojo((info) -> OrmPojoInfo.of(info).isPresent())).execute(this);
  }

  @Override
  public Contribution execute(PojoInfo pojoInfo) {
    OrmPojoInfo ormPojoInfo = OrmPojoInfo.of(pojoInfo).get();
    return execute0(ormPojoInfo);
  }

  private Contribution execute0(OrmPojoInfo pojoInfo) {
    return Contribution.builder()
        .addCustomField(ClassName.get(Orm.class), "orm")
        .build();
  }

}