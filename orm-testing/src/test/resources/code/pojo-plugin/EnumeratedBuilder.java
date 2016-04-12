package br.com.objectos.pojo.plugin;

import br.com.objectos.way.schema.meta.EnumType;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.way.orm.compiler.InjectPlugin",
    "br.com.objectos.way.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.way.pojo.plugin.StandardBuilderPropertyAction"
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
