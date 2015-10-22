package br.com.objectos.pojo.plugin;

import br.com.objectos.db.type.ColumnType;
import java.util.Optional;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.pojo.compiler.PojoCompiler",
    "br.com.objectos.pojo.plugin.OptionalPlugin",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction"
})
final class EnumeratedDuoBuilderPojo implements EnumeratedDuoBuilder, EnumeratedDuoBuilder.EnumeratedDuoBuilderId, EnumeratedDuoBuilder.EnumeratedDuoBuilderName {
  private int id;

  private Optional<ColumnType> name;

  public EnumeratedDuoBuilderPojo() {
  }

  @Override
  public EnumeratedDuo build() {
    return new EnumeratedDuoPojo(this);
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