package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.compiler.SuperOrm;
import br.com.objectos.schema.it.SALARY;
import br.com.objectos.sql.query.Row3;
import java.util.Objects;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.orm.compiler.CompanionTypePlugin")
public final class SalaryOrm {
  private final SuperOrm orm;

  @Inject
  SalaryOrm(SuperOrm orm) {
    this.orm = orm;
  }

  public static SalaryOrm get(SuperOrm orm) {
    Objects.requireNonNull(orm);
    return new SalaryOrm(orm);
  }

  public Salary load(Employee employee, Row3<SALARY.SALARY_SALARY, SALARY.SALARY_FROM_DATE, SALARY.SALARY_TO_DATE> row) {
    return new SalaryPojo(orm, employee, row);
  }
}