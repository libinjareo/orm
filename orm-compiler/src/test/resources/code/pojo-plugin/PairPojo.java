package br.com.objectos.pojo.plugin;

import br.com.objectos.schema.it.PAIR;
import javax.annotation.Generated;

@Generated({
  "br.com.objectos.orm.compiler.ColumnPropertyPlugin$StandardAction",
  "br.com.objectos.pojo.compiler.PojoCompiler"
})
final class PairPojo extends Pair {
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
}