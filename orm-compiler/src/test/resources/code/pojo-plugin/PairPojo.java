package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.InsertableRowBinder;
import br.com.objectos.schema.it.PAIR;
import br.com.objectos.sql.query.InsertableRow2;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.ColumnPropertyPlugin$StandardAction",
    "br.com.objectos.orm.compiler.InsertablePlugin",
    "br.com.objectos.pojo.compiler.PojoCompiler"
})
final class PairPojo extends Pair implements InsertableRowBinder<InsertableRow2<PAIR.PAIR_ID, PAIR.PAIR_NAME>, InsertableRow2.Values<PAIR.PAIR_ID, PAIR.PAIR_NAME>> {
  private final PAIR.PAIR_ID id;

  private final PAIR.PAIR_NAME name;

  public PairPojo(PairBuilderPojo builder) {
    super();
    id = PAIR.get().ID(builder.___get___id());
    name = PAIR.get().NAME(builder.___get___name());
  }

  @Override
  int id() {
    return id.get();
  }

  @Override
  String name() {
    return name.get();
  }

  @Override
  public InsertableRow2.Values<PAIR.PAIR_ID, PAIR.PAIR_NAME> bindInsertableRow(InsertableRow2<PAIR.PAIR_ID, PAIR.PAIR_NAME> row) {
    return row.values(id, name);
  }
}