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
package br.com.objectos.orm.compiler;

import java.util.List;
import java.util.stream.Stream;

import br.com.objectos.collections.ImmutableList;
import br.com.objectos.pojo.plugin.OptionalPlugin;
import br.com.objectos.pojo.testing.PluginAssertion;

import org.testng.annotations.Test;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class SqlPojoCompilerTest {

  @Test
  public void employee() {
    test("Employee");
  }

  @Test
  public void enumerated() {
    test("Enumerated");
  }

  @Test
  public void enumeratedDuo() {
    test("EnumeratedDuo");
  }

  @Test
  public void pair() {
    test("Pair");
  }

  @Test
  public void salary() {
    test("Salary", "Employee");
  }

  private void test(String pojo, String... more) {
    List<String> with = ImmutableList.<String> builder()
        .add(pojo)
        .add(more)
        .add("DUO")
        .add("EMPLOYEE_V002")
        .add("EMPLOYEE")
        .add("OBJECTOS_SQL")
        .add("PAIR")
        .add("REVISION_V003")
        .add("SALARY")
        .add("V001__First_Migration")
        .add("V002__Employee_Salary")
        .add("V003__Revision")
        .add("V004__More")
        .build();
    PluginAssertion.assertThat(
        new ColumnPropertyPlugin(),
        new InsertablePlugin(),
        new OptionalPlugin())
        .with(with.toArray(new String[] {}))
        .generates(generates(pojo, "Pojo", "Builder", "BuilderPojo"));
  }

  private String[] generates(String pojo, String... suffixes) {
    return Stream.of(suffixes).map(suffix -> pojo + suffix).toArray(String[]::new);
  }

}