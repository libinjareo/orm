package br.com.objectos.pojo.plugin;

import br.com.objectos.way.orm.Orm;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.way.orm.compiler.InjectPlugin",
    "br.com.objectos.way.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.way.pojo.plugin.StandardBuilderPropertyAction"
})
final class PairBuilderPojo implements PairBuilder, PairBuilder.PairBuilderId, PairBuilder.PairBuilderName {
  private final Orm orm;

  private int id;

  private String name;

  public PairBuilderPojo(Orm orm) {
    this.orm = orm;
  }

  @Override
  public Pair build() {
    return new PairPojo(orm, this);
  }

  @Override
  public PairBuilder.PairBuilderId id(int id) {
    this.id = id;
    return this;
  }

  int ___get___id() {
    return id;
  }

  @Override
  public PairBuilder.PairBuilderName name(String name) {
    if (name == null) {
      throw new NullPointerException();
    }
    this.name = name;
    return this;
  }

  String ___get___name() {
    return name;
  }
}
