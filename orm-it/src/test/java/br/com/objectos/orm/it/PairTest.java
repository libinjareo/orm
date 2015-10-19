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

import static br.com.objectos.assertion.TestableAssertion.assertThat;

import org.testng.annotations.Test;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class PairTest {

  @Test
  public void isEqualTo() {
    Pair a = p(1, "a");
    Pair b = p(1, "a");
    assertThat(a).isEqualTo(b);
  }

  private Pair p(int id, String name) {
    return Pair.builder()
        .id(id)
        .name(name)
        .build();
  }

}