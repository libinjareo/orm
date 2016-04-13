package br.com.objectos.pojo.plugin;

import java.util.List;

import br.com.objectos.db.SortOrder;
import br.com.objectos.orm.Query;
import br.com.objectos.orm.Repo;
import br.com.objectos.schema.it.PAIR;

@Repo
abstract class PairRepo {
  
  PairRepo() {}
  
  @Query
  abstract List<Pair> findAll();
  
  @Query
  @PAIR.NAME(orderBy = SortOrder.ASC)
  abstract List<Pair> findAllOrderByName();

}