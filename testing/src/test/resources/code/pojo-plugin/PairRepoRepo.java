package br.com.objectos.pojo.plugin;

import br.com.objectos.core.util.MoreCollectors;
import br.com.objectos.db.SqlRuntimeException;
import br.com.objectos.db.Transaction;
import br.com.objectos.orm.Orm;
import br.com.objectos.schema.it.PAIR;
import br.com.objectos.sql.Sql;
import java.util.List;
import javax.annotation.Generated;
import javax.inject.Inject;

@Generated("br.com.objectos.orm.compiler.RepoCompiler")
final class PairRepoRepo extends PairRepo {
  private final Orm orm;

  @Inject
  PairRepoRepo(Orm orm) {
    this.orm = orm;
  }

  @Override
  List<Pair> findAll() {
    try (Transaction trx = orm.startTransaction()) {
      PAIR PAIR = br.com.objectos.schema.it.PAIR.get();
      return Sql.select(PAIR.ID(), PAIR.NAME())
          .from(PAIR)
          .compile(trx.dialect())
          .stream(trx)
          .map(PairOrm.get(orm)::load)
          .collect(MoreCollectors.toImmutableList());
    } catch (Exception e) {
      throw new SqlRuntimeException(e);
    }
  }

  @Override
  List<Pair> findAllOrderByName() {
    try (Transaction trx = orm.startTransaction()) {
      PAIR PAIR = br.com.objectos.schema.it.PAIR.get();
      return Sql.select(PAIR.ID(), PAIR.NAME())
          .from(PAIR)
          .orderBy(PAIR.NAME().asc())
          .compile(trx.dialect())
          .stream(trx)
          .map(PairOrm.get(orm)::load)
          .collect(MoreCollectors.toImmutableList());
    } catch (Exception e) {
      throw new SqlRuntimeException(e);
    }
  }
}