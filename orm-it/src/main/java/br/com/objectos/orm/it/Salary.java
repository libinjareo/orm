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

import br.com.objectos.orm.Insertable;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.schema.it.SALARY;
import br.com.objectos.testable.Testable;
import br.com.objectos.way.relational.Loader;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
@Loader
abstract class Salary
    implements
    Insertable,
    br.com.objectos.way.relational.Insertable,
    Testable {

  @SALARY.SALARY_EMP_NO_FK
  abstract Employee employee();

  @SALARY.SALARY_
  abstract int salary();

  @SALARY.FROM_DATE
  abstract LocalDate fromDate();

  @SALARY.TO_DATE
  abstract LocalDate toDate();

  final Model model;

  Salary(Model model) {
    this.model = model;
  }

  public static SalaryBuilder builder(Model model) {
    return new SalaryBuilderPojo(model);
  }

}