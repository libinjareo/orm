package br.com.objectos.pojo.plugin;

import java.util.Optional;

import br.com.objectos.db.type.ColumnType;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.schema.it.DUO;

@Pojo
abstract class EnumeratedDuo {

  @DUO.ID
  abstract int id();

  @DUO.NAME
  abstract Optional<ColumnType> name();

  EnumeratedDuo() {
  }

}
