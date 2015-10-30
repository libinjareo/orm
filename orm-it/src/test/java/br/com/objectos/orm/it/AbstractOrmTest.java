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
import br.com.objectos.db.core.SqlException;
import br.com.objectos.db.core.Transaction;
import br.com.objectos.orm.Orm;

import com.google.inject.Inject;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Guice(modules = OrmTestingModule.class)
public abstract class AbstractOrmTest {

  @Inject
  private Database db;
  @Inject
  private Orm orm;

  private Transaction trx;

  @BeforeMethod
  public void startTrx() throws SqlException {
    trx = db.startTransaction();
  }

  @AfterMethod(alwaysRun = true)
  public void rollbackTrx() throws SqlException {
    trx.rollback();
  }

  protected Orm orm() {
    return orm;
  }

  protected Transaction trx() {
    return trx;
  }

}