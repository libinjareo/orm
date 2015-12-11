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

import br.com.objectos.orm.InsertableRowBinder;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.pojo.plugin.Contribution;
import br.com.objectos.pojo.plugin.Naming;
import br.com.objectos.schema.info.TableInfoAnnotationInfo;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class IsOrmInsertable implements OrmInsertable {

  abstract TableInfoAnnotationInfo tableInfo();
  abstract ParameterizedTypeName insertableRowTypeName();
  abstract ParameterizedTypeName insertableRowValuesTypeName();
  abstract InsertableRowExpression insertableRowExpression();

  IsOrmInsertable() {
  }

  public static IsOrmInsertable of(TableInfoAnnotationInfo tableClassInfo, List<OrmProperty> propertyList) {
    IsOrmInsertableHelper helper = IsOrmInsertableHelper.get();

    for (OrmProperty property : propertyList) {
      property.acceptIsOrmInsertableHelper(helper);
    }

    return helper.build(tableClassInfo);
  }

  static IsOrmInsertableBuilder builder() {
    return new IsOrmInsertableBuilderPojo();
  }

  @Override
  public CompanionType acceptCompanionType(CompanionType type) {
    return type.addMethod(companionTypeInsertAll(type));
  }

  @Override
  public void acceptInsertAll(MethodSpec.Builder insertAll) {
    insertAll
        .addCode(tableInfo().tableGetCode())
        .addStatement("$T insert", insertableRowValuesTypeName())
        .addCode("insert = pojo.bindInsertableRow(")
        .addCode(tableInfo().insertIntoCode())
        .addCode(");\n");
  }

  @Override
  public Contribution execute() {
    return Contribution.builder()
        .addSuperinterface(superinterface())
        .addMethod(bindInsertableRow())
        .build();
  }

  private MethodSpec bindInsertableRow() {
    return MethodSpec.methodBuilder("bindInsertableRow")
        .addAnnotation(Override.class)
        .addModifiers(Modifier.PUBLIC)
        .addParameter(insertableRowTypeName(), "row")
        .returns(insertableRowValuesTypeName())
        .addCode(insertableRowExpression().get())
        .build();
  }

  private MethodSpec companionTypeInsertAll(CompanionType type) {
    Naming naming = type.naming();
    OrmInject inject = type.inject();
    return MethodSpec.methodBuilder("insertAll")
        .addModifiers(Modifier.PUBLIC)
        .addParameter(OrmNaming.iterableOf(naming.superClassTypeName()), "entities")
        .addStatement("$T iterator = entities.iterator()", OrmNaming.iteratorOf(naming.superClassTypeName()))
        .addCode(CodeBlock.builder()
            .beginControlFlow("if (!iterator.hasNext())")
            .addStatement("return")
            .endControlFlow()
            .build())
        .addStatement("$1T pojo = ($1T) iterator.next()", naming.pojo())
        .addCode(tableInfo().tableGetCode())
        .addStatement("$T insert", insertableRowValuesTypeName())
        .addCode("insert = pojo.bindInsertableRow(")
        .addCode(tableInfo().insertIntoCode())
        .addCode(");\n")
        .addCode(CodeBlock.builder()
            .beginControlFlow("while(iterator.hasNext())")
            .addStatement("pojo = ($T) iterator.next()", naming.pojo())
            .addStatement("insert = pojo.bindInsertableRow(insert)")
            .endControlFlow()
            .build())
        .addStatement("$L.executeUnchecked(insert)", inject.name())
        .build();
  }

  private TypeName superinterface() {
    ClassName rawType = ClassName.get(InsertableRowBinder.class);
    return ParameterizedTypeName.get(rawType, insertableRowTypeName(), insertableRowValuesTypeName());
  }

}