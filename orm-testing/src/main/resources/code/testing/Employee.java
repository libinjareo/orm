package br.com.objectos.way.schema.it;

import java.time.LocalDate;

import br.com.objectos.way.code.Testing;
import br.com.objectos.way.orm.compiler.SuperOrm;
import br.com.objectos.way.pojo.Pojo;
import br.com.objectos.way.schema.it.EMPLOYEE;

@Pojo
@Testing
abstract class Employee {

  abstract SuperOrm orm();
  
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

}