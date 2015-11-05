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

import java.util.stream.Stream;

import br.com.objectos.collections.ImmutableList;

import com.squareup.javapoet.ClassName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class IsOrmInsertableHelper {

  private final ImmutableList.Builder<ClassName> columnClassNameList = ImmutableList.builder();
  private final ImmutableList.Builder<String> valueNameList = ImmutableList.builder();
  private final ImmutableList.Builder<String> generatedKeyListenerNameList = ImmutableList.builder();

  private IsOrmInsertableHelper() {
  }

  public static IsOrmInsertableHelper get() {
    return new IsOrmInsertableHelper();
  }

  public IsOrmInsertableHelper addColumnClassNameStream(Stream<ClassName> stream) {
    stream.forEach(columnClassNameList::add);
    return this;
  }

  public IsOrmInsertableHelper addGeneratedKeyListenerName(String valueName) {
    generatedKeyListenerNameList.add(valueName);
    return this;
  }

  public IsOrmInsertableHelper addValueName(String valueName) {
    valueNameList.add(valueName);
    return this;
  }

  public IsOrmInsertable build(TableClassInfo tableClassInfo) {
    ClassName[] columnClassNameArray = columnClassNameList.build().toArray(new ClassName[] {});
    return IsOrmInsertable.builder()
        .tableClassInfo(tableClassInfo)
        .insertableRowTypeName(OrmNaming.insertableRowTypeName(columnClassNameArray))
        .insertableRowValuesTypeName(OrmNaming.insertableRowValuesTypeName(columnClassNameArray))
        .valueNameList(valueNameList.build())
        .generatedKeyListenerNameList(generatedKeyListenerNameList.build())
        .build();
  }

}