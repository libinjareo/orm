package br.com.objectos.pojo.plugin;

import javax.annotation.Generated;

@Generated({
    "br.com.objectos.pojo.compiler.PojoCompiler",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction"
})
final class PairBuilderPojo implements PairBuilder, PairBuilder.PairBuilderId, PairBuilder.PairBuilderName {
  private int id;

  private String name;

  public PairBuilderPojo() {
  }

  @Override
  public Pair build() {
    return new PairPojo(this);
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