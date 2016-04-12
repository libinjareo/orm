package br.com.objectos.pojo.plugin;

import br.com.objectos.way.orm.Orm;
import br.com.objectos.way.relational.ResultSetLoader;
import br.com.objectos.way.relational.ResultSetWrapper;
import br.com.objectos.way.schema.it.PAIR;
import java.sql.ResultSet;
import javax.annotation.Generated;

@Generated("br.com.objectos.way.orm.compiler.RelationalLoaderPlugin")
abstract class AbstractPairLoader implements ResultSetLoader<Pair> {
  private final Orm orm;

  AbstractPairLoader(Orm orm) {
    this.orm = orm;
  }

  @Override
  public Pair load(ResultSet resultSet) {
    ResultSetWrapper rs = new ResultSetWrapper("PAIR", resultSet);
    return new PairPojo(
        orm,
        PAIR.get().ID(id(rs, "ID")),
        PAIR.get().NAME(name(rs, "NAME")));
  }

  int id(ResultSetWrapper rs, String columnName) {
    return rs.getInt(columnName);
  }

  String name(ResultSetWrapper rs, String columnName) {
    return rs.getString(columnName);
  }
}
