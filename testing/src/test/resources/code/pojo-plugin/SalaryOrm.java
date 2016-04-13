package br.com.objectos.pojo.plugin;

import br.com.objectos.db.NoResultFoundException;
import br.com.objectos.db.SqlRuntimeException;
import br.com.objectos.db.Transaction;
import br.com.objectos.schema.it.SALARY;
import br.com.objectos.sql.InsertableRow4;
import br.com.objectos.sql.Row3;
import br.com.objectos.sql.Row4;
import br.com.objectos.sql.Sql;
import br.com.objectos.way.orm.compiler.SuperOrm;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.way.orm.compiler.CompanionTypePlugin")
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

  public void insertAll(Iterable<Salary> entities) {
    Iterator<Salary> iterator = entities.iterator();
    if (!iterator.hasNext()) {
      return;
    }
    SalaryPojo pojo = (SalaryPojo) iterator.next();
    SALARY SALARY = br.com.objectos.schema.it.SALARY.get();
    InsertableRow4.Values<SALARY.SALARY_EMP_NO, SALARY.SALARY_SALARY, SALARY.SALARY_FROM_DATE, SALARY.SALARY_TO_DATE> insert;
    insert = pojo.bindInsertableRow(Sql
        .insertInto(SALARY)
        .$(SALARY.EMP_NO(), SALARY.SALARY_(), SALARY.FROM_DATE(), SALARY.TO_DATE()));
    while(iterator.hasNext()) {
      pojo = (SalaryPojo) iterator.next();
      insert = pojo.bindInsertableRow(insert);
    }
    orm.executeUnchecked(insert);
  }

  public Salary find(SALARY.SALARY_EMP_NO pk0, SALARY.SALARY_FROM_DATE pk1) {
    return maybe(pk0, pk1).orElseThrow(NoResultFoundException::new);
  }

  public Optional<Salary> maybe(SALARY.SALARY_EMP_NO pk0, SALARY.SALARY_FROM_DATE pk1) {
    try (Transaction trx = orm.startTransaction()) {
      SALARY SALARY = br.com.objectos.schema.it.SALARY.get();
      return Sql.select(SALARY.EMP_NO(), SALARY.SALARY_(), SALARY.FROM_DATE(), SALARY.TO_DATE())
          .from(SALARY)
          .where(pk0)
          .and(pk1)
          .compile(trx.dialect())
          .findFirst(trx)
          .map(SalaryOrm.get(orm)::load);
    } catch (Exception e) {
      throw new SqlRuntimeException(e);
    }
  }

  public Salary load(Employee employee, Row3<SALARY.SALARY_SALARY, SALARY.SALARY_FROM_DATE, SALARY.SALARY_TO_DATE> row) {
    return new SalaryPojo(orm, employee, row);
  }

  public Salary load(Row4<SALARY.SALARY_EMP_NO, SALARY.SALARY_SALARY, SALARY.SALARY_FROM_DATE, SALARY.SALARY_TO_DATE> row) {
    return new SalaryPojo(orm, row);
  }
}
