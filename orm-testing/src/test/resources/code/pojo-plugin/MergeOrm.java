package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import java.util.Objects;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.orm.compiler.CompanionTypePlugin")
public final class MergeOrm {
  final Orm orm;

  @Inject
  MergeOrm(Orm orm) {
    this.orm = orm;
  }

  public static MergeOrm get(Orm orm) {
    Objects.requireNonNull(orm);
    return new MergeOrm(orm);
  }
}