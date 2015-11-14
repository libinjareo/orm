package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import br.com.objectos.schema.it.PAIR;
import br.com.objectos.schema.meta.EnumType;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.ColumnPropertyPlugin$StandardAction",
    "br.com.objectos.orm.compiler.ConstructorPlugin",
    "br.com.objectos.orm.compiler.InjectPlugin",
    "br.com.objectos.pojo.compiler.PojoCompiler"
})
final class EnumeratedPojo extends Enumerated {
  final Orm orm;

  private final PAIR.PAIR_ID ordinalEnum;

  private final PAIR.PAIR_NAME stringEnum;

  public EnumeratedPojo(Orm orm, PAIR.PAIR_ID ordinalEnum, PAIR.PAIR_NAME stringEnum) {
    super();
    this.orm = orm;
    this.ordinalEnum = ordinalEnum;
    this.stringEnum = stringEnum;
  }

  public EnumeratedPojo(Orm orm, EnumeratedBuilderPojo builder) {
    super();
    this.orm = orm;
    ordinalEnum = PAIR.get().ID(builder.___get___ordinalEnum().ordinal());
    stringEnum = PAIR.get().NAME(builder.___get___stringEnum().name());
  }

  @Override
  EnumType ordinalEnum() {
    return EnumType.values()[ordinalEnum.get()];
  }

  @Override
  EnumType stringEnum() {
    return EnumType.valueOf(stringEnum.get());
  }
}