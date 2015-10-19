package br.com.objectos.orm.it;

import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.SqlPojoCompiler",
    "br.com.objectos.pojo.plugin.StandardPojoPropertyAction"
})
final class PairPojo extends Pair {
  private final int id;

  private final String name;

  public PairPojo(PairBuilderPojo builder) {
    super();
    id = builder.___get___id();
    name = builder.___get___name();
  }

  @Override
  int id() {
    return id;
  }

  @Override
  String name() {
    return name;
  }
}