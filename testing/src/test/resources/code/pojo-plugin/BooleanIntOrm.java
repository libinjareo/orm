package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import br.com.objectos.schema.it.PAIR;
import br.com.objectos.sql.Row2;
import java.util.Objects;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.orm.compiler.CompanionTypePlugin")
public final class BooleanIntOrm {
  private final Orm orm;

  @Inject
  BooleanIntOrm(Orm orm) {
    this.orm = orm;
  }

  public static BooleanIntOrm get(Orm orm) {
    Objects.requireNonNull(orm);
    return new BooleanIntOrm(orm);
  }

  public BooleanInt load(Row2<PAIR.PAIR_ID, PAIR.PAIR_NAME> row) {
    return new BooleanIntPojo(orm, row);
  }
}
