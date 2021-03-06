package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Insertable;
import br.com.objectos.orm.Orm;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.schema.it.APP;

@Pojo
abstract class App
    implements
    Insertable,
    br.com.objectos.way.relational.Insertable {

  @APP.ID
  abstract int id();

  App() {
  }

}