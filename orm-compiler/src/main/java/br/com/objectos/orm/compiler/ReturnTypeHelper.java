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

import java.util.Optional;

import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.code.TypeParameterInfo;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
abstract class ReturnTypeHelper {

  final SimpleTypeInfo returnTypeInfo;

  public ReturnTypeHelper(SimpleTypeInfo returnTypeInfo) {
    this.returnTypeInfo = returnTypeInfo;
  }

  public static ReturnTypeHelper of(SimpleTypeInfo returnTypeInfo) {
    return returnTypeInfo.isInfoOf(Optional.class)
        ? OptionalHelper.get(returnTypeInfo)
        : StandardHelper.get(returnTypeInfo);
  }

  public BindType bindType(TypeInfo columnClassTypeInfo) {
    return BindType.of(returnTypeInfo, columnClassTypeInfo);
  }

  public abstract ReturnType returnType();

  private static class OptionalHelper extends ReturnTypeHelper {

    private OptionalHelper(SimpleTypeInfo returnTypeInfo) {
      super(returnTypeInfo);
    }

    public static ReturnTypeHelper get(SimpleTypeInfo returnTypeInfo) {
      SimpleTypeInfo enclosedTypeInfo = returnTypeInfo.getTypeParameterInfoStream()
          .findFirst()
          .map(TypeParameterInfo::simpleTypeInfo)
          .get();
      return new OptionalHelper(enclosedTypeInfo);
    }

    @Override
    public ReturnType returnType() {
      return OptionalReturnType.get(returnTypeInfo);
    }

  }

  private static class StandardHelper extends ReturnTypeHelper {

    private StandardHelper(SimpleTypeInfo returnTypeInfo) {
      super(returnTypeInfo);
    }

    public static ReturnTypeHelper get(SimpleTypeInfo returnTypeInfo) {
      return new StandardHelper(returnTypeInfo);
    }

    @Override
    public ReturnType returnType() {
      return StandardReturnType.get(returnTypeInfo);
    }

  }

}