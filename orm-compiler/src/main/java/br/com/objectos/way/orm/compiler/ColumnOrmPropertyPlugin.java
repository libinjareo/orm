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

import br.com.objectos.way.metainf.Services;
import br.com.objectos.way.pojo.plugin.AbstractPlugin;
import br.com.objectos.way.pojo.plugin.Plugin;
import br.com.objectos.way.pojo.plugin.PojoProperty;
import br.com.objectos.way.pojo.plugin.PojoPropertyAction;
import br.com.objectos.way.pojo.plugin.Property;
import br.com.objectos.way.schema.meta.ColumnAnnotation;
import br.com.objectos.way.schema.meta.GeneratedValue;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Services(Plugin.class)
public class ColumnOrmPropertyPlugin extends AbstractPlugin implements PojoPropertyAction {

  @Override
  protected void configure() {
    when(property(hasAnnotationAnnotatedWith(ColumnAnnotation.class)))
        .execute(this);

    when(property(hasAnnotationAnnotatedWith(GeneratedValue.class)))
        .execute(ColumnPropertyBuilderPropertyAction.INSTANCE);
  }

  @Override
  public PojoProperty execute(Property property) {
    return ColumnOrmProperty.get(property).executePojoProperty();
  }

  @Override
  public void onConfigure() {
    Compiler.invalidate();
  }

  @Override
  public void onStart() {
    Compiler.invalidate();
  }

}