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
package br.com.objectos.way.it.orm;

import static br.com.objectos.testing.MoreMatchers.equalTo;
import static br.com.objectos.testing.MoreMatchers.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import br.com.objectos.core.util.ImmutableList;
import br.com.objectos.way.it.schema.APP.APP_ID;
import br.com.objectos.way.it.schema.OBJECTOS_ORM;
import br.com.objectos.sql.Row1;
import br.com.objectos.sql.Sql;

import org.testng.annotations.Test;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class AppTest extends AbstractOrmTest {

  private final br.com.objectos.way.it.schema.APP APP = OBJECTOS_ORM.APP();

  @Test
  public void insertAll() {
    List<App> list = ImmutableList.of(app(), app(), app());

    assertId(list, 0, 0, 0);
    AppOrm.get(orm()).insertAll(list);
    assertId(list, 1, 2, 3);

    List<Row1<APP_ID>> res = Sql.select(APP.ID())
        .from(APP)
        .compile(trx().dialect())
        .stream(trx())
        .collect(Collectors.toList());
    assertThat(res, hasSize(3));
    assertThat(res, equalTo(ImmutableList.<Row1<APP_ID>> builder()
        .add(Row1.of(APP.ID(1)))
        .add(Row1.of(APP.ID(2)))
        .add(Row1.of(APP.ID(3)))
        .build()));
  }

  private void assertId(List<App> list, int... expected) {
    int[] res = list.stream().mapToInt(rev -> rev.id()).toArray();
    assertThat(res, equalTo(expected));
  }

  private App app() {
    return App.builder(orm())
        .build();
  }

}