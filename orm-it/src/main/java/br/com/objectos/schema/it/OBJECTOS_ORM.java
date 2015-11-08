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
package br.com.objectos.schema.it;

import br.com.objectos.schema.annotation.Schema;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Schema(migrations = {
  V001__First_Migration.class,
  V002__Employee_Salary.class,
  V003__Revision.class,
  V004__More.class
})
public class OBJECTOS_ORM {

  public static br.com.objectos.schema.it.PAIR PAIR() {
    return PAIR.get();
  }

  public static br.com.objectos.schema.it.REVISION REVISION() {
    return REVISION.get();
  }

}