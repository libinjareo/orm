package br.com.objectos.pojo.plugin;

import java.time.LocalDate;

import br.com.objectos.pojo.Pojo;
import br.com.objectos.schema.it.SALARY;

@Pojo
abstract class Salary {

  @SALARY.SALARY_EMP_NO_FK
  abstract Employee employee();

  @SALARY.SALARY_
  abstract int salary();

  @SALARY.FROM_DATE
  abstract LocalDate fromDate();

  @SALARY.TO_DATE
  abstract LocalDate toDate();

  Salary() {
  }

}