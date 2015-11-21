package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Insertable;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.schema.it.PAIR;
import br.com.objectos.way.relational.Loader;

@Pojo
@Loader
abstract class Pair 
    implements 
    Insertable, 
    br.com.objectos.way.relational.Insertable {

  @PAIR.ID
  abstract int id();

  @PAIR.NAME
  abstract String name();

  Pair() {
  }

}