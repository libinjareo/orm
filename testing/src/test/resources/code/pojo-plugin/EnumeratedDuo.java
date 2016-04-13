package br.com.objectos.pojo.plugin;

import java.util.Optional;

import br.com.objectos.schema.it.DUO;
import br.com.objectos.db.ColumnType;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.way.relational.Loader;

@Pojo
@Loader
abstract class EnumeratedDuo implements br.com.objectos.way.relational.Insertable {

  @DUO.ID
  abstract int id();

  @DUO.NAME
  abstract Optional<ColumnType> name();

  EnumeratedDuo() {
  }

}
