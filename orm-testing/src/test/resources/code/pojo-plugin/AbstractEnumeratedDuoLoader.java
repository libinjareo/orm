package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import br.com.objectos.way.relational.ResultSetWrapper;
import javax.annotation.Generated;

@Generated("br.com.objectos.orm.compiler.RelationalLoaderPlugin")
abstract class AbstractEnumeratedDuoLoader {
  private final Orm orm;

  AbstractEnumeratedDuoLoader(Orm orm) {
    this.orm = orm;
  }

  int id(ResultSetWrapper rs, String columnName) {
    return rs.getInt(columnName);
  }

  String name(ResultSetWrapper rs, String columnName) {
    return rs.getString(columnName);
  }
}