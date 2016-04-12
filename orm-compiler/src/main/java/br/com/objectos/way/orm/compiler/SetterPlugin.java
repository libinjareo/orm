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

import br.com.objectos.way.code.MethodInfo;
import br.com.objectos.way.metainf.Services;
import br.com.objectos.way.orm.Setter;
import br.com.objectos.way.pojo.plugin.AbstractPlugin;
import br.com.objectos.way.pojo.plugin.Contribution;
import br.com.objectos.way.pojo.plugin.MethodAction;
import br.com.objectos.way.pojo.plugin.Plugin;
import br.com.objectos.way.pojo.plugin.PojoInfo;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Services(Plugin.class)
public class SetterPlugin extends AbstractPlugin implements MethodAction {

  @Override
  protected void configure() {
    executeWhen(pojo((info) -> OrmPojoInfo.of(info).isPresent()));

    when(method(hasAnnotation(Setter.class))).execute(this);
  }

  @Override
  public Contribution execute(PojoInfo pojoInfo, MethodInfo method) {
    return OrmPojoInfo.of(pojoInfo)
        .map(info -> execute0(info, method))
        .orElse(Contribution.empty());
  }

  private Contribution execute0(OrmPojoInfo pojoInfo, MethodInfo methodInfo) {
    return SetterMethod.of(pojoInfo, methodInfo)
        .map(SetterMethod::get)
        .orElse(Contribution.empty());
  }

}