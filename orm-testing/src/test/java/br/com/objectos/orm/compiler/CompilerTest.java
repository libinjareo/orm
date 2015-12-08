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

import static br.com.objectos.pojo.testing.PluginAssertion.assertThat;

import java.util.List;
import java.util.stream.Stream;

import br.com.objectos.collections.ImmutableList;
import br.com.objectos.pojo.plugin.OptionalPlugin;
import br.com.objectos.pojo.plugin.Plugin;

import org.testng.annotations.Test;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class CompilerTest {

  @Test
  public void booleanInt() {
    assertThat(plugins())
        .with(with("BooleanInt"))
        .generates("BooleanIntPojo");
  }

  @Test
  public void employee() {
    test("Employee", "Salary");
  }

  @Test
  public void enumerated() {
    testRelational("Enumerated");
  }

  @Test
  public void enumeratedDuo() {
    testRelational("EnumeratedDuo");
  }

  @Test
  public void merge() {
    testRelational("Merge", "Revision");
  }

  @Test
  public void pair() {
    testRelational("Pair");
  }

  @Test
  public void revision() {
    test("Revision");
  }

  @Test
  public void salary() {
    test("Salary", "Employee");
  }

  private void test(String pojo, String... more) {
    assertThat(plugins())
        .with(with(pojo, more))
        .generates(generates(pojo, "Pojo", "Builder", "BuilderPojo", "Orm"));
  }

  private void testRelational(String pojo, String... more) {
    assertThat(plugins())
        .with(with(pojo, more))
        .generates(
            pojo + "Pojo",
            pojo + "Builder",
            pojo + "BuilderPojo",
            pojo + "Orm",
            "Abstract" + pojo + "Loader");
  }

  private String[] generates(String pojo, String... suffixes) {
    return Stream.of(suffixes).map(suffix -> pojo + suffix).toArray(String[]::new);
  }

  private Plugin[] plugins() {
    return new Plugin[] {
      new ColumnOrmPropertyPlugin(),
      new CompanionTypePlugin(),
      new ConstructorPlugin(),
      new InjectPlugin(),
      new InsertablePlugin(),
      new OptionalPlugin(),
      new QueryPlugin(),
      new RelationalInsertablePlugin(),
      new RelationalLoaderPlugin(),
      new SetterPlugin()
    };
  }

  private String[] with(String pojo, String... more) {
    List<String> with = ImmutableList.<String> builder()
        .add(pojo)
        .add(more)
        .add("DUO")
        .add("EMPLOYEE_V002")
        .add("EMPLOYEE")
        .add("MERGE")
        .add("OBJECTOS_ORM")
        .add("PAIR")
        .add("REVISION_V003")
        .add("REVISION")
        .add("SALARY")
        .add("V001__First_Migration")
        .add("V002__Employee_Salary")
        .add("V003__Revision")
        .add("V004__More")
        .build();
    return with.toArray(new String[] {});
  }

}