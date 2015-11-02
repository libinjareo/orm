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

import java.util.Iterator;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class OrmNaming {

  private static final ClassName ITERABLE = ClassName.get(Iterable.class);
  private static final ClassName ITERATOR = ClassName.get(Iterator.class);

  private OrmNaming() {
  }

  public static ParameterizedTypeName insertableRowTypeName(ClassName... columnClassNameArray) {
    int size = columnClassNameArray.length;
    return ParameterizedTypeName.get(insertableRow(size), columnClassNameArray);
  }

  public static ParameterizedTypeName insertableRowValuesTypeName(ClassName... columnClassNameArray) {
    int size = columnClassNameArray.length;
    ClassName insertableRow = insertableRow(size);
    return ParameterizedTypeName.get(insertableRow.nestedClass("Values"), columnClassNameArray);
  }

  public static ParameterizedTypeName iterableOf(TypeName typeName) {
    return ParameterizedTypeName.get(ITERABLE, typeName);
  }

  public static ParameterizedTypeName iteratorOf(TypeName typeName) {
    return ParameterizedTypeName.get(ITERATOR, typeName);
  }

  private static ClassName insertableRow(int size) {
    return ClassName.get("br.com.objectos.sql.query", "InsertableRow" + size);
  }

}