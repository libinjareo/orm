package br.com.objectos.schema.it;

import br.com.objectos.schema.annotation.Length;
import br.com.objectos.schema.annotation.Migration;
import br.com.objectos.schema.annotation.NotNull;
import br.com.objectos.schema.annotation.Table;
import br.com.objectos.schema.type.IntColumn;
import br.com.objectos.schema.type.VarcharColumn;

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