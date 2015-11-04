package br.com.objectos.pojo.plugin;

import java.time.LocalDate;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.pojo.compiler.PojoCompiler",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction"
})
final class RevisionBuilderPojo implements RevisionBuilder, RevisionBuilder.RevisionBuilderSeq, RevisionBuilder.RevisionBuilderDate, RevisionBuilder.RevisionBuilderDescription {
  private int seq;

  private LocalDate date;

  private String description;

  public RevisionBuilderPojo() {
  }

  @Override
  public Revision build() {
    return new RevisionPojo(this);
  }

  @Override
  public RevisionBuilder.RevisionBuilderSeq seq(int seq) {
    this.seq = seq;
    return this;
  }

  int ___get___seq() {
    return seq;
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