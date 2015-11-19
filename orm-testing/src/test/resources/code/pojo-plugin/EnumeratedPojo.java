package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.Orm;
import br.com.objectos.schema.it.PAIR;
import br.com.objectos.schema.meta.EnumType;
import br.com.objectos.sql.query.Row2;
import br.com.objectos.way.relational.Insert;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.ColumnOrmPropertyPlugin",
    "br.com.objectos.orm.compiler.ConstructorPlugin",
    "br.com.objectos.orm.compiler.InjectPlugin",
    "br.com.objectos.orm.compiler.RelationalInsertablePlugin",
    "br.com.objectos.pojo.compiler.PojoCompiler"
})
final class EnumeratedPojo extends Enumerated {
  final Orm orm;

  private final PAIR.PAIR_ID ordinalEnum;

  private final PAIR.PAIR_NAME stringEnum;

  public EnumeratedPojo(Orm orm, Row2<PAIR.PAIR_ID, PAIR.PAIR_NAME> row) {
    this(orm, row.column1(), row.column2());
  }

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
  public Insert getInsert() {
    return Insert.into("OBJECTOS_ORM.PAIR")
        .value("ID", ordinalEnum.getWrapped())
        .value("NAME", stringEnum.getWrapped());
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