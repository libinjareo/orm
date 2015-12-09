package br.com.objectos.pojo.plugin;

import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.InjectPlugin",
    "br.com.objectos.pojo.compiler.PojoCompiler",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction"
})
interface AppBuilder {
  PairBuilderId id(int id);

  interface PairBuilderId {
    PairBuilderName name(String name);
  }

  interface PairBuilderName {
    Pair build();
  }
}