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

import java.util.Map;

import br.com.objectos.code.TypeInfo;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class TypeInfoFake {

  public static final Map<String, TypeInfo> qualifiedNameMap = TestingProcessor.loadAll(
      TestingProcessor::new,
      "code/testing",
      "OBJECTOS_SQL.java",
      "V001__First_Migration.java",
      "Pair.java",
      "PAIR.java");

  public static final TypeInfo OBJECTOS_SQL = get("OBJECTOS_SQL");
  public static final TypeInfo V001__FIRST_MIGRATION = get("V001__First_Migration");
  public static final TypeInfo V001__PAIR = get("V001__First_Migration.PAIR");
  public static final TypeInfo Pair = get("Pair");
  public static final TypeInfo PAIR = get("PAIR");
  public static final TypeInfo PAIR_ID = get("PAIR.ID");
  public static final TypeInfo PAIR_NAME = get("PAIR.NAME");

  private TypeInfoFake() {
  }

  private static TypeInfo get(String name) {
    TypeInfo typeInfo = qualifiedNameMap.get("br.com.objectos.schema.it." + name);

    if (typeInfo == null) {
      throw new NullPointerException(name);
    }

    return typeInfo;
  }

}