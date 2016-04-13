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

import br.com.objectos.code.AnnotationProcessorAssert;
import br.com.objectos.core.util.ImmutableList;
import br.com.objectos.orm.compiler.RepoCompiler;

import org.testng.annotations.Test;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class RepoCompilerTest {

  @Test
  public void pair() {
    test("Pair");
  }

  private void test(String pojo, String... more) {
    AnnotationProcessorAssert
        .using(new RepoCompiler())
        .dir("code/pojo-plugin")
        .pack("br.com.objectos.pojo.plugin")
        .input(input(pojo, more))
        .output(pojo + "RepoRepo")
        .verify();
  }

  private String[] input(String pojo, String... more) {
    List<String> with = ImmutableList.<String> builder()
        .add(pojo)
        .add(pojo + "Repo")
        .add(pojo + "Orm")
        .add(pojo + "Pojo")
        .add(pojo + "Builder")
        .add(pojo + "BuilderPojo")
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