package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import javax.annotation.Generated;

@Generated("br.com.objectos.orm.compiler.RelationalLoaderPlugin")
abstract class AbstractPairLoader {
  private final Orm orm;

  AbstractPairLoader(Orm orm) {
    this.orm = orm;
  }
}