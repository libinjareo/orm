package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import br.com.objectos.way.relational.ResultSetWrapper;
import javax.annotation.Generated;

@Generated("br.com.objectos.orm.compiler.RelationalLoaderPlugin")
abstract class AbstractEnumeratedLoader {
  private final Orm orm;

  AbstractEnumeratedLoader(Orm orm) {
    this.orm = orm;
  }

  int ordinalEnum(ResultSetWrapper rs, String columnName) {
    return rs.getInt(columnName);
  }

  String stringEnum(ResultSetWrapper rs, String columnName) {
    return rs.getString(columnName);
  }
}