package br.com.objectos.pojo.plugin;

import br.com.objectos.schema.meta.EnumType;
import br.com.objectos.way.orm.Orm;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction",
    "br.com.objectos.way.orm.compiler.InjectPlugin"
})
final class EnumeratedBuilderPojo implements EnumeratedBuilder, EnumeratedBuilder.EnumeratedBuilderOrdinalEnum, EnumeratedBuilder.EnumeratedBuilderStringEnum {
  private final Orm orm;

  private EnumType ordinalEnum;

  private EnumType stringEnum;

  public EnumeratedBuilderPojo(Orm orm) {
    this.orm = orm;
  }

  @Override
  public Enumerated build() {
    return new EnumeratedPojo(orm, this);
  }

  @Override
  public EnumeratedBuilder.EnumeratedBuilderOrdinalEnum ordinalEnum(EnumType ordinalEnum) {
    if (ordinalEnum == null) {
      throw new NullPointerException();
    }
    this.ordinalEnum = ordinalEnum;
    return this;
  }

  EnumType ___get___ordinalEnum() {
    return ordinalEnum;
  }

  @Override
  public EnumeratedBuilder.EnumeratedBuilderStringEnum stringEnum(EnumType stringEnum) {
    if (stringEnum == null) {
      throw new NullPointerException();
    }
    this.stringEnum = stringEnum;
    return this;
  }

  EnumType ___get___stringEnum() {
    return stringEnum;
  }
}
