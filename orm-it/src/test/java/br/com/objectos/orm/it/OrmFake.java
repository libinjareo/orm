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
package br.com.objectos.orm.it;

import br.com.objectos.db.core.Database;
import br.com.objectos.db.core.DatabaseConfig;
import br.com.objectos.db.core.SqlException;
import br.com.objectos.db.core.Transaction;
import br.com.objectos.db.mysql.Mysql;
import br.com.objectos.orm.Orm;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public enum OrmFake implements Orm {

  INSTANCE(DatabaseConfig.builder()
      .dialect(Mysql.dialect())
      .server(System.getProperty("jdbc.server", "localhost"))
      .port(9000)
      .db("OBJECTOS_ORM")
      .user("tatu")
      .password("tatu")
      .build()
      .connect());

  private final Database db;

  private OrmFake(Database db) {
    this.db = db;
  }

  @Override
  public Transaction startTransaction() throws SqlException {
    return db.startTransaction();
  }

}