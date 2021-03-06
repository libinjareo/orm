package br.com.objectos.pojo.plugin;

import br.com.objectos.db.Result;
import br.com.objectos.orm.InsertableRowBinder;
import br.com.objectos.orm.Orm;
import br.com.objectos.schema.it.APP;
import br.com.objectos.sql.InsertableRow1;
import br.com.objectos.sql.Row1;
import br.com.objectos.way.relational.Insert;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.ColumnOrmPropertyPlugin",
    "br.com.objectos.orm.compiler.ConstructorPlugin",
    "br.com.objectos.orm.compiler.InjectPlugin",
    "br.com.objectos.orm.compiler.InsertablePlugin",
    "br.com.objectos.orm.compiler.RelationalInsertablePlugin",
    "br.com.objectos.pojo.compiler.WritingPojoCompiler"
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
  public Insert getInsert() {
    return Insert.into("OBJECTOS_ORM.APP")
        .value("ID", (Integer) null)
        .on(rs -> id.onGeneratedKey(Result.of(rs)));
  }

  @Override
  int id() {
    return id.get();
  }
}
