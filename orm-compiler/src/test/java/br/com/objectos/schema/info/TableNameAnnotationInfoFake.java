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

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class TableNameAnnotationInfoFake {

  private static final SchemaName OBJECTOS_ORM = SchemaName.builder()
      .simpleName("OBJECTOS_ORM")
      .build();

  public static final TableName PAIR = TableName.builder()
      .schemaName(OBJECTOS_ORM)
      .simpleName("PAIR")
      .migrationVersion(MigrationVersionFake.V001)
      .build();
  public static final TableName REVISION = TableName.builder()
      .schemaName(OBJECTOS_ORM)
      .simpleName("REVISION")
      .migrationVersion(MigrationVersionFake.V003)
      .build();
  public static final TableName SALARY = TableName.builder()
      .schemaName(OBJECTOS_ORM)
      .simpleName("SALARY")
      .migrationVersion(MigrationVersionFake.V002)
      .build();

  private TableNameAnnotationInfoFake() {
  }

}