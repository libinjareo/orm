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

import static br.com.objectos.testing.MoreMatchers.equalTo;
import static br.com.objectos.testing.MoreMatchers.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.objectos.collections.ImmutableList;
import br.com.objectos.schema.it.OBJECTOS_ORM;
import br.com.objectos.schema.it.REVISION.REVISION_SEQ;
import br.com.objectos.sql.query.Row1;
import br.com.objectos.sql.query.Sql;

import org.testng.annotations.Test;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class RevisionTest extends AbstractOrmTest {

  private final br.com.objectos.schema.it.REVISION REVISION = OBJECTOS_ORM.REVISION();

  @Test
  public void insertAll() {
    List<Revision> list = ImmutableList.of(
        rev(dt(2015, 11, 5), "First commit"),
        rev(dt(2015, 11, 6), "Second commit"),
        rev(dt(2015, 11, 7), "Third commit"));

    assertSeq(list, 0, 0, 0);
    RevisionOrm.get(orm()).insertAll(list);
    assertSeq(list, 1, 2, 3);

    List<Row1<REVISION_SEQ>> res = Sql.select(REVISION.SEQ())
        .from(REVISION)
        .compile(trx().dialect())
        .stream(trx())
        .collect(Collectors.toList());
    assertThat(res, hasSize(3));
    assertThat(res, equalTo(ImmutableList.<Row1<REVISION_SEQ>> builder()
        .add(Row1.of(REVISION.SEQ(1)))
        .add(Row1.of(REVISION.SEQ(2)))
        .add(Row1.of(REVISION.SEQ(3)))
        .build()));
  }

  private void assertSeq(List<Revision> list, int... expected) {
    int[] res = list.stream().mapToInt(rev -> rev.seq()).toArray();
    assertThat(res, equalTo(expected));
  }

  private LocalDate dt(int y, int m, int d) {
    return LocalDate.of(y, m, d);
  }

  private Revision rev(LocalDate date, String description) {
    return Revision.builder()
        .date(date)
        .description(description)
        .build();
  }

}