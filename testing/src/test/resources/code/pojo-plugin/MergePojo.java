package br.com.objectos.pojo.plugin;

import br.com.objectos.db.Result;
import br.com.objectos.schema.it.MERGE;
import br.com.objectos.sql.Row1;
import br.com.objectos.sql.Row3;
import br.com.objectos.way.orm.Orm;
import br.com.objectos.way.relational.Insert;
import java.util.Optional;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.pojo.plugin.StandardPojoPropertyAction",
    "br.com.objectos.way.orm.compiler.ColumnOrmPropertyPlugin",
    "br.com.objectos.way.orm.compiler.ConstructorPlugin",
    "br.com.objectos.way.orm.compiler.InjectPlugin",
    "br.com.objectos.way.orm.compiler.RelationalInsertablePlugin"
})
final class MergePojo extends Merge {
  final Orm orm;

  private final MERGE.MERGE_SEQ seq;

  private final Revision parentA;

  private final Optional<Revision> parentB;

  public MergePojo(Orm orm, Revision parentA, Optional<Revision> parentB, Row1<MERGE.MERGE_SEQ> row) {
    this(orm, row.column1(), parentA, parentB);
  }

  public MergePojo(Orm orm, MERGE.MERGE_SEQ seq, Revision parentA, Optional<Revision> parentB) {
    super();
    this.orm = orm;
    this.seq = seq;
    this.parentA = parentA;
    this.parentB = parentB;
  }

  public MergePojo(Orm orm, Row3<MERGE.MERGE_SEQ, MERGE.MERGE_PARENT_A, MERGE.MERGE_PARENT_B> row) {
    this(orm, row.column1(), row.column2(), row.column3());
  }

  public MergePojo(Orm orm, MERGE.MERGE_SEQ seq, MERGE.MERGE_PARENT_A parentA0, MERGE.MERGE_PARENT_B parentB0) {
    this(orm, seq, RevisionOrm.get(orm).find(parentA0.REVISION_SEQ()), RevisionOrm.get(orm).maybe(parentB0.REVISION_SEQ()));
  }

  public MergePojo(Orm orm, MergeBuilderPojo builder) {
    super();
    this.orm = orm;
    seq = MERGE.get().SEQ();
    parentA = builder.___get___parentA();
    parentB = builder.___get___parentB();
  }

  @Override
  public Insert getInsert() {
    return Insert.into("OBJECTOS_ORM.MERGE")
        .on(rs -> seq.onGeneratedKey(Result.of(rs)))
        .value("PARENT_A", parentA.seq())
        .value("PARENT_B", parentB.isPresent() ? parentB.get().seq() : null);
  }

  @Override
  int seq() {
    return seq.get();
  }

  @Override
  Revision parentA() {
    return parentA;
  }

  @Override
  Optional<Revision> parentB() {
    return parentB;
  }
}
