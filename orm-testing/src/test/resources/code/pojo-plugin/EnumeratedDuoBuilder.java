package br.com.objectos.pojo.plugin;

import br.com.objectos.way.db.ColumnType;
import java.util.Optional;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.way.orm.compiler.InjectPlugin",
    "br.com.objectos.way.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.way.pojo.plugin.OptionalPlugin",
    "br.com.objectos.way.pojo.plugin.StandardBuilderPropertyAction"
})
interface EnumeratedDuoBuilder {
  EnumeratedDuoBuilderId id(int id);

  interface EnumeratedDuoBuilderId {
    EnumeratedDuoBuilderName name(Optional<ColumnType> name);

    EnumeratedDuoBuilderName name();

    EnumeratedDuoBuilderName nameOf(ColumnType name);

    EnumeratedDuoBuilderName nameOfNullable(ColumnType name);
  }

  interface EnumeratedDuoBuilderName {
    EnumeratedDuo build();
  }
}
