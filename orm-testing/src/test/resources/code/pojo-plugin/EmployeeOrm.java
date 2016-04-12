package br.com.objectos.pojo.plugin;

import br.com.objectos.way.db.NoResultFoundException;
import br.com.objectos.way.db.SqlRuntimeException;
import br.com.objectos.way.db.Transaction;
import br.com.objectos.way.orm.compiler.SuperOrm;
import br.com.objectos.way.schema.it.EMPLOYEE;
import br.com.objectos.way.sql.Row5;
import br.com.objectos.way.sql.Sql;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.way.orm.compiler.CompanionTypePlugin")
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

  public Employee find(EMPLOYEE.EMPLOYEE_EMP_NO pk0) {
    return maybe(pk0).orElseThrow(NoResultFoundException::new);
  }

  public Optional<Employee> maybe(EMPLOYEE.EMPLOYEE_EMP_NO pk0) {
    try (Transaction trx = orm.startTransaction()) {
      EMPLOYEE EMPLOYEE = br.com.objectos.way.schema.it.EMPLOYEE.get();
      return Sql.select(EMPLOYEE.EMP_NO(), EMPLOYEE.BIRTH_DATE(), EMPLOYEE.FIRST_NAME(), EMPLOYEE.LAST_NAME(), EMPLOYEE.HIRE_DATE())
          .from(EMPLOYEE)
          .where(pk0)
          .compile(trx.dialect())
          .findFirst(trx)
          .map(EmployeeOrm.get(orm)::load);
    } catch (Exception e) {
      throw new SqlRuntimeException(e);
    }
  }

  public Employee load(Row5<EMPLOYEE.EMPLOYEE_EMP_NO, EMPLOYEE.EMPLOYEE_BIRTH_DATE, EMPLOYEE.EMPLOYEE_FIRST_NAME, EMPLOYEE.EMPLOYEE_LAST_NAME, EMPLOYEE.EMPLOYEE_HIRE_DATE> row) {
    return new EmployeePojo(orm, row);
  }
}
