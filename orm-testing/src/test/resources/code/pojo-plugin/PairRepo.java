package br.com.objectos.pojo.plugin;

import java.util.List;

import br.com.objectos.way.db.SortOrder;
import br.com.objectos.way.orm.Query;
import br.com.objectos.way.orm.Repo;
import br.com.objectos.way.schema.it.PAIR;

@Repo
abstract class PairRepo {
  
  PairRepo() {}
  
  @Query
  abstract List<Pair> findAll();
  
  @Query
  @PAIR.NAME(orderBy = SortOrder.ASC)
  abstract List<Pair> findAllOrderByName();

}