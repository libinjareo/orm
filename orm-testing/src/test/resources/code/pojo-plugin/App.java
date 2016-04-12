package br.com.objectos.pojo.plugin;

import br.com.objectos.way.orm.Insertable;
import br.com.objectos.way.orm.Orm;
import br.com.objectos.way.pojo.Pojo;
import br.com.objectos.way.schema.it.APP;

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