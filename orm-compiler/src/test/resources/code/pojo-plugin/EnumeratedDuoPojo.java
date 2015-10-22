package br.com.objectos.pojo.plugin;

import br.com.objectos.db.type.ColumnType;
import br.com.objectos.schema.it.DUO;
import java.util.Optional;
import javax.annotation.Generated;

@Generated({
  "br.com.objectos.orm.compiler.ColumnPropertyPlugin$OptionalAction",
  "br.com.objectos.orm.compiler.ColumnPropertyPlugin$StandardAction",
  "br.com.objectos.pojo.compiler.PojoCompiler"
})
final class EnumeratedDuoPojo extends EnumeratedDuo {
  private final DUO.DUO_ID id;

  private final DUO.DUO_NAME name;

  public EnumeratedDuoPojo(EnumeratedDuoBuilderPojo builder) {
    super();
    id = DUO.get().ID(builder.___get___id());
    name = builder.___get___name()
        .map(o -> DUO.get().NAME(o.name()))
        .orElse(DUO.get().NAME());
  }

  @Override
  int id() {
    return id.get();
  }

  @Override
  Optional<ColumnType> name() {
    return name.getIfPresent().map(s -> ColumnType.valueOf(s));
  }
}
