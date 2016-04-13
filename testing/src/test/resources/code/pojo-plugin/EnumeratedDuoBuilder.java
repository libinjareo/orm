package br.com.objectos.pojo.plugin;

import br.com.objectos.db.ColumnType;
import java.util.Optional;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.InjectPlugin",
    "br.com.objectos.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.pojo.plugin.OptionalPlugin",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction"
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
