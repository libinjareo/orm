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
package br.com.objectos.orm;

import br.com.objectos.db.core.SqlException;
import br.com.objectos.db.core.SqlRuntimeException;
import br.com.objectos.db.core.Transaction;
import br.com.objectos.sql.query.InsertQuery;
import br.com.objectos.sql.query.InsertableRow;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public interface Orm {

  default int execute(InsertableRow.Values insert) throws SqlException {
    Transaction trx = startTransaction();
    InsertQuery query = insert.compile(trx.dialect());
    int res = query.execute(trx);
    trx.commit();
    return res;
  }

  default int executeUnchecked(InsertableRow.Values insert) {
    try {
      return execute(insert);
    } catch (SqlException e) {
      throw new SqlRuntimeException(e);
    }
  }

  Transaction startTransaction() throws SqlException;

}