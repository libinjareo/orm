/*
 * Copyright 2015 Objectos, Fábrica de Software LTDA.
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

import br.com.objectos.code.MethodInfo;
import br.com.objectos.code.TypeInfo;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class MethodInfoFake {

  public static final MethodInfo V001__PAIR_ID = methodInfo(TypeInfoFake.V001__PAIR, "ID");
  public static final MethodInfo V001__PAIR_NAME = methodInfo(TypeInfoFake.V001__PAIR, "NAME");

  public static final MethodInfo Pair_ID = methodInfo(TypeInfoFake.Pair, "id");
  public static final MethodInfo Pair_NAME = methodInfo(TypeInfoFake.Pair, "name");

  private MethodInfoFake() {
  }

  private static MethodInfo methodInfo(TypeInfo typeInfo, String name) {
    return typeInfo.methodInfoStream()
        .filter(m -> m.name().equals(name))
        .findFirst()
        .get();
  }

}