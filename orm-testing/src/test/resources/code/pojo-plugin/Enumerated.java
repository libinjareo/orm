package br.com.objectos.pojo.plugin;

import br.com.objectos.pojo.Pojo;
import br.com.objectos.schema.it.PAIR;
import br.com.objectos.schema.meta.EnumType;

@Pojo
abstract class Enumerated implements br.com.objectos.way.relational.Insertable {

  @PAIR.ID
  abstract EnumType ordinalEnum();

  @PAIR.NAME
  abstract EnumType stringEnum();

  Enumerated() {
  }

}