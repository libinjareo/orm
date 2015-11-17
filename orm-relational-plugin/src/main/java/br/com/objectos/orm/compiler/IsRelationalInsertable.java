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

import javax.lang.model.element.Modifier;

import br.com.objectos.db.query.Result;
import br.com.objectos.pojo.plugin.Contribution;
import br.com.objectos.schema.info.TableName;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class IsRelationalInsertable implements OrmPropertyAdapter<CodeBlock>, RelationalInsertable {

  private final TableName tableName;
  private final List<OrmProperty> propertyList;
  private final int size;

  private int index = 0;

  public IsRelationalInsertable(TableName tableName, List<OrmProperty> propertyList) {
    this.tableName = tableName;
    this.propertyList = propertyList;
    size = propertyList.size();
  }

  @Override
  public Contribution execute() {
    return Contribution.builder()
        .addMethod(getInsert())
        .build();
  }

  @Override
  public CodeBlock onColumn(ColumnOrmProperty property) {
    GenerationType generationType = property.generationType();
    return generationType.adapt(new GenerationTypeAdapterPojo(property));
  }

  @Override
  public CodeBlock onForeignKey(ForeignKeyOrmProperty property) {
    return build(CodeBlock.builder());
  }

  private CodeBlock build(CodeBlock.Builder builder) {
    if (++index == size) {
      builder.add(";");
    }
    return builder.add("\n")
        .build();
  }

  private MethodSpec getInsert() {
    ClassName insertClassName = ClassName.get("br.com.objectos.way.relational", "Insert");
    String qualifiedTableName = tableName.schemaSimpleName() + "." + tableName.simpleName();
    MethodSpec.Builder builder = MethodSpec.methodBuilder("getInsert")
        .addAnnotation(Override.class)
        .addModifiers(Modifier.PUBLIC)
        .returns(ClassName.get("br.com.objectos.way.relational", "Insert"))
        .addCode("return $T.into($S)\n", insertClassName, qualifiedTableName);

    for (OrmProperty property : propertyList) {
      builder.addCode(property.adapt(this));
    }

    return builder.build();
  }

  private class GenerationTypeAdapterPojo implements GenerationTypeAdapter<CodeBlock> {

    private final ColumnOrmProperty property;

    public GenerationTypeAdapterPojo(ColumnOrmProperty property) {
      this.property = property;
    }

    @Override
    public CodeBlock onAutoIncrement() {
      return build(CodeBlock.builder()
          .add("    .on(rs -> $L.onGeneratedKey($T.of(rs)))",
              property.property().name(),
              Result.class));
    }

    @Override
    public CodeBlock onNone() {
      return build(CodeBlock.builder()
          .add("    .value($S, $L.getWrapped())",
              property.columnSimpleName(),
              property.property().name()));
    }

  }

}