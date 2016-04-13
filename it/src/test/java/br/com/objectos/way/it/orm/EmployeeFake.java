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

import br.com.objectos.way.it.orm.Employee;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class EmployeeFake {

  public static final Employee HOMER_SIMPSON = Employee.builder(OrmFake.INSTANCE)
      .empNo(1)
      .birthDate(LocalDate.of(1970, 1, 1))
      .firstName("Homer")
      .lastName("Simpson")
      .hireDate(LocalDate.of(2010, 1, 1))
      .build();

  private EmployeeFake() {
  }

}