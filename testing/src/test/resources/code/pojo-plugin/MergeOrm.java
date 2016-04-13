package br.com.objectos.pojo.plugin;

import br.com.objectos.db.NoResultFoundException;
import br.com.objectos.db.SqlRuntimeException;
import br.com.objectos.db.Transaction;
import br.com.objectos.schema.it.MERGE;
import br.com.objectos.sql.Row1;
import br.com.objectos.sql.Row3;
import br.com.objectos.sql.Sql;
import br.com.objectos.way.orm.Orm;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.way.orm.compiler.CompanionTypePlugin")
public final class MergeOrm {
  private final Orm orm;

  @Inject
  MergeOrm(Orm orm) {
    this.orm = orm;
  }

  public static MergeOrm get(Orm orm) {
    Objects.requireNonNull(orm);
    return new MergeOrm(orm);
  }

  public Merge find(MERGE.MERGE_SEQ pk0) {
    return maybe(pk0).orElseThrow(NoResultFoundException::new);
  }

  public Optional<Merge> maybe(MERGE.MERGE_SEQ pk0) {
    try (Transaction trx = orm.startTransaction()) {
      MERGE MERGE = br.com.objectos.schema.it.MERGE.get();
      return Sql.select(MERGE.SEQ(), MERGE.PARENT_A(), MERGE.PARENT_B())
          .from(MERGE)
          .where(pk0)
          .compile(trx.dialect())
          .findFirst(trx)
          .map(MergeOrm.get(orm)::load);
    } catch (Exception e) {
      throw new SqlRuntimeException(e);
    }
  }

  public Merge load(Revision parentA, Optional<Revision> parentB, Row1<MERGE.MERGE_SEQ> row) {
    return new MergePojo(orm, parentA, parentB, row);
  }

  public Merge load(Row3<MERGE.MERGE_SEQ, MERGE.MERGE_PARENT_A, MERGE.MERGE_PARENT_B> row) {
    return new MergePojo(orm, row);
  }
}
