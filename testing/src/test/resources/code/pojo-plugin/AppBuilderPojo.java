package br.com.objectos.pojo.plugin;

import br.com.objectos.way.orm.Orm;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.way.orm.compiler.ColumnPropertyBuilderPropertyAction",
    "br.com.objectos.way.orm.compiler.InjectPlugin"
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
