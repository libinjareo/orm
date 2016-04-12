package br.com.objectos.pojo.plugin;

import br.com.objectos.way.pojo.Pojo;
import br.com.objectos.way.schema.it.PAIR;

@Pojo
abstract class BooleanInt {

  @PAIR.ID
  abstract boolean id();

  @PAIR.NAME
  abstract String name();

  BooleanInt() {
  }

}