package br.com.objectos.pojo.plugin;

import br.com.objectos.schema.it.EMPLOYEE;
import java.time.LocalDate;
import javax.annotation.Generated;

@Generated({
  "br.com.objectos.orm.compiler.ColumnPropertyPlugin$StandardAction",
  "br.com.objectos.pojo.compiler.PojoCompiler"
})
final class EmployeePojo extends Employee {
  private final EMPLOYEE.EMPLOYEE_EMP_NO empNo;

  private final EMPLOYEE.EMPLOYEE_BIRTH_DATE birthDate;

  private final EMPLOYEE.EMPLOYEE_FIRST_NAME firstName;

  private final EMPLOYEE.EMPLOYEE_LAST_NAME lastName;

  private final EMPLOYEE.EMPLOYEE_HIRE_DATE hireDate;

  public EmployeePojo(EmployeeBuilderPojo builder) {
    super();
    empNo = EMPLOYEE.get().EMP_NO(builder.___get___empNo());
    birthDate = EMPLOYEE.get().BIRTH_DATE(builder.___get___birthDate());
    firstName = EMPLOYEE.get().FIRST_NAME(builder.___get___firstName());
    lastName = EMPLOYEE.get().LAST_NAME(builder.___get___lastName());
    hireDate = EMPLOYEE.get().HIRE_DATE(builder.___get___hireDate());
  }

  @Override
  int empNo() {
    return empNo.get();
  }

  @Override
  LocalDate birthDate() {
    return birthDate.get();
  }

  @Override
  String firstName() {
    return firstName.get();
  }

  @Override
  String lastName() {
    return lastName.get();
  }

  @Override
  LocalDate hireDate() {
    return hireDate.get();
  }
}
