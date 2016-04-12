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

import br.com.objectos.way.pojo.plugin.BuilderProperty;
import br.com.objectos.way.pojo.plugin.BuilderPropertyAction;
import br.com.objectos.way.pojo.plugin.Property;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
enum ColumnPropertyBuilderPropertyAction implements BuilderPropertyAction {

  INSTANCE;

  @Override
  public BuilderProperty execute(Property property) {
    return BuilderProperty.ignore();
  }

}