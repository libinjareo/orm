package br.com.objectos.pojo.plugin;

import br.com.objectos.schema.it.PAIR;
import br.com.objectos.schema.meta.EnumType;
import javax.annotation.Generated;

@Generated({
  "br.com.objectos.orm.compiler.ColumnPropertyPlugin",
  "br.com.objectos.pojo.compiler.PojoCompiler"
})
final class EnumeratedPojo extends Enumerated {
  private final PAIR.PAIR_ID ordinalEnum;

  private final PAIR.PAIR_NAME stringEnum;

  public EnumeratedPojo(EnumeratedBuilderPojo builder) {
    super();
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
