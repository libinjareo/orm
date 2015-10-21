package br.com.objectos.pojo.plugin;

import br.com.objectos.schema.meta.EnumType;
import javax.annotation.Generated;

@Generated({
  "br.com.objectos.pojo.compiler.PojoCompiler",
  "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction"
})
final class EnumeratedBuilderPojo implements EnumeratedBuilder, EnumeratedBuilder.EnumeratedBuilderOrdinalEnum, EnumeratedBuilder.EnumeratedBuilderStringEnum {
  private EnumType ordinalEnum;

  private EnumType stringEnum;

  public EnumeratedBuilderPojo() {
  }

  @Override
  public Enumerated build() {
    return new EnumeratedPojo(this);
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
