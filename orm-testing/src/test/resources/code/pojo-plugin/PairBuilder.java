package br.com.objectos.pojo.plugin;

import javax.annotation.Generated;

@Generated({
    "br.com.objectos.way.orm.compiler.InjectPlugin",
    "br.com.objectos.way.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.way.pojo.plugin.StandardBuilderPropertyAction"
})
interface PairBuilder {
  PairBuilderId id(int id);

  interface PairBuilderId {
    PairBuilderName name(String name);
  }

  interface PairBuilderName {
    Pair build();
  }
}
