package br.com.objectos.way.schema.it;

import br.com.objectos.way.schema.annotation.Length;
import br.com.objectos.way.schema.annotation.Migration;
import br.com.objectos.way.schema.annotation.NotNull;
import br.com.objectos.way.schema.annotation.Table;
import br.com.objectos.way.schema.type.IntColumn;
import br.com.objectos.way.schema.type.VarcharColumn;

@Migration(schema = OBJECTOS_ORM.class)
class V001__First_Migration {

  @Table
  interface PAIR {

    @NotNull
    IntColumn ID();

    @NotNull
    @Length(120)
    VarcharColumn NAME();

  }

}