package br.com.objectos.pojo.plugin;

import br.com.objectos.schema.it.PAIR;
import br.com.objectos.sql.Row2;
import br.com.objectos.way.orm.Orm;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.way.orm.compiler.ColumnOrmPropertyPlugin",
    "br.com.objectos.way.orm.compiler.ConstructorPlugin",
    "br.com.objectos.way.orm.compiler.InjectPlugin"
})
final class BooleanIntPojo extends BooleanInt {
  final Orm orm;

  private final PAIR.PAIR_ID id;

  private final PAIR.PAIR_NAME name;

  public BooleanIntPojo(Orm orm, Row2<PAIR.PAIR_ID, PAIR.PAIR_NAME> row) {
    this(orm, row.column1(), row.column2());
  }

  public BooleanIntPojo(Orm orm, PAIR.PAIR_ID id, PAIR.PAIR_NAME name) {
    super();
    this.orm = orm;
    this.id = id;
    this.name = name;
  }

  public BooleanIntPojo(Orm orm, BooleanIntBuilderPojo builder) {
    super();
    this.orm = orm;
    id = PAIR.get().ID(builder.___get___id() ? 1 : 0);
    name = PAIR.get().NAME(builder.___get___name());
  }

  @Override
  boolean id() {
    return id.get() != 0;
  }

  @Override
  String name() {
    return name.get();
  }
}
