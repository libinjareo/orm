package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import br.com.objectos.schema.it.PAIR;
import br.com.objectos.way.relational.ResultSetLoader;
import br.com.objectos.way.relational.ResultSetWrapper;
import java.sql.ResultSet;
import javax.annotation.Generated;

@Generated("br.com.objectos.orm.compiler.RelationalLoaderPlugin")
abstract class AbstractEnumeratedLoader implements ResultSetLoader<Enumerated> {
  private final Orm orm;

  AbstractEnumeratedLoader(Orm orm) {
    this.orm = orm;
  }

  @Override
  public Enumerated load(ResultSet resultSet) {
    ResultSetWrapper rs = new ResultSetWrapper("PAIR", resultSet);
    return new EnumeratedPojo(
        orm,
        PAIR.get().ID(ordinalEnum(rs, "ID")),
        PAIR.get().NAME(stringEnum(rs, "NAME")));
  }

  int ordinalEnum(ResultSetWrapper rs, String columnName) {
    return rs.getInt(columnName);
  }

  String stringEnum(ResultSetWrapper rs, String columnName) {
    return rs.getString(columnName);
  }
}