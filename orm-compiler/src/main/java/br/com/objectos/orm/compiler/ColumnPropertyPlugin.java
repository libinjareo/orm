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

import java.util.Optional;

import br.com.objectos.metainf.Services;
import br.com.objectos.pojo.plugin.AbstractPlugin;
import br.com.objectos.pojo.plugin.Plugin;
import br.com.objectos.pojo.plugin.PojoProperty;
import br.com.objectos.pojo.plugin.PojoPropertyAction;
import br.com.objectos.pojo.plugin.Property;
import br.com.objectos.schema.meta.ColumnAnnotation;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Services(Plugin.class)
public class ColumnPropertyPlugin extends AbstractPlugin {

  @Override
  protected void configure() {
    when(property(hasAnnotationAnnotatedWith(ColumnAnnotation.class)))
        .and(instanceOf(Optional.class))
        .execute(OptionalAction.INSTANCE);

    when(property(hasAnnotationAnnotatedWith(ColumnAnnotation.class)))
        .execute(StandardAction.INSTANCE);
  }

  @Override
  public void onStart() {
    Compiler.invalidate();
  }

  private static enum OptionalAction implements PojoPropertyAction {
    INSTANCE;
    @Override
    public PojoProperty execute(Property property) {
      return OptionalColumnProperty.of(property).execute();
    }
  }

  private static enum StandardAction implements PojoPropertyAction {
    INSTANCE;
    @Override
    public PojoProperty execute(Property property) {
      return StandardColumnProperty.of(property).execute();
    }
  }

}