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
import java.util.stream.Collectors;

import javax.lang.model.element.Modifier;

import br.com.objectos.orm.InsertableRowBinder;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.pojo.plugin.Contribution;
import br.com.objectos.schema.info.TableInfoAnnotationInfo;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class IsOrmInsertable implements OrmInsertable {

  abstract TableInfoAnnotationInfo tableInfo();
  abstract ParameterizedTypeName insertableRowTypeName();
  abstract ParameterizedTypeName insertableRowValuesTypeName();
  abstract List<String> valueNameList();
  abstract List<String> generatedKeyListenerNameList();

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
  public void acceptCompanionType(CompanionType companion, TypeSpec.Builder type) {
    type.addMethod(companion.insertAll());
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
    String generated = generatedKeyListenerNameList().stream().collect(Collectors.joining(", "));
    if (!generated.isEmpty()) {
      generated = ".onGeneratedKey(" + generated + ")";
    }
    return MethodSpec.methodBuilder("bindInsertableRow")
        .addAnnotation(Override.class)
        .addModifiers(Modifier.PUBLIC)
        .addParameter(insertableRowTypeName(), "row")
        .returns(insertableRowValuesTypeName())
        .addStatement("return row.values($L)$L",
            valueNameList().stream().collect(Collectors.joining(", ")),
            generated)
        .build();
  }

  private TypeName superinterface() {
    ClassName rawType = ClassName.get(InsertableRowBinder.class);
    return ParameterizedTypeName.get(rawType, insertableRowTypeName(), insertableRowValuesTypeName());
  }

}