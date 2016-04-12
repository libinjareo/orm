package br.com.objectos.pojo.plugin;

import br.com.objectos.way.orm.Orm;
import br.com.objectos.way.relational.ResultSetLoader;
import br.com.objectos.way.relational.ResultSetWrapper;
import br.com.objectos.way.schema.it.DUO;
import java.sql.ResultSet;
import javax.annotation.Generated;

@Generated("br.com.objectos.way.orm.compiler.RelationalLoaderPlugin")
abstract class AbstractEnumeratedDuoLoader implements ResultSetLoader<EnumeratedDuo> {
  private final Orm orm;

  AbstractEnumeratedDuoLoader(Orm orm) {
    this.orm = orm;
  }

  @Override
  public EnumeratedDuo load(ResultSet resultSet) {
    ResultSetWrapper rs = new ResultSetWrapper("DUO", resultSet);
    return new EnumeratedDuoPojo(
        orm,
        DUO.get().ID(id(rs, "ID")),
        DUO.get().NAME(name(rs, "NAME")));
  }

  int id(ResultSetWrapper rs, String columnName) {
    return rs.getInt(columnName);
  }

  String name(ResultSetWrapper rs, String columnName) {
    return rs.getString(columnName);
  }
}
