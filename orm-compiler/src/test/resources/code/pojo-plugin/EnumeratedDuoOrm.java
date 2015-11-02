package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.orm.compiler.CompanionTypePlugin")
public final class EnumeratedDuoOrm {
  private final Orm orm;

  @Inject
  EnumeratedDuoOrm(Orm orm) {
    this.orm = orm;
  }
}