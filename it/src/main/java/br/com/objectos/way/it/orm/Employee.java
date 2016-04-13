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
package br.com.objectos.way.it.orm;

import java.time.LocalDate;
import java.util.List;

import br.com.objectos.way.orm.Query;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.way.it.schema.EMPLOYEE;
import br.com.objectos.testable.NotTestable;
import br.com.objectos.testable.Testable;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class Employee implements Testable {

  @NotTestable
  abstract Model model();

  @EMPLOYEE.EMP_NO
  abstract int empNo();

  @EMPLOYEE.BIRTH_DATE
  abstract LocalDate birthDate();

  @EMPLOYEE.FIRST_NAME
  abstract String firstName();

  @EMPLOYEE.LAST_NAME
  abstract String lastName();

  @EMPLOYEE.HIRE_DATE
  abstract LocalDate hireDate();

  Employee() {
  }

  public static EmployeeBuilder builder(Model model) {
    return new EmployeeBuilderPojo(model);
  }

  @Query
  public abstract List<Salary> salaryList();

}