package br.com.objectos.pojo.plugin;

import br.com.objectos.schema.it.REVISION;
import java.time.LocalDate;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.ColumnPropertyPlugin$StandardAction",
    "br.com.objectos.pojo.compiler.PojoCompiler"
})
final class RevisionPojo extends Revision {
  private final REVISION.REVISION_SEQ seq;

  private final REVISION.REVISION_DATE date;

  private final REVISION.REVISION_DESCRIPTION description;

  public RevisionPojo(RevisionBuilderPojo builder) {
    super();
    seq = REVISION.get().SEQ(builder.___get___seq());
    date = REVISION.get().DATE(builder.___get___date());
    description = REVISION.get().DESCRIPTION(builder.___get___description());
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
