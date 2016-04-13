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

import br.com.objectos.assertion.TestableAssertion;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.way.orm.compiler.TypeInfoFake;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class TableNameAnnotationInfoTest {

  @DataProvider
  public Object[][] ofProvider() {
    return new Object[][] {
      { TypeInfoFake.PAIR, TableNameAnnotationInfoFake.PAIR },
      { TypeInfoFake.SALARY, TableNameAnnotationInfoFake.SALARY }
    };
  }

  @Test(dataProvider = "ofProvider")
  public void of(TypeInfo annotation, TableName expected) {
    TableName res = TableNameAnnotationInfo.of(annotation);
    TestableAssertion.assertThat(res).isEqualTo(expected);
  }

}