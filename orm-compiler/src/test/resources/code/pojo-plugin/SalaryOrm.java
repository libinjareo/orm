package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.orm.compiler.CompanionTypePlugin")
public final class SalaryOrm {
  private final Orm orm;

  @Inject
  SalaryOrm(Orm orm) {
    this.orm = orm;
  }
}