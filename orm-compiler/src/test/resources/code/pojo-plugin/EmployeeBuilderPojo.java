package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.compiler.SuperOrm;
import java.time.LocalDate;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.InjectPlugin",
    "br.com.objectos.pojo.compiler.PojoCompiler",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction"
})
final class EmployeeBuilderPojo implements EmployeeBuilder, EmployeeBuilder.EmployeeBuilderEmpNo, EmployeeBuilder.EmployeeBuilderBirthDate, EmployeeBuilder.EmployeeBuilderFirstName, EmployeeBuilder.EmployeeBuilderLastName, EmployeeBuilder.EmployeeBuilderHireDate {
  private final SuperOrm orm;

  private int empNo;

  private LocalDate birthDate;

  private String firstName;

  private String lastName;

  private LocalDate hireDate;

  public EmployeeBuilderPojo(SuperOrm orm) {
    this.orm = orm;
  }

  @Override
  public Employee build() {
    return new EmployeePojo(this, orm);
  }

  @Override
  public EmployeeBuilder.EmployeeBuilderEmpNo empNo(int empNo) {
    this.empNo = empNo;
    return this;
  }

  int ___get___empNo() {
    return empNo;
  }

  @Override
  public EmployeeBuilder.EmployeeBuilderBirthDate birthDate(LocalDate birthDate) {
    if (birthDate == null) {
      throw new NullPointerException();
    }
    this.birthDate = birthDate;
    return this;
  }

  LocalDate ___get___birthDate() {
    return birthDate;
  }

  @Override
  public EmployeeBuilder.EmployeeBuilderFirstName firstName(String firstName) {
    if (firstName == null) {
      throw new NullPointerException();
    }
    this.firstName = firstName;
    return this;
  }

  String ___get___firstName() {
    return firstName;
  }

  @Override
  public EmployeeBuilder.EmployeeBuilderLastName lastName(String lastName) {
    if (lastName == null) {
      throw new NullPointerException();
    }
    this.lastName = lastName;
    return this;
  }

  String ___get___lastName() {
    return lastName;
  }

  @Override
  public EmployeeBuilder.EmployeeBuilderHireDate hireDate(LocalDate hireDate) {
    if (hireDate == null) {
      throw new NullPointerException();
    }
    this.hireDate = hireDate;
    return this;
  }

  LocalDate ___get___hireDate() {
    return hireDate;
  }
}