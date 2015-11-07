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

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.orm.Insertable;
import br.com.objectos.pojo.plugin.Contribution;
import br.com.objectos.pojo.plugin.PojoInfo;
import br.com.objectos.schema.info.TableInfoAnnotationInfo;
import br.com.objectos.testable.Testable;

import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
interface OrmInsertable extends Testable {

  static OrmInsertable of(PojoInfo pojoInfo, List<OrmProperty> propertyList) {
    if (!pojoInfo.instanceOf(Insertable.class)) {
      return NotOrmInsertable.INSTANCE;
    }

    Set<Entry<TableInfoAnnotationInfo, List<OrmProperty>>> entrySet = propertyList.stream()
        .collect(Collectors.groupingBy(OrmProperty::tableClassInfo))
        .entrySet();

    if (entrySet.size() != 1) {
      return NotOrmInsertable.INSTANCE;
    }

    Entry<TableInfoAnnotationInfo, List<OrmProperty>> entry = entrySet.iterator().next();
    return ofEntry(entry.getKey(), entry.getValue());
  }

  static OrmInsertable ofEntry(TableInfoAnnotationInfo tableClassInfo, List<OrmProperty> propertyList) {
    List<SimpleTypeInfo> columnAnnotationClassList = propertyList.stream()
        .filter(property -> !property.isGenerated())
        .flatMap(m -> m.columnAnnotationClassList().stream())
        .collect(Collectors.toList());
    return tableClassInfo.containsAll(columnAnnotationClassList)
        ? IsOrmInsertable.of(tableClassInfo, propertyList)
        : NotOrmInsertable.INSTANCE;
  }

  void acceptCompanionType(CompanionType companion, TypeSpec.Builder type);

  void acceptInsertAll(Builder insertAll);

  Contribution execute();

}