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

import static br.com.objectos.assertion.BooleanAssertion.assertThat;

import br.com.objectos.orm.compiler.TableInfoMap.Builder;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class TableInfoMapTest {

  @DataProvider
  public Object[][] containsPrimaryKeyProvider() {
    return new Object[][] {
      { mapOf(OrmPropertyFake.Pair_id, OrmPropertyFake.Pair_name), false },
      { mapOf(OrmPropertyFake.Employee_empNo), true },
      { mapOf(OrmPropertyFake.Salary_employee), false },
      { mapOf(OrmPropertyFake.Salary_employee, OrmPropertyFake.Salary_fromDate), true }
    };
  }

  @Test(dataProvider = "containsPrimaryKeyProvider")
  public void containsPrimaryKey(TableInfoMap map, boolean expected) {
    assertThat(map.containsPrimaryKey()).is(expected);
  }

  private TableInfoMap mapOf(OrmProperty... properties) {
    Builder builder = TableInfoMap.builder();
    for (OrmProperty property : properties) {
      builder.add(property);
    }
    return builder.build();
  }

}