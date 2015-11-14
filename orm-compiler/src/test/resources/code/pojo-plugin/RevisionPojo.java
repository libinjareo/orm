package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.InsertableRowBinder;
import br.com.objectos.orm.Orm;
import br.com.objectos.schema.it.REVISION;
import br.com.objectos.sql.query.InsertableRow2;
import java.time.LocalDate;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.ColumnPropertyPlugin$StandardAction",
    "br.com.objectos.orm.compiler.InjectPlugin",
    "br.com.objectos.orm.compiler.InsertablePlugin",
    "br.com.objectos.pojo.compiler.PojoCompiler"
})
final class RevisionPojo extends Revision implements InsertableRowBinder<InsertableRow2<REVISION.REVISION_DATE, REVISION.REVISION_DESCRIPTION>, InsertableRow2.Values<REVISION.REVISION_DATE, REVISION.REVISION_DESCRIPTION>> {
  final Orm orm;

  private final REVISION.REVISION_SEQ seq;

  private final REVISION.REVISION_DATE date;

  private final REVISION.REVISION_DESCRIPTION description;

  public RevisionPojo(Orm orm, RevisionBuilderPojo builder) {
    super();
    this.orm = orm;
    seq = REVISION.get().SEQ();
    date = REVISION.get().DATE(builder.___get___date());
    description = REVISION.get().DESCRIPTION(builder.___get___description());
  }

  @Override
  public InsertableRow2.Values<REVISION.REVISION_DATE, REVISION.REVISION_DESCRIPTION> bindInsertableRow(InsertableRow2<REVISION.REVISION_DATE, REVISION.REVISION_DESCRIPTION> row) {
    return row.values(date, description).onGeneratedKey(seq);
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