package br.com.objectos.way.schema.it;

import br.com.objectos.way.schema.annotation.ForeignKey;
import br.com.objectos.way.schema.annotation.ForeignKeyAction;
import br.com.objectos.way.schema.annotation.Length;
import br.com.objectos.way.schema.annotation.Migration;
import br.com.objectos.way.schema.annotation.Name;
import br.com.objectos.way.schema.annotation.NotNull;
import br.com.objectos.way.schema.annotation.PrimaryKey;
import br.com.objectos.way.schema.annotation.Table;
import br.com.objectos.way.schema.type.DateColumn;
import br.com.objectos.way.schema.type.IntColumn;
import br.com.objectos.way.schema.type.VarcharColumn;

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