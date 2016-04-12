package br.com.objectos.pojo.plugin;

import br.com.objectos.way.db.ColumnType;
import br.com.objectos.way.orm.Orm;
import java.util.Optional;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.way.orm.compiler.InjectPlugin",
    "br.com.objectos.way.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.way.pojo.plugin.OptionalPlugin",
    "br.com.objectos.way.pojo.plugin.StandardBuilderPropertyAction"
})
final class EnumeratedDuoBuilderPojo implements EnumeratedDuoBuilder, EnumeratedDuoBuilder.EnumeratedDuoBuilderId, EnumeratedDuoBuilder.EnumeratedDuoBuilderName {
  private final Orm orm;

  private int id;

  private Optional<ColumnType> name;

  public EnumeratedDuoBuilderPojo(Orm orm) {
    this.orm = orm;
  }

  @Override
  public EnumeratedDuo build() {
    return new EnumeratedDuoPojo(orm, this);
  }

  @Override
  public EnumeratedDuoBuilder.EnumeratedDuoBuilderId id(int id) {
    this.id = id;
    return this;
  }

  int ___get___id() {
    return id;
  }

  @Override
  public EnumeratedDuoBuilder.EnumeratedDuoBuilderName name(Optional<ColumnType> name) {
    if (name == null) {
      throw new NullPointerException();
    }
    this.name = name;
    return this;
  }

  Optional<ColumnType> ___get___name() {
    return name;
  }

  @Override
  public EnumeratedDuoBuilder.EnumeratedDuoBuilderName name() {
    this.name = Optional.empty();
    return this;
  }

  @Override
  public EnumeratedDuoBuilder.EnumeratedDuoBuilderName nameOf(ColumnType name) {
    this.name = Optional.of(name);
    return this;
  }

  @Override
  public EnumeratedDuoBuilder.EnumeratedDuoBuilderName nameOfNullable(ColumnType name) {
    this.name = Optional.ofNullable(name);
    return this;
  }
}
