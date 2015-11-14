package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.compiler.SuperOrm;
import br.com.objectos.schema.it.SALARY;
import java.time.LocalDate;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.ColumnPropertyPlugin$StandardAction",
    "br.com.objectos.orm.compiler.InjectPlugin",
    "br.com.objectos.pojo.compiler.PojoCompiler",
    "br.com.objectos.pojo.plugin.StandardPojoPropertyAction"
})
final class SalaryPojo extends Salary {
  final SuperOrm orm;

  private final Employee employee;

  private final SALARY.SALARY_SALARY salary;

  private final SALARY.SALARY_FROM_DATE fromDate;

  private final SALARY.SALARY_TO_DATE toDate;

  public SalaryPojo(SuperOrm orm, SalaryBuilderPojo builder) {
    super();
    this.orm = orm;
    employee = builder.___get___employee();
    salary = SALARY.get().SALARY_(builder.___get___salary());
    fromDate = SALARY.get().FROM_DATE(builder.___get___fromDate());
    toDate = SALARY.get().TO_DATE(builder.___get___toDate());
  }
  @Override
  SuperOrm orm() {
    return orm;
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