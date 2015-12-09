package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.InsertableRowBinder;
import br.com.objectos.orm.Orm;
import br.com.objectos.schema.it.APP;
import br.com.objectos.sql.query.InsertableRow1;
import br.com.objectos.sql.query.Row1;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.ColumnOrmPropertyPlugin",
    "br.com.objectos.orm.compiler.ConstructorPlugin",
    "br.com.objectos.orm.compiler.InjectPlugin",
    "br.com.objectos.orm.compiler.InsertablePlugin",
    "br.com.objectos.pojo.compiler.PojoCompiler"
})
final class AppPojo extends App implements InsertableRowBinder<InsertableRow1<APP.APP_ID>, InsertableRow1.Values<APP.APP_ID>> {
  final Orm orm;

  private final APP.APP_ID id;

  public AppPojo(Orm orm, Row1<APP.APP_ID> row) {
    this(orm, row.column1());
  }

  public AppPojo(Orm orm, APP.APP_ID id) {
    super();
    this.orm = orm;
    this.id = id;
  }

  public AppPojo(Orm orm, AppBuilderPojo builder) {
    super();
    this.orm = orm;
    id = APP.get().ID();
  }

  @Override
  public InsertableRow1.Values<APP.APP_ID> bindInsertableRow(InsertableRow1<APP.APP_ID> row) {
    return row.values(id).onGeneratedKey(id);
  }

  @Override
  int id() {
    return id.get();
  }
}