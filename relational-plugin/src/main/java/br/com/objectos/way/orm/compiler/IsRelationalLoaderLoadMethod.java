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
package br.com.objectos.way.orm.compiler;

import br.com.objectos.code.AnnotationInfo;
import br.com.objectos.schema.info.TableInfoAnnotationInfo;
import br.com.objectos.schema.meta.ColumnName;

import com.squareup.javapoet.CodeBlock;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
enum IsRelationalLoaderLoadMethod implements OrmPropertyAdapter<CodeBlock> {

  INSTANCE;

  @Override
  public CodeBlock onColumn(ColumnOrmProperty property) {
    TableInfoAnnotationInfo tableInfo = property.tableInfo();
    AnnotationInfo columnAnnotationInfo = property.columnAnnotationInfo();
    return CodeBlock.builder()
        .add(",\n    $T.get().$L($L(rs, $S))",
            tableInfo.className(), columnAnnotationInfo.simpleName(),
            name(property), property.columnSimpleName())
        .build();
  }

  @Override
  public CodeBlock onForeignKey(ForeignKeyOrmProperty property) {
    String columnName = property.columnAnnotationClassList().get(0)
        .typeInfo()
        .flatMap(t -> t.annotationInfo(ColumnName.class))
        .flatMap(ann -> ann.stringValue("value"))
        .get();
    return CodeBlock.builder()
        .add(",\n    $L(rs, $S)", name(property), columnName)
        .build();
  }

  private String name(OrmProperty property) {
    return property.property().name();
  }

}