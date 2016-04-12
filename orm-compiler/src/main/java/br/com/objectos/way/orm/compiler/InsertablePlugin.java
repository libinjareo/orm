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

import br.com.objectos.way.metainf.Services;
import br.com.objectos.way.orm.Insertable;
import br.com.objectos.way.pojo.plugin.AbstractPlugin;
import br.com.objectos.way.pojo.plugin.Contribution;
import br.com.objectos.way.pojo.plugin.Plugin;
import br.com.objectos.way.pojo.plugin.PojoAction;
import br.com.objectos.way.pojo.plugin.PojoInfo;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Services(Plugin.class)
public class InsertablePlugin extends AbstractPlugin implements PojoAction {

  @Override
  protected void configure() {
    when(pojo(instanceOf(Insertable.class))).execute(this);
  }

  @Override
  public Contribution execute(PojoInfo pojoInfo) {
    return OrmPojoInfo.of(pojoInfo)
        .map(OrmPojoInfo::insertable)
        .map(OrmInsertable::execute)
        .orElse(Contribution.empty());
  }

}