package br.com.objectos.schema.it;

import br.com.objectos.code.Testing;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.schema.it.PAIR;

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