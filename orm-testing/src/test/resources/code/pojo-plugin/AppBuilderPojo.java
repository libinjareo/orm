package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.ColumnPropertyBuilderPropertyAction",
    "br.com.objectos.orm.compiler.InjectPlugin",
    "br.com.objectos.pojo.compiler.PojoCompiler"
})
final class AppBuilderPojo implements AppBuilder {
  private final Orm orm;

  public AppBuilderPojo(Orm orm) {
    this.orm = orm;
  }

  @Override
  public App build() {
    return new AppPojo(orm, this);
  }
}