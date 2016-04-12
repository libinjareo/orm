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
package br.com.objectos.way.schema.info;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class MigrationVersionFake {

  public static final MigrationVersion V001 = MigrationVersion.builder()
      .prefix("V001")
      .build();
  public static final MigrationVersion V002 = MigrationVersion.builder()
      .prefix("V002")
      .build();
  public static final MigrationVersion V003 = MigrationVersion.builder()
      .prefix("V003")
      .build();

  private MigrationVersionFake() {
  }

}