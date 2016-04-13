package br.com.objectos.pojo.plugin;

import java.util.Optional;

import br.com.objectos.schema.it.MERGE;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.way.relational.Loader;

@Pojo
@Loader
abstract class Merge implements br.com.objectos.way.relational.Insertable {

  @MERGE.SEQ
  abstract int seq();

  @MERGE.MERGE_REVISION_PARENT_A_FK
  abstract Revision parentA();

  @MERGE.MERGE_REVISION_PARENT_B_FK
  abstract Optional<Revision> parentB();

  Merge() {
  }

}