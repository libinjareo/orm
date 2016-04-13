package br.com.objectos.pojo.plugin;

import br.com.objectos.orm.InsertableRowBinder;
import br.com.objectos.orm.Orm;
import br.com.objectos.schema.it.PAIR;
import br.com.objectos.sql.InsertableRow2;
import br.com.objectos.sql.Row2;
import br.com.objectos.way.relational.Insert;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.ColumnOrmPropertyPlugin",
    "br.com.objectos.orm.compiler.ConstructorPlugin",
    "br.com.objectos.orm.compiler.InjectPlugin",
    "br.com.objectos.orm.compiler.InsertablePlugin",
    "br.com.objectos.orm.compiler.RelationalInsertablePlugin",
    "br.com.objectos.orm.compiler.SetterPlugin",
    "br.com.objectos.pojo.compiler.WritingPojoCompiler"
})
final class PairPojo extends Pair implements InsertableRowBinder<InsertableRow2<PAIR.PAIR_ID, PAIR.PAIR_NAME>, InsertableRow2.Values<PAIR.PAIR_ID, PAIR.PAIR_NAME>> {
  final Orm orm;

  private final PAIR.PAIR_ID id;

  private final PAIR.PAIR_NAME name;

  public PairPojo(Orm orm, Row2<PAIR.PAIR_ID, PAIR.PAIR_NAME> row) {
    this(orm, row.column1(), row.column2());
  }

  public PairPojo(Orm orm, PAIR.PAIR_ID id, PAIR.PAIR_NAME name) {
    super();
    this.orm = orm;
    this.id = id;
    this.name = name;
  }

  public PairPojo(Orm orm, PairBuilderPojo builder) {
    super();
    this.orm = orm;
    id = PAIR.get().ID(builder.___get___id());
    name = PAIR.get().NAME(builder.___get___name());
  }

  @Override
  public InsertableRow2.Values<PAIR.PAIR_ID, PAIR.PAIR_NAME> bindInsertableRow(InsertableRow2<PAIR.PAIR_ID, PAIR.PAIR_NAME> row) {
    return row.values(id, name);
  }

  @Override
  public Insert getInsert() {
    return Insert.into("OBJECTOS_ORM.PAIR")
        .value("ID", id.getWrapped())
        .value("NAME", name.getWrapped());
  }

  @Override
  int id() {
    return id.get();
  }

  @Override
  String name() {
    return name.get();
  }

  @Override
  public Pair set(int id) {
    return new PairPojo(
        orm,
        PAIR.get().ID(id),
        name);
  }

  @Override
  public Pair set(int id, String name) {
    return new PairPojo(
        orm,
        PAIR.get().ID(id),
        PAIR.get().NAME(name));
  }

  @Override
  Pair setName(String name) {
    return new PairPojo(
        orm,
        id,
        PAIR.get().NAME(name));
  }
}
