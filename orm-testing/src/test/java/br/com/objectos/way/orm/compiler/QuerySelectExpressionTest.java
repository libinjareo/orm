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
package br.com.objectos.way.orm.compiler;

import static br.com.objectos.way.orm.compiler.NamingFake.schemaIt;
import static br.com.objectos.way.orm.compiler.OrmPropertyFake.Pair_id;
import static br.com.objectos.way.orm.compiler.OrmPropertyFake.Pair_name;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class QuerySelectExpressionTest extends AbstractOrmCompilerTest {

  @Test
  public void pair() {
    test("PAIR", "pair", Pair_id, Pair_name);
  }

  @Test
  public void salary() {
    test("SALARY", "salary",
        OrmPropertyFake.Salary_employee,
        OrmPropertyFake.Salary_salary_,
        OrmPropertyFake.Salary_fromDate,
        OrmPropertyFake.Salary_toDate);
  }

  private void test(String table, String expected, OrmProperty... properties) {
    List<OrmProperty> propertyList = Arrays.asList(properties);
    QuerySelectExpression exp = new SingletonQuerySelectExpression(schemaIt(table), propertyList);
    ASSERT.that(exp.get()).hasToStringEqualTo("QuerySelectExpressionTest." + expected + ".poet");
  }

}