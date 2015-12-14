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
package br.com.objectos.orm.it;

import java.time.LocalDate;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class SalaryFake {

  public static final Salary HOMER_SIMPSON_2010 = Salary.builder(OrmFake.INSTANCE)
      .employee(EmployeeFake.HOMER_SIMPSON)
      .salary(1000)
      .fromDate(LocalDate.of(2010, 1, 1))
      .toDate(LocalDate.of(2010, 12, 31))
      .build();
  public static final Salary HOMER_SIMPSON_2011 = Salary.builder(OrmFake.INSTANCE)
      .employee(EmployeeFake.HOMER_SIMPSON)
      .salary(2500)
      .fromDate(LocalDate.of(2011, 1, 1))
      .toDate(LocalDate.of(2011, 12, 31))
      .build();
  public static final Salary HOMER_SIMPSON_2012 = Salary.builder(OrmFake.INSTANCE)
      .employee(EmployeeFake.HOMER_SIMPSON)
      .salary(3000)
      .fromDate(LocalDate.of(2012, 1, 1))
      .toDate(LocalDate.of(2012, 12, 31))
      .build();

  private SalaryFake() {
  }

}