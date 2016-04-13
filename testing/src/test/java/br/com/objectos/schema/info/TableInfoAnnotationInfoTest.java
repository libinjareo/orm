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
package br.com.objectos.schema.info;

import static br.com.objectos.testing.MoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class TableInfoAnnotationInfoTest {

  @DataProvider
  public Object[][] insertIntoCodeProvider() {
    return new Object[][] {
      { TableInfoAnnotationInfoFake.PAIR, Arrays.asList(
          "br.com.objectos.sql.Sql",
          "    .insertInto(PAIR)",
          "    .$(PAIR.ID(), PAIR.NAME())") },
      { TableInfoAnnotationInfoFake.REVISION, Arrays.asList(
          "br.com.objectos.sql.Sql",
          "    .insertInto(REVISION)",
          "    .$(REVISION.SEQ(), REVISION.DATE(), REVISION.DESCRIPTION())") },
      { TableInfoAnnotationInfoFake.SALARY, Arrays.asList(
          "br.com.objectos.sql.Sql",
          "    .insertInto(SALARY)",
          "    .$(SALARY.EMP_NO(), SALARY.SALARY_(), SALARY.FROM_DATE(), SALARY.TO_DATE())") }
    };
  }

  @DataProvider
  public Object[][] tableGetCodeProvider() {
    return new Object[][] {
      { TableInfoAnnotationInfoFake.PAIR,
        "br.com.objectos.schema.it.PAIR PAIR = br.com.objectos.schema.it.PAIR.get();\n" },
      { TableInfoAnnotationInfoFake.REVISION,
        "br.com.objectos.schema.it.REVISION REVISION = br.com.objectos.schema.it.REVISION.get();\n" },
      { TableInfoAnnotationInfoFake.SALARY,
        "br.com.objectos.schema.it.SALARY SALARY = br.com.objectos.schema.it.SALARY.get();\n" }
    };
  }

  @Test(dataProvider = "insertIntoCodeProvider")
  public void insertIntoCode(TableInfoAnnotationInfo info, List<String> expected) {
    String[] res = info.insertIntoCode().toString().split("\n");
    assertThat(Arrays.asList(res), equalTo(expected));
  }

  @Test(dataProvider = "tableGetCodeProvider")
  public void tableGetCode(TableInfoAnnotationInfo info, String expected) {
    String res = info.tableGetCode().toString();
    assertThat(res, equalTo(expected));
  }

}