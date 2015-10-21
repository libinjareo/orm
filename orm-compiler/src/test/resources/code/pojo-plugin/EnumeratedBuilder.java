package br.com.objectos.pojo.plugin;

import br.com.objectos.schema.meta.EnumType;
import javax.annotation.Generated;

@Generated({
  "br.com.objectos.pojo.compiler.PojoCompiler",
  "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction"
})
interface EnumeratedBuilder {
  EnumeratedBuilderOrdinalEnum ordinalEnum(EnumType ordinalEnum);

  interface EnumeratedBuilderOrdinalEnum {
    EnumeratedBuilderStringEnum stringEnum(EnumType stringEnum);
  }

  interface EnumeratedBuilderStringEnum {
    Enumerated build();
  }
}
