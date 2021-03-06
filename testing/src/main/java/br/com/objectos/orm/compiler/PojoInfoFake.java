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

import br.com.objectos.code.TypeInfo;
import br.com.objectos.pojo.plugin.PojoInfo;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class PojoInfoFake {

  public static final PojoInfo Employee = of(TypeInfoFake.Employee);
  public static final PojoInfo Pair = of(TypeInfoFake.Pair);
  public static final PojoInfo Revision = of(TypeInfoFake.Revision);
  public static final PojoInfo Salary = of(TypeInfoFake.Salary);
  public static final PojoInfo SalaryOpt = of(TypeInfoFake.SalaryOpt);

  private PojoInfoFake() {
  }

  private static PojoInfo of(TypeInfo typeInfo) {
    return PojoInfo.of(typeInfo);
  }

}