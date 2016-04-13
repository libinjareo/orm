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

import br.com.objectos.db.Result;
import br.com.objectos.orm.compiler.ColumnOrmProperty;
import br.com.objectos.orm.compiler.ForeignKeyOrmProperty;
import br.com.objectos.orm.compiler.GenerationType;
import br.com.objectos.orm.compiler.GenerationTypeAdapter;
import br.com.objectos.orm.compiler.OptionalReturnType;
import br.com.objectos.orm.compiler.OrmProperty;
import br.com.objectos.orm.compiler.OrmPropertyAdapter;
import br.com.objectos.orm.compiler.ReturnType;
import br.com.objectos.orm.compiler.ReturnTypeAdapter;
import br.com.objectos.orm.compiler.StandardReturnType;
import br.com.objectos.pojo.plugin.Contribution;
import br.com.objectos.schema.info.TableName;
import br.com.objectos.schema.meta.ColumnName;

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
    ReturnType returnType = property.returnType();
    return returnType.adapt(new ReturnTypeAdapterPojo(property));
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
    String qualifiedTableName = tableName.qualifiedName();
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
      CodeBlock.Builder code = CodeBlock.builder();

      if (size == 1) {
        code.add("    .value($S, ($T) null)\n",
            property.columnSimpleName(),
            property.property().returnTypeInfo().autobox().className());
      }

      return build(code.add("    .on(rs -> $L.onGeneratedKey($T.of(rs)))",
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

  private class ReturnTypeAdapterPojo implements ReturnTypeAdapter<CodeBlock> {

    private final String fieldName;
    private final String columnName;
    private final String propertyAccessor;
    private final String bindTypeAccessor;

    public ReturnTypeAdapterPojo(ForeignKeyOrmProperty property) {
      fieldName = property.property().name();
      columnName = property.columnAnnotationClassList().get(0)
          .typeInfo()
          .flatMap(t -> t.annotationInfo(ColumnName.class))
          .flatMap(ann -> ann.stringValue("value"))
          .get();
      ColumnOrmProperty refMethod = property.referencedPropertyList().get(0);
      propertyAccessor = refMethod.property().accessorName();
      bindTypeAccessor = refMethod.bindType().accessor();
    }

    @Override
    public CodeBlock onOptional(OptionalReturnType returnType) {
      return build(CodeBlock.builder()
          .add("    .value($S, $L.isPresent() ? $L.get().$L()$L : null)",
              columnName, fieldName, fieldName, propertyAccessor, bindTypeAccessor));
    }

    @Override
    public CodeBlock onStandard(StandardReturnType returnType) {
      return build(CodeBlock.builder()
          .add("    .value($S, $L.$L()$L)",
              columnName, fieldName, propertyAccessor, bindTypeAccessor));
    }

  }

}