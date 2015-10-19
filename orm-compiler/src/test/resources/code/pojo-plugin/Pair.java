package br.com.objectos.pojo.plugin;

import br.com.objectos.pojo.Pojo;
import br.com.objectos.schema.it.PAIR;

@Pojo
abstract class Pair {

  @PAIR.ID
  abstract int id();

  @PAIR.NAME
  abstract String name();

  Pair() {
  }

}