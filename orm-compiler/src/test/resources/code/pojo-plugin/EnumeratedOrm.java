package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.orm.compiler.CompanionTypePlugin")
public final class EnumeratedOrm {
  private final Orm orm;

  @Inject
  EnumeratedOrm(Orm orm) {
    this.orm = orm;
  }
}