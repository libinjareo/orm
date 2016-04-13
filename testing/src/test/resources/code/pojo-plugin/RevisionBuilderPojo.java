package br.com.objectos.pojo.plugin;

import br.com.objectos.way.orm.Orm;
import java.time.LocalDate;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction",
    "br.com.objectos.way.orm.compiler.ColumnPropertyBuilderPropertyAction",
    "br.com.objectos.way.orm.compiler.InjectPlugin"
})
final class RevisionBuilderPojo implements RevisionBuilder, RevisionBuilder.RevisionBuilderDate, RevisionBuilder.RevisionBuilderDescription {
  private final Orm orm;

  private LocalDate date;

  private String description;

  public RevisionBuilderPojo(Orm orm) {
    this.orm = orm;
  }

  @Override
  public Revision build() {
    return new RevisionPojo(orm, this);
  }

  @Override
  public RevisionBuilder.RevisionBuilderDate date(LocalDate date) {
    if (date == null) {
      throw new NullPointerException();
    }
    this.date = date;
    return this;
  }

  LocalDate ___get___date() {
    return date;
  }

  @Override
  public RevisionBuilder.RevisionBuilderDescription description(String description) {
    if (description == null) {
      throw new NullPointerException();
    }
    this.description = description;
    return this;
  }

  String ___get___description() {
    return description;
  }
}
