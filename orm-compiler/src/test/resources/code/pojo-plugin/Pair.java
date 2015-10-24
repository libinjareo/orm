package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Insertable;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.schema.it.PAIR;

@Pojo
abstract class Pair implements Insertable {

  @PAIR.ID
  abstract int id();

  @PAIR.NAME
  abstract String name();

  Pair() {
  }

}