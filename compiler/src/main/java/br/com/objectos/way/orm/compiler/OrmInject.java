/*
 * Copyright 2015 Objectos, Fábrica de Software LTDA.
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

import java.util.Optional;
import java.util.stream.Stream;

import javax.lang.model.element.Modifier;

import br.com.objectos.code.ConstructorInfo;
import br.com.objectos.code.ParameterInfo;
import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.pojo.plugin.Contribution;
import br.com.objectos.pojo.plugin.PojoInfo;
import br.com.objectos.pojo.plugin.Property;
import br.com.objectos.testable.Testable;
import br.com.objectos.way.orm.Orm;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
abstract class OrmInject implements Testable {

  OrmInject() {
  }

  public static boolean isNotOrm(ParameterInfo parameter) {
    return !isOrm(parameter);
  }

  public static boolean isOrm(ParameterInfo parameter) {
    SimpleTypeInfo simpleTypeInfo = parameter.simpleTypeInfo();
    return simpleTypeInfo.isSubType(Orm.class);
  }

  public static OrmInject of(PojoInfo pojoInfo) {
    return ofStream(pojoInfo.ignoredPropertyStream())
        .orElse(ofStream(pojoInfo.propertyStream())
            .orElse(ofConstructor(pojoInfo)
                .orElse(StandardOrmInject.INSTANCE)));
  }

  private static Optional<OrmInject> ofConstructor(PojoInfo pojoInfo) {
    return pojoInfo.constructorInfoStream()
        .flatMap(ConstructorInfo::parameterInfoStream)
        .filter(OrmInject::isOrm)
        .findFirst()
        .map(ConstructorOrmInject::of);
  }

  private static Optional<OrmInject> ofStream(Stream<Property> propertyStream) {
    return propertyStream
        .filter(property -> {
          SimpleTypeInfo returnTypeInfo = property.returnTypeInfo();
          return returnTypeInfo.isSubType(Orm.class);
        })
        .map(PropertyOrmInject::of)
        .findFirst();
  }

  public void acceptRepoConstructor(MethodSpec.Builder constructor) {
    constructor
        .addParameter(parameterSpec())
        .addStatement("this.$1L = $1L", name());
  }

  public CodeBlock assignToFieldStatement() {
    return CodeBlock.builder()
        .addStatement("this.$1L = $1L", name())
        .build();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof OrmInject)) {
      return false;
    }
    OrmInject that = (OrmInject) obj;
    return typeName().equals(that.typeName());
  }

  public abstract Contribution execute();

  public final FieldSpec fieldSpec() {
    return FieldSpec.builder(typeName(), name())
        .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
        .build();
  }

  @Override
  public int hashCode() {
    return typeName().hashCode();
  }

  public final ParameterSpec parameterSpec() {
    return ParameterSpec.builder(typeName(), name()).build();
  }

  abstract String name();

  abstract TypeName typeName();

}