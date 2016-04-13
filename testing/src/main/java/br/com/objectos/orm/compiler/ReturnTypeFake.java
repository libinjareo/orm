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
package br.com.objectos.orm.compiler;

import java.time.LocalDate;

import br.com.objectos.orm.compiler.StandardReturnType;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class ReturnTypeFake {

  public static final StandardReturnType EMPLOYEE = StandardReturnType.builder()
      .typeName(TypeInfoFake.Employee.typeName())
      .companionTypeClassNameOf(TypeInfoFake.Employee.classNameSuffix("Orm"))
      .build();
  public static final StandardReturnType INT = StandardReturnType.builder()
      .typeName(TypeName.INT)
      .companionTypeClassName()
      .build();
  public static final StandardReturnType LOCAL_DATE = StandardReturnType.builder()
      .typeName(TypeName.get(LocalDate.class))
      .companionTypeClassNameOf(orm(LocalDate.class))
      .build();
  public static final StandardReturnType STRING = StandardReturnType.builder()
      .typeName(TypeName.get(String.class))
      .companionTypeClassNameOf(orm(String.class))
      .build();

  private ReturnTypeFake() {
  }

  private static ClassName orm(Class<?> type) {
    ClassName className = ClassName.get(type);
    return className.peerClass(className.simpleName() + "Orm");
  }

}