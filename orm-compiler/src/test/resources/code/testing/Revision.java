package br.com.objectos.schema.it;

import java.time.LocalDate;

import br.com.objectos.code.Testing;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.schema.it.REVISION;

@Pojo
@Testing
abstract class Revision {

  @REVISION.SEQ
  abstract int seq();

  @REVISION.DATE
  abstract LocalDate date();

  @REVISION.DESCRIPTION
  abstract String description();

  Revision() {
  }

}