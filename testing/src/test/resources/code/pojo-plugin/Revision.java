package br.com.objectos.pojo.plugin;

import java.time.LocalDate;

import br.com.objectos.orm.Insertable;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.schema.it.REVISION;

@Pojo
abstract class Revision
    implements
    Insertable,
    br.com.objectos.way.relational.Insertable {

  @REVISION.SEQ
  abstract int seq();

  @REVISION.DATE
  abstract LocalDate date();

  @REVISION.DESCRIPTION
  abstract String description();

  Revision() {
  }

}