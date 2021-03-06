package br.com.objectos.pojo.plugin;

import java.time.LocalDate;

import br.com.objectos.orm.Insertable;
import br.com.objectos.orm.compiler.SuperOrm;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.schema.it.SALARY;

@Pojo
abstract class Salary 
    implements
    Insertable,
    br.com.objectos.way.relational.Insertable {

  @SALARY.SALARY_EMP_NO_FK
  abstract Employee employee();

  @SALARY.SALARY_
  abstract int salary();

  @SALARY.FROM_DATE
  abstract LocalDate fromDate();

  @SALARY.TO_DATE
  abstract LocalDate toDate();
  
  private final SuperOrm orm;

  Salary(SuperOrm orm) {
    this.orm = orm;
  }

}