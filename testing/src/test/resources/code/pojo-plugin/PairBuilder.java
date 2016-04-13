package br.com.objectos.pojo.plugin;

import javax.annotation.Generated;

@Generated({
    "br.com.objectos.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction",
    "br.com.objectos.way.orm.compiler.InjectPlugin"
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
