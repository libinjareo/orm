package br.com.objectos.pojo.plugin;

import br.com.objectos.schema.it.DUO;
import br.com.objectos.sql.Row2;
import br.com.objectos.way.orm.Orm;
import java.util.Objects;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.way.orm.compiler.CompanionTypePlugin")
public final class EnumeratedDuoOrm {
  private final Orm orm;

  @Inject
  EnumeratedDuoOrm(Orm orm) {
    this.orm = orm;
  }

  public static EnumeratedDuoOrm get(Orm orm) {
    Objects.requireNonNull(orm);
    return new EnumeratedDuoOrm(orm);
  }

  public EnumeratedDuo load(Row2<DUO.DUO_ID, DUO.DUO_NAME> row) {
    return new EnumeratedDuoPojo(orm, row);
  }
}
