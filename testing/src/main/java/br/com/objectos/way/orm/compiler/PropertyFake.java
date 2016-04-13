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

import br.com.objectos.pojo.plugin.PojoInfo;
import br.com.objectos.pojo.plugin.Property;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class PropertyFake {

  public static final Property Employee_empNo = get(PojoInfoFake.Employee, "empNo");
  public static final Property Pair_id = get(PojoInfoFake.Pair, "id");
  public static final Property Pair_name = get(PojoInfoFake.Pair, "name");
  public static final Property Salary_employee = get(PojoInfoFake.Salary, "employee");
  public static final Property Salary_fromDate = get(PojoInfoFake.Salary, "fromDate");
  public static final Property Salary_orm = get(PojoInfoFake.Salary, "orm");
  public static final Property Salary_salary = get(PojoInfoFake.Salary, "salary");
  public static final Property Salary_toDate = get(PojoInfoFake.Salary, "toDate");

  private PropertyFake() {
  }

  private static Property get(PojoInfo pojoInfo, String name) {
    return pojoInfo.propertyStream()
        .filter(p -> p.name().equals(name))
        .findFirst()
        .get();
  }

}