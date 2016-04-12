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

import java.util.List;

import br.com.objectos.way.assertion.ListAssertion;
import br.com.objectos.way.orm.compiler.ColumnOrmProperty;
import br.com.objectos.way.orm.compiler.OrmPropertyFake;

import org.testng.annotations.Test;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class ForeignKeyOrmPropertyTest {

  @Test
  public void referencedMethodList() {
    List<ColumnOrmProperty> res = OrmPropertyFake.Salary_employee.referencedPropertyList();
    ListAssertion.assertThat(res).isEqualTo(OrmPropertyFake.Employee_empNo);
  }

}