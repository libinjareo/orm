package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.InsertableRowBinder;
import br.com.objectos.orm.compiler.SuperOrm;
import br.com.objectos.schema.it.SALARY;
import br.com.objectos.sql.InsertableRow4;
import br.com.objectos.sql.Row3;
import br.com.objectos.sql.Row4;
import br.com.objectos.way.relational.Insert;
import java.time.LocalDate;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.ColumnOrmPropertyPlugin",
    "br.com.objectos.orm.compiler.ConstructorPlugin",
    "br.com.objectos.orm.compiler.InjectPlugin",
    "br.com.objectos.orm.compiler.InsertablePlugin",
    "br.com.objectos.orm.compiler.RelationalInsertablePlugin",
    "br.com.objectos.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.pojo.plugin.StandardPojoPropertyAction"
})
final class SalaryPojo extends Salary implements InsertableRowBinder<InsertableRow4<SALARY.SALARY_EMP_NO, SALARY.SALARY_SALARY, SALARY.SALARY_FROM_DATE, SALARY.SALARY_TO_DATE>, InsertableRow4.Values<SALARY.SALARY_EMP_NO, SALARY.SALARY_SALARY, SALARY.SALARY_FROM_DATE, SALARY.SALARY_TO_DATE>> {
  final SuperOrm orm;

  private final Employee employee;

  private final SALARY.SALARY_SALARY salary;

  private final SALARY.SALARY_FROM_DATE fromDate;

  private final SALARY.SALARY_TO_DATE toDate;

  public SalaryPojo(SuperOrm orm, Employee employee, Row3<SALARY.SALARY_SALARY, SALARY.SALARY_FROM_DATE, SALARY.SALARY_TO_DATE> row) {
    this(orm, employee, row.column1(), row.column2(), row.column3());
  }

  public SalaryPojo(SuperOrm orm, Employee employee, SALARY.SALARY_SALARY salary, SALARY.SALARY_FROM_DATE fromDate, SALARY.SALARY_TO_DATE toDate) {
    super(orm);
    this.orm = orm;
    this.employee = employee;
    this.salary = salary;
    this.fromDate = fromDate;
    this.toDate = toDate;
  }

  public SalaryPojo(SuperOrm orm, Row4<SALARY.SALARY_EMP_NO, SALARY.SALARY_SALARY, SALARY.SALARY_FROM_DATE, SALARY.SALARY_TO_DATE> row) {
    this(orm, row.column1(), row.column2(), row.column3(), row.column4());
  }

  public SalaryPojo(SuperOrm orm, SALARY.SALARY_EMP_NO employee0, SALARY.SALARY_SALARY salary, SALARY.SALARY_FROM_DATE fromDate, SALARY.SALARY_TO_DATE toDate) {
    this(orm, EmployeeOrm.get(orm).find(employee0.EMPLOYEE_EMP_NO()), salary, fromDate, toDate);
  }

  public SalaryPojo(SuperOrm orm, SalaryBuilderPojo builder) {
    super(orm);
    this.orm = orm;
    employee = builder.___get___employee();
    salary = SALARY.get().SALARY_(builder.___get___salary());
    fromDate = SALARY.get().FROM_DATE(builder.___get___fromDate());
    toDate = SALARY.get().TO_DATE(builder.___get___toDate());
  }

  @Override
  public InsertableRow4.Values<SALARY.SALARY_EMP_NO, SALARY.SALARY_SALARY, SALARY.SALARY_FROM_DATE, SALARY.SALARY_TO_DATE> bindInsertableRow(InsertableRow4<SALARY.SALARY_EMP_NO, SALARY.SALARY_SALARY, SALARY.SALARY_FROM_DATE, SALARY.SALARY_TO_DATE> row) {
    return row.values(SALARY.get().EMP_NO(employee.empNo()), salary, fromDate, toDate);
  }

  @Override
  public Insert getInsert() {
    return Insert.into("OBJECTOS_ORM.SALARY")
        .value("EMP_NO", employee.empNo())
        .value("SALARY", salary.getWrapped())
        .value("FROM_DATE", fromDate.getWrapped())
        .value("TO_DATE", toDate.getWrapped());
  }

  @Override
  Employee employee() {
    return employee;
  }

  @Override
  int salary() {
    return salary.get();
  }

  @Override
  LocalDate fromDate() {
    return fromDate.get();
  }

  @Override
  LocalDate toDate() {
    return toDate.get();
  }
}
