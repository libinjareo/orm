package br.com.objectos.pojo.plugin;

import br.com.objectos.db.query.NoResultFoundException;
import br.com.objectos.orm.Orm;
import br.com.objectos.schema.it.MERGE;
import br.com.objectos.sql.query.Row1;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.orm.compiler.CompanionTypePlugin")
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

  public Merge find(int pk0) {
    return maybe(pk0).orElseThrow(NoResultFoundException::new);
  }

  public Optional<Merge> maybe(int pk0) {
    return Optional.empty();
  }

  public Merge load(Revision parentA, Optional<Revision> parentB, Row1<MERGE.MERGE_SEQ> row) {
    return new MergePojo(orm, parentA, parentB, row);
  }
}