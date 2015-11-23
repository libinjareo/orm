package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import br.com.objectos.way.relational.ResultSetWrapper;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("br.com.objectos.orm.compiler.RelationalLoaderPlugin")
abstract class AbstractMergeLoader {
  private final Orm orm;

  AbstractMergeLoader(Orm orm) {
    this.orm = orm;
  }

  int seq(ResultSetWrapper rs, String columnName) {
    return rs.getInt(columnName);
  }

  Revision parentA(ResultSetWrapper rs, String columnName) {
    return parentA(rs, columnName, rs.getInt(columnName));
  }

  abstract Revision parentA(ResultSetWrapper rs, String columnName, int key);

  Optional<Revision> parentB(ResultSetWrapper rs, String columnName) {
    return parentB(rs, columnName, rs.getInt(columnName));
  }

  abstract Optional<Revision> parentB(ResultSetWrapper rs, String columnName, int key);
}