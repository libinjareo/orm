package br.com.objectos.orm.it;

import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.SqlPojoCompiler",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction"
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