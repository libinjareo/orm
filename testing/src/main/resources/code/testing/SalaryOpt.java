package br.com.objectos.schema.it;

import java.time.LocalDate;
import java.util.Optional;

import br.com.objectos.pojo.Pojo;
import br.com.objectos.code.Testing;
import br.com.objectos.way.orm.compiler.SuperOrm;
import br.com.objectos.schema.it.SALARY;

@Pojo
@Testing
abstract class SalaryOpt {

  abstract SuperOrm orm();

  @SALARY.SALARY_EMP_NO_FK
  abstract Optional<Employee> employee();

  @SALARY.SALARY_
  abstract int salary();

  @SALARY.FROM_DATE
  abstract LocalDate fromDate();

  SalaryOpt() {
  }

}