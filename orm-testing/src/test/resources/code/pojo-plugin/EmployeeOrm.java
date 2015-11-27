package br.com.objectos.pojo.plugin;

import br.com.objectos.db.query.NoResultFoundException;
import br.com.objectos.orm.compiler.SuperOrm;
import br.com.objectos.schema.it.EMPLOYEE;
import br.com.objectos.sql.query.Row5;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.orm.compiler.CompanionTypePlugin")
public final class EmployeeOrm {
  private final SuperOrm orm;

  @Inject
  EmployeeOrm(SuperOrm orm) {
    this.orm = orm;
  }

  public static EmployeeOrm get(SuperOrm orm) {
    Objects.requireNonNull(orm);
    return new EmployeeOrm(orm);
  }

  public Employee find(int pk0) {
    return maybe(pk0).orElseThrow(NoResultFoundException::new);
  }

  public Optional<Employee> maybe(int pk0) {
    return Optional.empty();
  }

  public Employee load(Row5<EMPLOYEE.EMPLOYEE_EMP_NO, EMPLOYEE.EMPLOYEE_BIRTH_DATE, EMPLOYEE.EMPLOYEE_FIRST_NAME, EMPLOYEE.EMPLOYEE_LAST_NAME, EMPLOYEE.EMPLOYEE_HIRE_DATE> row) {
    return new EmployeePojo(orm, row);
  }
}