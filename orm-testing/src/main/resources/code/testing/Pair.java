package br.com.objectos.way.schema.it;

import br.com.objectos.way.code.Testing;
import br.com.objectos.way.pojo.Pojo;
import br.com.objectos.way.schema.it.PAIR;

@Pojo
@Testing
abstract class Pair {

  @PAIR.ID
  abstract int id();

  @PAIR.NAME
  abstract String name();

  Pair() {
  }

}