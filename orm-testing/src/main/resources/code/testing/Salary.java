package br.com.objectos.way.schema.it;

import java.time.LocalDate;

import br.com.objectos.way.code.Testing;
import br.com.objectos.way.orm.compiler.SuperOrm;
import br.com.objectos.way.pojo.Pojo;
import br.com.objectos.way.schema.it.SALARY.FROM_DATE;
import br.com.objectos.way.schema.it.SALARY.SALARY_;
import br.com.objectos.way.schema.it.SALARY.SALARY_EMP_NO_FK;
import br.com.objectos.way.schema.it.SALARY.TO_DATE;

@Pojo
@Testing
abstract class Salary {
  
  abstract SuperOrm orm();

  @SALARY_EMP_NO_FK
  abstract Employee employee();

  @SALARY_
  abstract int salary();

  @FROM_DATE
  abstract LocalDate fromDate();

  @TO_DATE
  abstract LocalDate toDate();

  Salary() {
  }

}