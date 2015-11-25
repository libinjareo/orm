package br.com.objectos.pojo.plugin;

import java.util.List;

import br.com.objectos.orm.Query;
import br.com.objectos.orm.Repo;

@Repo
abstract class PairRepo {
  
  PairRepo() {}
  
  @Query
  abstract List<Pair> findAll();

}