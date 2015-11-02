package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import java.util.Objects;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.orm.compiler.CompanionTypePlugin")
public final class SalaryOrm {
  final Orm orm;

  @Inject
  SalaryOrm(Orm orm) {
    this.orm = orm;
  }

  public static SalaryOrm get(Orm orm) {
    Objects.requireNonNull(orm);
    return new SalaryOrm(orm);
  }
}