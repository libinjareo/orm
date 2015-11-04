package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import java.util.Objects;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.orm.compiler.CompanionTypePlugin")
public final class RevisionOrm {
  final Orm orm;

  @Inject
  RevisionOrm(Orm orm) {
    this.orm = orm;
  }

  public static RevisionOrm get(Orm orm) {
    Objects.requireNonNull(orm);
    return new RevisionOrm(orm);
  }
}