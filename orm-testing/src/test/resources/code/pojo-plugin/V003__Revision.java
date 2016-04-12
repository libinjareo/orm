package br.com.objectos.way.schema.it;

import br.com.objectos.way.schema.annotation.AutoIncrement;
import br.com.objectos.way.schema.annotation.Length;
import br.com.objectos.way.schema.annotation.Migration;
import br.com.objectos.way.schema.annotation.NotNull;
import br.com.objectos.way.schema.annotation.PrimaryKey;
import br.com.objectos.way.schema.annotation.Table;
import br.com.objectos.way.schema.type.DateColumn;
import br.com.objectos.way.schema.type.IntColumn;
import br.com.objectos.way.schema.type.VarcharColumn;

@Migration(schema = OBJECTOS_ORM.class)
class V003__Revision {

  @Table
  interface REVISION {

    @PrimaryKey
    @AutoIncrement
    @NotNull
    IntColumn SEQ();

    @NotNull
    DateColumn DATE();

    @NotNull
    @Length(140)
    VarcharColumn DESCRIPTION();

  }

}