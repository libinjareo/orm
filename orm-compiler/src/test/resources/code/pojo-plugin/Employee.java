package br.com.objectos.pojo.plugin;

import java.time.LocalDate;

import br.com.objectos.orm.compiler.SuperOrm;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.schema.it.EMPLOYEE;

@Pojo
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