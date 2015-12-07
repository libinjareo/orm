package br.com.objectos.pojo.plugin;

import br.com.objectos.collections.MoreCollectors;
import br.com.objectos.db.core.SqlRuntimeException;
import br.com.objectos.db.core.Transaction;
import br.com.objectos.orm.compiler.SuperOrm;
import br.com.objectos.schema.it.EMPLOYEE;
import br.com.objectos.schema.it.SALARY;
import br.com.objectos.sql.query.Row5;
import br.com.objectos.sql.query.Sql;
import java.time.LocalDate;
import java.util.List;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.ColumnOrmPropertyPlugin",
    "br.com.objectos.orm.compiler.ConstructorPlugin",
    "br.com.objectos.orm.compiler.InjectPlugin",
    "br.com.objectos.orm.compiler.QueryPlugin",
    "br.com.objectos.pojo.compiler.PojoCompiler"
})
final class EmployeePojo extends Employee {
  final SuperOrm orm;

  private final EMPLOYEE.EMPLOYEE_EMP_NO empNo;

  private final EMPLOYEE.EMPLOYEE_BIRTH_DATE birthDate;

  private final EMPLOYEE.EMPLOYEE_FIRST_NAME firstName;

  private final EMPLOYEE.EMPLOYEE_LAST_NAME lastName;

  private final EMPLOYEE.EMPLOYEE_HIRE_DATE hireDate;

  public EmployeePojo(SuperOrm orm, Row5<EMPLOYEE.EMPLOYEE_EMP_NO, EMPLOYEE.EMPLOYEE_BIRTH_DATE, EMPLOYEE.EMPLOYEE_FIRST_NAME, EMPLOYEE.EMPLOYEE_LAST_NAME, EMPLOYEE.EMPLOYEE_HIRE_DATE> row) {
    this(orm, row.column1(), row.column2(), row.column3(), row.column4(), row.column5());
  }

  public EmployeePojo(SuperOrm orm, EMPLOYEE.EMPLOYEE_EMP_NO empNo, EMPLOYEE.EMPLOYEE_BIRTH_DATE birthDate, EMPLOYEE.EMPLOYEE_FIRST_NAME firstName, EMPLOYEE.EMPLOYEE_LAST_NAME lastName, EMPLOYEE.EMPLOYEE_HIRE_DATE hireDate) {
    super();
    this.orm = orm;
    this.empNo = empNo;
    this.birthDate = birthDate;
    this.firstName = firstName;
    this.lastName = lastName;
    this.hireDate = hireDate;
  }

  public EmployeePojo(SuperOrm orm, EmployeeBuilderPojo builder) {
    super();
    this.orm = orm;
    empNo = EMPLOYEE.get().EMP_NO(builder.___get___empNo());
    birthDate = EMPLOYEE.get().BIRTH_DATE(builder.___get___birthDate());
    firstName = EMPLOYEE.get().FIRST_NAME(builder.___get___firstName());
    lastName = EMPLOYEE.get().LAST_NAME(builder.___get___lastName());
    hireDate = EMPLOYEE.get().HIRE_DATE(builder.___get___hireDate());
  }

  @Override
  SuperOrm orm() {
    return orm;
  }

  @Override
  List<Salary> salaryList() {
    try (Transaction trx = orm.startTransaction()) {
      SALARY SALARY = br.com.objectos.schema.it.SALARY.get();
      return Sql.select(SALARY.SALARY_(), SALARY.FROM_DATE(), SALARY.TO_DATE())
          .from(SALARY)
          .compile(trx.dialect())
          .stream(trx)
          .map(row -> SalaryOrm.get(orm).load(EmployeePojo.this, row))
          .collect(MoreCollectors.toImmutableList());
    } catch (Exception e) {
      throw new SqlRuntimeException(e);
    }
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
