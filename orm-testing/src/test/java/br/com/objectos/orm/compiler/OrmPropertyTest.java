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

import static br.com.objectos.assertion.OptionalAssertion.assertThat;

import java.util.Optional;

import br.com.objectos.pojo.plugin.Property;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class OrmPropertyTest {

  @DataProvider
  public Object[][] ofProvider() {
    return new Object[][] {
      { PropertyFake.Pair_id, OrmPropertyFake.Pair_id },
      { PropertyFake.Pair_name, OrmPropertyFake.Pair_name },
      { PropertyFake.Salary_employee, OrmPropertyFake.Salary_employee }
    };
  }

  @DataProvider
  public Object[][] notPropertyProvider() {
    return new Object[][] {
      { PropertyFake.Salary_orm }
    };
  }

  @Test(dataProvider = "ofProvider")
  public void of(Property property, OrmProperty expected) {
    Optional<OrmProperty> res = OrmProperty.of(property);
    assertThat(res).isPresent().isEqualTo(expected);
  }

  @Test(dataProvider = "notPropertyProvider")
  public void notProperty(Property property) {
    Optional<OrmProperty> res = OrmProperty.of(property);
    assertThat(res).isEmpty();
  }

}