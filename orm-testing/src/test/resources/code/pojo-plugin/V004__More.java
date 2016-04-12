package br.com.objectos.way.schema.it;

import br.com.objectos.way.schema.annotation.AutoIncrement;
import br.com.objectos.way.schema.annotation.ForeignKey;
import br.com.objectos.way.schema.annotation.Length;
import br.com.objectos.way.schema.annotation.Migration;
import br.com.objectos.way.schema.annotation.NotNull;
import br.com.objectos.way.schema.annotation.PrimaryKey;
import br.com.objectos.way.schema.annotation.Table;
import br.com.objectos.way.schema.type.IntColumn;
import br.com.objectos.way.schema.type.VarcharColumn;

@Migration(schema = OBJECTOS_ORM.class)
class V004__More {

  @Table
  interface DUO {
    IntColumn ID();

    @Length(140)
    VarcharColumn NAME();
  }

  @Table
  interface MERGE {
    @NotNull
    @AutoIncrement
    IntColumn SEQ();

    @NotNull
    IntColumn PARENT_A();

    IntColumn PARENT_B();

    @PrimaryKey
    interface MERGE_PK {
      IntColumn SEQ();
    }

    @ForeignKey
    interface MERGE_REVISION_PARENT_B_FK {
      @REVISION_V003.SEQ
      IntColumn PARENT_B();
    }

    @ForeignKey
    interface MERGE_REVISION_PARENT_A_FK {
      @REVISION_V003.SEQ
      IntColumn PARENT_A();
    }
  }
  
  @Table
  interface APP {
    @NotNull
    @AutoIncrement
    IntColumn ID();
  }

}