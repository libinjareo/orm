package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.InjectPlugin",
    "br.com.objectos.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction"
})
final class BooleanIntBuilderPojo implements BooleanIntBuilder, BooleanIntBuilder.BooleanIntBuilderId, BooleanIntBuilder.BooleanIntBuilderName {
  private final Orm orm;

  private boolean id;

  private String name;

  public BooleanIntBuilderPojo(Orm orm) {
    this.orm = orm;
  }

  @Override
  public BooleanInt build() {
    return new BooleanIntPojo(orm, this);
  }

  @Override
  public BooleanIntBuilder.BooleanIntBuilderId id(boolean id) {
    this.id = id;
    return this;
  }

  boolean ___get___id() {
    return id;
  }

  @Override
  public BooleanIntBuilder.BooleanIntBuilderName name(String name) {
    if (name == null) {
      throw new NullPointerException();
    }
    this.name = name;
    return this;
  }

  String ___get___name() {
    return name;
  }
}
