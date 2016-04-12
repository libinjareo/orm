package br.com.objectos.pojo.plugin;

import br.com.objectos.way.schema.it.PAIR;
import br.com.objectos.way.schema.meta.EnumType;
import br.com.objectos.way.pojo.Pojo;
import br.com.objectos.way.relational.Loader;

@Pojo
@Loader
abstract class Enumerated implements br.com.objectos.way.relational.Insertable {

  @PAIR.ID
  abstract EnumType ordinalEnum();

  @PAIR.NAME
  abstract EnumType stringEnum();

  Enumerated() {
  }

}