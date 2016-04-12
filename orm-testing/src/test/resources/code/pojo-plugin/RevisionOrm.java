package br.com.objectos.pojo.plugin;

import br.com.objectos.way.db.NoResultFoundException;
import br.com.objectos.way.db.SqlRuntimeException;
import br.com.objectos.way.db.Transaction;
import br.com.objectos.way.orm.Orm;
import br.com.objectos.way.schema.it.REVISION;
import br.com.objectos.way.sql.InsertableRow3;
import br.com.objectos.way.sql.Row3;
import br.com.objectos.way.sql.Sql;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.way.orm.compiler.CompanionTypePlugin")
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
    REVISION REVISION = br.com.objectos.way.schema.it.REVISION.get();
    InsertableRow3.Values<REVISION.REVISION_SEQ, REVISION.REVISION_DATE, REVISION.REVISION_DESCRIPTION> insert;
    insert = pojo.bindInsertableRow(Sql
        .insertInto(REVISION)
        .$(REVISION.SEQ(), REVISION.DATE(), REVISION.DESCRIPTION()));
    while(iterator.hasNext()) {
      pojo = (RevisionPojo) iterator.next();
      insert = pojo.bindInsertableRow(insert);
    }
    orm.executeUnchecked(insert);
  }

  public Revision find(REVISION.REVISION_SEQ pk0) {
    return maybe(pk0).orElseThrow(NoResultFoundException::new);
  }

  public Optional<Revision> maybe(REVISION.REVISION_SEQ pk0) {
    try (Transaction trx = orm.startTransaction()) {
      REVISION REVISION = br.com.objectos.way.schema.it.REVISION.get();
      return Sql.select(REVISION.SEQ(), REVISION.DATE(), REVISION.DESCRIPTION())
          .from(REVISION)
          .where(pk0)
          .compile(trx.dialect())
          .findFirst(trx)
          .map(RevisionOrm.get(orm)::load);
    } catch (Exception e) {
      throw new SqlRuntimeException(e);
    }
  }

  public Revision load(Row3<REVISION.REVISION_SEQ, REVISION.REVISION_DATE, REVISION.REVISION_DESCRIPTION> row) {
    return new RevisionPojo(orm, row);
  }
}
