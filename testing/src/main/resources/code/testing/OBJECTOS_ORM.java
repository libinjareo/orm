package br.com.objectos.schema.it;

import br.com.objectos.schema.annotation.Schema;

@Schema(migrations = {
  V001__First_Migration.class,
  V002__Employee_Salary.class,
  V003__Revision.class,
  V004__More.class
})
public class OBJECTOS_ORM {

}