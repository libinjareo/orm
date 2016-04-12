package br.com.objectos.pojo.plugin;

import br.com.objectos.way.schema.it.PAIR;
import br.com.objectos.way.orm.Insertable;
import br.com.objectos.way.orm.Setter;
import br.com.objectos.way.pojo.Pojo;
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

  @Setter
  public abstract Pair set(@PAIR.ID int id);

  @Setter
  public abstract Pair set(@PAIR.ID int id, @PAIR.NAME String name);

  @Setter
  abstract Pair setName(@PAIR.NAME String name);

}