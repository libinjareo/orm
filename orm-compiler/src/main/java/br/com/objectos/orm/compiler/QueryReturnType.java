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
package br.com.objectos.orm.compiler;

import java.util.List;
import java.util.Optional;

import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.code.TypeParameterInfo;
import br.com.objectos.collections.MoreCollectors;
import br.com.objectos.pojo.plugin.PojoInfo;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
enum QueryReturnType {

  LIST {
    @Override
    CodeBlock collect(CodeBlock code) {
      return CodeBlock.builder()
          .add("    .compile(trx.dialect())\n")
          .add("    .stream(trx)\n")
          .add("    .map(")
          .add(code)
          .add(")\n")
          .addStatement("    .collect($T.toImmutableList())", MoreCollectors.class)
          .build();
    }

    @Override
    TypeInfo pojoTypeInfo(SimpleTypeInfo returnTypeInfo) {
      return firstTypeVariable(returnTypeInfo);
    }

    @Override
    TypeName typeName(TypeName pojoTypeName) {
      ClassName rawType = ClassName.get(List.class);
      return ParameterizedTypeName.get(rawType, pojoTypeName);
    }
  },

  OPTIONAL {
    @Override
    CodeBlock collect(CodeBlock code) {
      return CodeBlock.builder()
          .add("    .findFirst()\n")
          .add("    .map(")
          .add(code)
          .addStatement(")")
          .build();
    }

    @Override
    TypeInfo pojoTypeInfo(SimpleTypeInfo returnTypeInfo) {
      return firstTypeVariable(returnTypeInfo);
    }

    @Override
    TypeName typeName(TypeName pojoTypeName) {
      ClassName rawType = ClassName.get(Optional.class);
      return ParameterizedTypeName.get(rawType, pojoTypeName);
    }
  },

  POJO {
    @Override
    CodeBlock collect(CodeBlock code) {
      throw new UnsupportedOperationException();
    }

    @Override
    TypeInfo pojoTypeInfo(SimpleTypeInfo returnTypeInfo) {
      return toPojoTypeInfo(returnTypeInfo);
    }
  };

  public static QueryReturnType of(SimpleTypeInfo returnTypeInfo) {
    return returnTypeInfo.isInfoOf(List.class)
        ? LIST
        : returnTypeInfo.isInfoOf(Optional.class)
            ? OPTIONAL
            : POJO;
  }

  public Optional<OrmPojoInfo> ormPojoInfo(SimpleTypeInfo returnTypeInfo) {
    TypeInfo pojoTypeInfo = pojoTypeInfo(returnTypeInfo);
    PojoInfo pojoInfo = PojoInfo.of(pojoTypeInfo);
    return OrmPojoInfo.of(pojoInfo);
  }

  abstract CodeBlock collect(CodeBlock code);

  TypeInfo firstTypeVariable(SimpleTypeInfo returnTypeInfo) {
    return returnTypeInfo.getTypeParameterInfoStream()
        .findFirst()
        .flatMap(TypeParameterInfo::typeInfo)
        .get();
  }

  abstract TypeInfo pojoTypeInfo(SimpleTypeInfo returnTypeInfo);

  TypeInfo toPojoTypeInfo(SimpleTypeInfo returnTypeInfo) {
    return returnTypeInfo.typeInfo().get();
  }

  TypeName typeName(TypeName pojoTypeName) {
    return pojoTypeName;
  }

}