package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import java.util.Objects;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.orm.compiler.CompanionTypePlugin")
public final class EmployeeOrm {
  final Orm orm;

  @Inject
  EmployeeOrm(Orm orm) {
    this.orm = orm;
  }

  public static EmployeeOrm get(Orm orm) {
    Objects.requireNonNull(orm);
    return new EmployeeOrm(orm);
  }
}