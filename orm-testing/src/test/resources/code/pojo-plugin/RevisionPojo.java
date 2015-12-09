package br.com.objectos.pojo.plugin;

import br.com.objectos.db.query.Result;
import br.com.objectos.orm.InsertableRowBinder;
import br.com.objectos.orm.Orm;
import br.com.objectos.schema.it.REVISION;
import br.com.objectos.sql.query.InsertableRow3;
import br.com.objectos.sql.query.Row3;
import br.com.objectos.way.relational.Insert;
import java.time.LocalDate;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.ColumnOrmPropertyPlugin",
    "br.com.objectos.orm.compiler.ConstructorPlugin",
    "br.com.objectos.orm.compiler.InjectPlugin",
    "br.com.objectos.orm.compiler.InsertablePlugin",
    "br.com.objectos.orm.compiler.RelationalInsertablePlugin",
    "br.com.objectos.pojo.compiler.PojoCompiler"
})
final class RevisionPojo extends Revision implements InsertableRowBinder<InsertableRow3<REVISION.REVISION_SEQ, REVISION.REVISION_DATE, REVISION.REVISION_DESCRIPTION>, InsertableRow3.Values<REVISION.REVISION_SEQ, REVISION.REVISION_DATE, REVISION.REVISION_DESCRIPTION>> {
  final Orm orm;

  private final REVISION.REVISION_SEQ seq;

  private final REVISION.REVISION_DATE date;

  private final REVISION.REVISION_DESCRIPTION description;

  public RevisionPojo(Orm orm, Row3<REVISION.REVISION_SEQ, REVISION.REVISION_DATE, REVISION.REVISION_DESCRIPTION> row) {
    this(orm, row.column1(), row.column2(), row.column3());
  }

  public RevisionPojo(Orm orm, REVISION.REVISION_SEQ seq, REVISION.REVISION_DATE date, REVISION.REVISION_DESCRIPTION description) {
    super();
    this.orm = orm;
    this.seq = seq;
    this.date = date;
    this.description = description;
  }

  public RevisionPojo(Orm orm, RevisionBuilderPojo builder) {
    super();
    this.orm = orm;
    seq = REVISION.get().SEQ();
    date = REVISION.get().DATE(builder.___get___date());
    description = REVISION.get().DESCRIPTION(builder.___get___description());
  }

  @Override
  public InsertableRow3.Values<REVISION.REVISION_SEQ, REVISION.REVISION_DATE, REVISION.REVISION_DESCRIPTION> bindInsertableRow(InsertableRow3<REVISION.REVISION_SEQ, REVISION.REVISION_DATE, REVISION.REVISION_DESCRIPTION> row) {
    return row.values(seq, date, description).onGeneratedKey(seq);
  }

  @Override
  public Insert getInsert() {
    return Insert.into("OBJECTOS_ORM.REVISION")
        .on(rs -> seq.onGeneratedKey(Result.of(rs)))
        .value("DATE", date.getWrapped())
        .value("DESCRIPTION", description.getWrapped());
  }

  @Override
  int seq() {
    return seq.get();
  }

  @Override
  LocalDate date() {
    return date.get();
  }

  @Override
  String description() {
    return description.get();
  }
}