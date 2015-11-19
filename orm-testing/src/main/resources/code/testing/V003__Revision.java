package br.com.objectos.schema.it;

import br.com.objectos.code.Testing;
import br.com.objectos.schema.annotation.AutoIncrement;
import br.com.objectos.schema.annotation.Length;
import br.com.objectos.schema.annotation.Migration;
import br.com.objectos.schema.annotation.NotNull;
import br.com.objectos.schema.annotation.PrimaryKey;
import br.com.objectos.schema.annotation.Table;
import br.com.objectos.schema.type.DateColumn;
import br.com.objectos.schema.type.IntColumn;
import br.com.objectos.schema.type.VarcharColumn;

@Migration(schema = OBJECTOS_ORM.class)
@Testing
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