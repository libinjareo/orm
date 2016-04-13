package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import br.com.objectos.schema.it.PAIR;
import br.com.objectos.sql.Row2;
import java.util.Objects;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.orm.compiler.CompanionTypePlugin")
public final class EnumeratedOrm {
  private final Orm orm;

  @Inject
  EnumeratedOrm(Orm orm) {
    this.orm = orm;
  }

  public static EnumeratedOrm get(Orm orm) {
    Objects.requireNonNull(orm);
    return new EnumeratedOrm(orm);
  }

  public Enumerated load(Row2<PAIR.PAIR_ID, PAIR.PAIR_NAME> row) {
    return new EnumeratedPojo(orm, row);
  }
}
