/*
 * Copyright 2014-2015 Objectos, Fábrica de Software LTDA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package br.com.objectos.orm.it;

import java.util.Iterator;
import java.util.Objects;

import javax.inject.Inject;

import br.com.objectos.orm.Orm;
import br.com.objectos.schema.it.PAIR;
import br.com.objectos.schema.it.PAIR.PAIR_ID;
import br.com.objectos.schema.it.PAIR.PAIR_NAME;
import br.com.objectos.sql.query.InsertableRow2;
import br.com.objectos.sql.query.Sql;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class PairOrm2 {

  private final Orm orm;

  @Inject
  PairOrm2(Orm orm) {
    this.orm = orm;
  }

  public static PairOrm2 get(Orm orm) {
    Objects.requireNonNull(orm);
    return new PairOrm2(orm);
  }

  public void insertAll(Iterable<Pair> entities) {
    Iterator<Pair> iterator = entities.iterator();
    if (!iterator.hasNext()) {
      return;
    }

    PAIR PAIR = br.com.objectos.schema.it.PAIR.get();
    PairPojo pojo = (PairPojo) iterator.next();
    InsertableRow2.Values<PAIR_ID, PAIR_NAME> insert;
    insert = pojo.bindInsertableRow(Sql
        .insertInto(PAIR)
        .$(PAIR.ID(), PAIR.NAME()));
    pojo.bindInsertableRow(insert);

    while (iterator.hasNext()) {
      pojo = (PairPojo) iterator.next();
      insert = pojo.bindInsertableRow(insert);
    }

    orm.executeUnchecked(insert);
  }

}