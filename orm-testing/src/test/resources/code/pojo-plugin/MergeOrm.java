package br.com.objectos.pojo.plugin;

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

  public Merge load(Revision parentA, Optional<Revision> parentB, Row1<MERGE.MERGE_SEQ> row) {
    return new MergePojo(orm, parentA, parentB, row);
  }
}