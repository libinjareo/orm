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

import static br.com.objectos.assertion.TestableAssertion.assertThat;

import br.com.objectos.orm.compiler.OrmPojoInfo;
import br.com.objectos.orm.compiler.OrmPojoInfoFake;
import br.com.objectos.orm.compiler.PojoInfoFake;
import br.com.objectos.pojo.plugin.PojoInfo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class OrmPojoInfoTest {

  @DataProvider
  public Object[][] ofProvider() {
    return new Object[][] {
      { PojoInfoFake.Pair, OrmPojoInfoFake.Pair }
    };
  }

  @Test(dataProvider = "ofProvider")
  public void of(PojoInfo pojoInfo, OrmPojoInfo expected) {
    OrmPojoInfo res = OrmPojoInfo.of(pojoInfo).get();
    assertThat(res).isEqualTo(expected);
  }

}