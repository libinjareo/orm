package br.com.objectos.schema.it;

import java.time.LocalDate;

import br.com.objectos.code.Testing;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.schema.it.EMPLOYEE;

@Pojo
@Testing
abstract class Employee {

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