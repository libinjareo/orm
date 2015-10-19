package br.com.objectos.orm.it;

import br.com.objectos.orm.annotation.SqlPojo;

@SqlPojo
abstract class Pair {

  abstract int id();

  abstract String name();

  Pair() {
  }

}