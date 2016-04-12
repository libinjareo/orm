package br.com.objectos.pojo.plugin;

import br.com.objectos.way.orm.Orm;
import br.com.objectos.way.relational.ResultSetLoader;
import br.com.objectos.way.relational.ResultSetWrapper;
import br.com.objectos.way.schema.it.MERGE;
import java.sql.ResultSet;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("br.com.objectos.way.orm.compiler.RelationalLoaderPlugin")
abstract class AbstractMergeLoader implements ResultSetLoader<Merge> {
  private final Orm orm;

  AbstractMergeLoader(Orm orm) {
    this.orm = orm;
  }

  @Override
  public Merge load(ResultSet resultSet) {
    ResultSetWrapper rs = new ResultSetWrapper("MERGE", resultSet);
    return new MergePojo(
        orm,
        MERGE.get().SEQ(seq(rs, "SEQ")),
        parentA(rs, "PARENT_A"),
        parentB(rs, "PARENT_B"));
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
