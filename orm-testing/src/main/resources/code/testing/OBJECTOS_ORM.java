package br.com.objectos.way.schema.it;

import br.com.objectos.way.schema.annotation.Schema;

@Schema(migrations = {
  V001__First_Migration.class,
  V002__Employee_Salary.class,
  V003__Revision.class,
  V004__More.class
})
public class OBJECTOS_ORM {

}