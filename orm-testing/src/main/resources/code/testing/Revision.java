package br.com.objectos.way.schema.it;

import java.time.LocalDate;

import br.com.objectos.way.code.Testing;
import br.com.objectos.way.orm.Insertable;
import br.com.objectos.way.pojo.Pojo;
import br.com.objectos.way.schema.it.REVISION;

@Pojo
@Testing
abstract class Revision implements Insertable {

  @REVISION.SEQ
  abstract int seq();

  @REVISION.DATE
  abstract LocalDate date();

  @REVISION.DESCRIPTION
  abstract String description();

  Revision() {
  }

}