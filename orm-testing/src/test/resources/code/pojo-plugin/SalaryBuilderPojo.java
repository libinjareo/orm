package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.compiler.SuperOrm;
import java.time.LocalDate;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.pojo.compiler.PojoCompiler",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction"
})
final class SalaryBuilderPojo implements SalaryBuilder, SalaryBuilder.SalaryBuilderEmployee, SalaryBuilder.SalaryBuilderSalary, SalaryBuilder.SalaryBuilderFromDate, SalaryBuilder.SalaryBuilderToDate {
  private SuperOrm ___constructor0___orm;

  private Employee employee;

  private int salary;

  private LocalDate fromDate;

  private LocalDate toDate;

  public SalaryBuilderPojo(SuperOrm orm) {
    if (orm == null) {
      throw new NullPointerException();
    }
    ___constructor0___orm = orm;
  }

  @Override
  public Salary build() {
    return new SalaryPojo(___constructor0___orm, this);
  }

  @Override
  public SalaryBuilder.SalaryBuilderEmployee employee(Employee employee) {
    if (employee == null) {
      throw new NullPointerException();
    }
    this.employee = employee;
    return this;
  }

  Employee ___get___employee() {
    return employee;
  }

  @Override
  public SalaryBuilder.SalaryBuilderSalary salary(int salary) {
    this.salary = salary;
    return this;
  }

  int ___get___salary() {
    return salary;
  }

  @Override
  public SalaryBuilder.SalaryBuilderFromDate fromDate(LocalDate fromDate) {
    if (fromDate == null) {
      throw new NullPointerException();
    }
    this.fromDate = fromDate;
    return this;
  }

  LocalDate ___get___fromDate() {
    return fromDate;
  }

  @Override
  public SalaryBuilder.SalaryBuilderToDate toDate(LocalDate toDate) {
    if (toDate == null) {
      throw new NullPointerException();
    }
    this.toDate = toDate;
    return this;
  }

  LocalDate ___get___toDate() {
    return toDate;
  }
}