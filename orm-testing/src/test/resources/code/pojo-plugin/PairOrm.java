package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import br.com.objectos.schema.it.PAIR;
import br.com.objectos.sql.query.InsertableRow2;
import br.com.objectos.sql.query.Row2;
import br.com.objectos.sql.query.Sql;
import java.util.Iterator;
import java.util.Objects;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.orm.compiler.CompanionTypePlugin")
public final class PairOrm {
  private final Orm orm;

  @Inject
  PairOrm(Orm orm) {
    this.orm = orm;
  }

  public static PairOrm get(Orm orm) {
    Objects.requireNonNull(orm);
    return new PairOrm(orm);
  }

  public void insertAll(Iterable<Pair> entities) {
    Iterator<Pair> iterator = entities.iterator();
    if (!iterator.hasNext()) {
      return;
    }
    PairPojo pojo = (PairPojo) iterator.next();
    PAIR PAIR = br.com.objectos.schema.it.PAIR.get();
    InsertableRow2.Values<PAIR.PAIR_ID, PAIR.PAIR_NAME> insert;
    insert = pojo.bindInsertableRow(Sql
        .insertInto(PAIR)
        .$(PAIR.ID(), PAIR.NAME()));
    while(iterator.hasNext()) {
      pojo = (PairPojo) iterator.next();
      insert = pojo.bindInsertableRow(insert);
    }
    orm.executeUnchecked(insert);
  }

  public Pair load(Row2<PAIR.PAIR_ID, PAIR.PAIR_NAME> row) {
    return new PairPojo(orm, row);
  }
}