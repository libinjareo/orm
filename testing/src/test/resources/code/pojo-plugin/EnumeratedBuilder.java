package br.com.objectos.pojo.plugin;

import br.com.objectos.schema.meta.EnumType;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction",
    "br.com.objectos.way.orm.compiler.InjectPlugin"
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
