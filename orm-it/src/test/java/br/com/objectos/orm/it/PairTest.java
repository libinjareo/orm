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

import static br.com.objectos.assertion.TestableAssertion.assertThat;
import static br.com.objectos.testing.MoreMatchers.equalTo;
import static br.com.objectos.testing.MoreMatchers.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import br.com.objectos.collections.ImmutableList;
import br.com.objectos.schema.it.OBJECTOS_ORM;
import br.com.objectos.schema.it.PAIR.PAIR_NAME;
import br.com.objectos.sql.query.Row1;
import br.com.objectos.sql.query.Sql;

import org.testng.annotations.Test;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class PairTest extends AbstractOrmTest {

  private final br.com.objectos.schema.it.PAIR PAIR = OBJECTOS_ORM.PAIR();

  @Test
  public void insertAll() {
    List<Pair> pairList = ImmutableList.of(PairFake.PAIR_004, PairFake.PAIR_005);
    PairOrm.get(orm()).insertAll(pairList);
    List<Row1<PAIR_NAME>> res = Sql.select(PAIR.NAME())
        .from(PAIR)
        .compile(trx().dialect())
        .stream(trx())
        .collect(Collectors.toList());
    assertThat(res, hasSize(5));
    assertThat(res, equalTo(ImmutableList.<Row1<PAIR_NAME>> builder()
        .add(Row1.of(PAIR.NAME("One")))
        .add(Row1.of(PAIR.NAME("Two")))
        .add(Row1.of(PAIR.NAME("Three")))
        .add(Row1.of(PAIR.NAME("Four")))
        .add(Row1.of(PAIR.NAME("Five")))
        .build()));
  }

  @Test
  public void isEqualTo() {
    Pair a = p(1, "a");
    Pair b = p(1, "a");
    assertThat(a).isEqualTo(b);
  }

  private Pair p(int id, String name) {
    return Pair.builder()
        .id(id)
        .name(name)
        .build();
  }

}