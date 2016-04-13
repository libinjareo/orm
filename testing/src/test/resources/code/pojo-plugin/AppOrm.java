package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import br.com.objectos.schema.it.APP;
import br.com.objectos.sql.InsertableRow1;
import br.com.objectos.sql.Row1;
import br.com.objectos.sql.Sql;
import java.util.Iterator;
import java.util.Objects;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.orm.compiler.CompanionTypePlugin")
public final class AppOrm {
  private final Orm orm;

  @Inject
  AppOrm(Orm orm) {
    this.orm = orm;
  }

  public static AppOrm get(Orm orm) {
    Objects.requireNonNull(orm);
    return new AppOrm(orm);
  }

  public void insertAll(Iterable<App> entities) {
    Iterator<App> iterator = entities.iterator();
    if (!iterator.hasNext()) {
      return;
    }
    AppPojo pojo = (AppPojo) iterator.next();
    APP APP = br.com.objectos.schema.it.APP.get();
    InsertableRow1.Values<APP.APP_ID> insert;
    insert = pojo.bindInsertableRow(Sql
        .insertInto(APP)
        .$(APP.ID()));
    while(iterator.hasNext()) {
      pojo = (AppPojo) iterator.next();
      insert = pojo.bindInsertableRow(insert);
    }
    orm.executeUnchecked(insert);
  }

  public App load(Row1<APP.APP_ID> row) {
    return new AppPojo(orm, row);
  }
}
