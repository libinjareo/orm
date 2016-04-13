/*
 * Copyright 2015 Objectos, Fábrica de Software LTDA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package br.com.objectos.schema.it;

import br.com.objectos.schema.annotation.AutoIncrement;
import br.com.objectos.schema.annotation.ForeignKey;
import br.com.objectos.schema.annotation.Length;
import br.com.objectos.schema.annotation.Migration;
import br.com.objectos.schema.annotation.NotNull;
import br.com.objectos.schema.annotation.PrimaryKey;
import br.com.objectos.schema.annotation.Table;
import br.com.objectos.schema.type.IntColumn;
import br.com.objectos.schema.type.VarcharColumn;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
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