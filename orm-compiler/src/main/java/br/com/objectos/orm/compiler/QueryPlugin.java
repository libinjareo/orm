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
import br.com.objectos.orm.Query;
import br.com.objectos.pojo.plugin.AbstractPlugin;
import br.com.objectos.pojo.plugin.BuilderProperty;
import br.com.objectos.pojo.plugin.BuilderPropertyAction;
import br.com.objectos.pojo.plugin.Plugin;
import br.com.objectos.pojo.plugin.PojoProperty;
import br.com.objectos.pojo.plugin.PojoPropertyAction;
import br.com.objectos.pojo.plugin.Property;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Services(Plugin.class)
public class QueryPlugin extends AbstractPlugin {

  @Override
  protected void configure() {
    when(property(hasAnnotation(Query.class)))
        .execute(ThisPropertyAction.INSTANCE);

    when(property(hasAnnotation(Query.class)))
        .execute(ThisBuilderPropertyAction.INSTANCE);
  }

  private static enum ThisBuilderPropertyAction implements BuilderPropertyAction {
    INSTANCE;

    @Override
    public BuilderProperty execute(Property property) {
      return BuilderProperty.ignore();
    }
  }

  private static enum ThisPropertyAction implements PojoPropertyAction {
    INSTANCE;

    @Override
    public PojoProperty execute(Property property) {
      return PojoProperty.of(method(property));
    }

    private PojoProperty method(Property property) {
      return PojoProperty.overridingMethodBuilder(property)
          .statement("throw new $T()", UnsupportedOperationException.class)
          .build();
    }
  }

}