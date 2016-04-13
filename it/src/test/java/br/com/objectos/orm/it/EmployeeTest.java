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
package br.com.objectos.orm.it;

import static br.com.objectos.assertion.ListAssertion.assertThat;

import java.util.List;

import br.com.objectos.orm.it.Salary;

import org.testng.annotations.Test;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class EmployeeTest extends AbstractOrmTest {

  @Test
  public void salaryList() {
    List<Salary> res = EmployeeFake.HOMER_SIMPSON.salaryList();
    assertThat(res).isEqualTo(
        SalaryFake.HOMER_SIMPSON_2010,
        SalaryFake.HOMER_SIMPSON_2011,
        SalaryFake.HOMER_SIMPSON_2012);
  }

}