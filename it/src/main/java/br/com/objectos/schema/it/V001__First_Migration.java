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

import br.com.objectos.schema.annotation.AutoIncrement;
import br.com.objectos.schema.annotation.Length;
import br.com.objectos.schema.annotation.Migration;
import br.com.objectos.schema.annotation.Name;
import br.com.objectos.schema.annotation.NotNull;
import br.com.objectos.schema.annotation.PrimaryKey;
import br.com.objectos.schema.annotation.Table;
import br.com.objectos.schema.annotation.Unsigned;
import br.com.objectos.schema.type.TinyIntColumn;
import br.com.objectos.schema.type.VarcharColumn;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Migration(schema = sakila.class)
public class V001__First_Migration {

  @Table
  interface category {

    @PrimaryKey
    @AutoIncrement
    @NotNull
    @Unsigned
    TinyIntColumn category_id();

    @Name("name")
    @NotNull
    @Length(25)
    VarcharColumn name_();

  }

}