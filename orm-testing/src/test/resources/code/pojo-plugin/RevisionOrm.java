package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import br.com.objectos.schema.it.REVISION;
import br.com.objectos.sql.query.InsertableRow2;
import br.com.objectos.sql.query.Row3;
import br.com.objectos.sql.query.Sql;
import java.util.Iterator;
import java.util.Objects;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.orm.compiler.CompanionTypePlugin")
public final class RevisionOrm {
  private final Orm orm;

  @Inject
  RevisionOrm(Orm orm) {
    this.orm = orm;
  }

  public static RevisionOrm get(Orm orm) {
    Objects.requireNonNull(orm);
    return new RevisionOrm(orm);
  }

  public void insertAll(Iterable<Revision> entities) {
    Iterator<Revision> iterator = entities.iterator();
    if (!iterator.hasNext()) {
      return;
    }
    RevisionPojo pojo = (RevisionPojo) iterator.next();
    REVISION REVISION = br.com.objectos.schema.it.REVISION.get();
    InsertableRow2.Values<REVISION.REVISION_DATE, REVISION.REVISION_DESCRIPTION> insert;
    insert = pojo.bindInsertableRow(Sql
        .insertInto(REVISION)
        .$(REVISION.DATE(), REVISION.DESCRIPTION()));
    while(iterator.hasNext()) {
      pojo = (RevisionPojo) iterator.next();
      insert = pojo.bindInsertableRow(insert);
    }
    orm.executeUnchecked(insert);
  }

  public Revision load(Row3<REVISION.REVISION_SEQ, REVISION.REVISION_DATE, REVISION.REVISION_DESCRIPTION> row) {
    return new RevisionPojo(orm, row);
  }
}