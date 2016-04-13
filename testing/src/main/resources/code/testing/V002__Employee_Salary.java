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

import br.com.objectos.schema.annotation.ForeignKey;
import br.com.objectos.schema.annotation.ForeignKeyAction;
import br.com.objectos.schema.annotation.Length;
import br.com.objectos.schema.annotation.Migration;
import br.com.objectos.schema.annotation.Name;
import br.com.objectos.schema.annotation.NotNull;
import br.com.objectos.schema.annotation.PrimaryKey;
import br.com.objectos.schema.annotation.Table;
import br.com.objectos.schema.type.DateColumn;
import br.com.objectos.schema.type.IntColumn;
import br.com.objectos.schema.type.VarcharColumn;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Migration(schema = OBJECTOS_ORM.class)
class V002__Employee_Salary {

  @Table
  interface EMPLOYEE {

    @PrimaryKey
    @NotNull
    IntColumn EMP_NO();

    @NotNull
    DateColumn BIRTH_DATE();

    @NotNull
    @Length(14)
    VarcharColumn FIRST_NAME();

    @NotNull
    @Length(16)
    VarcharColumn LAST_NAME();

    @NotNull
    DateColumn HIRE_DATE();

  }

  @Table
  interface SALARY {

    @NotNull
    IntColumn EMP_NO();

    @Name("SALARY")
    @NotNull
    IntColumn SALARY_();

    @NotNull
    DateColumn FROM_DATE();

    @NotNull
    DateColumn TO_DATE();

    @PrimaryKey
    interface SALARY_PK {
      IntColumn EMP_NO();
      DateColumn FROM_DATE();
    }

    @ForeignKey(onDelete = ForeignKeyAction.CASCADE)
    interface SALARY_EMP_NO_FK {
      @EMPLOYEE_V002.EMP_NO
      IntColumn EMP_NO();
    }

  }

}