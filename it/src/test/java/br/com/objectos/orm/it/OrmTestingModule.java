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

import javax.inject.Inject;

import br.com.objectos.db.Database;
import br.com.objectos.db.DatabaseConfig;
import br.com.objectos.db.SqlException;
import br.com.objectos.db.Transaction;
import br.com.objectos.db.mysql.Mysql;
import br.com.objectos.orm.Orm;
import br.com.objectos.orm.it.Model;

import com.google.inject.AbstractModule;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class OrmTestingModule extends AbstractModule {

  @Override
  protected void configure() {
    Database database = DatabaseConfig.builder()
        .dialect(Mysql.dialect())
        .server(System.getProperty("jdbc.server", "localhost"))
        .port(Integer.getInteger("jdbc.port", 9000))
        .db("OBJECTOS_ORM")
        .user(System.getProperty("jdbc.user", "tatu"))
        .password(System.getProperty("jdbc.password", "tatu"))
        .build()
        .connect();
    bind(Database.class).toInstance(database);
    bind(Orm.class).to(OrmImpl.class);
    bind(Model.class).to(OrmImpl.class);
  }

  private static class OrmImpl implements Orm, Model {

    private final Database db;

    @Inject
    public OrmImpl(Database db) {
      this.db = db;
    }

    @Override
    public Transaction startTransaction() throws SqlException {
      return db.startTransaction();
    }

  }

}