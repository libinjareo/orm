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

import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.code.SimpleTypePrimitives;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.orm.EnumSql;
import br.com.objectos.pojo.plugin.PojoProperty;
import br.com.objectos.schema.meta.EnumColumn;
import br.com.objectos.schema.meta.EnumType;
import br.com.objectos.schema.meta.ValueType;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
enum ColumnSqlPojoBindType {

  BOOLEAN_INT {
    @Override
    public PojoProperty method(ColumnSqlPojoMethod method) {
      return method.methodWriter("return $L.booleanValue()")
          .setPropertyName()
          .build();
    }

    @Override
    String accessor() {
      return "";
    }
  },

  ENUM_ORDINAL {
    @Override
    public PojoProperty method(ColumnSqlPojoMethod method) {
      return method.methodWriter("return $T.values()[$L.get()]")
          .setReturnTypeName()
          .setPropertyName()
          .build();
    }

    @Override
    String accessor() {
      return ".ordinal()";
    }
  },

  ENUM_SQL {
    @Override
    public PojoProperty method(ColumnSqlPojoMethod method) {
      return method.methodWriter("return $T.load($L.get())")
          .setReturnTypeName()
          .setPropertyName()
          .build();
    }

    @Override
    String accessor() {
      return ".sqlValue()";
    }
  },

  ENUM_STRING {
    @Override
    public PojoProperty method(ColumnSqlPojoMethod method) {
      return method.methodWriter("return $T.valueOf($L.get())")
          .setReturnTypeName()
          .setPropertyName()
          .build();
    }

    @Override
    String accessor() {
      return ".name()";
    }
  },

  STANDARD {
    @Override
    public PojoProperty method(ColumnSqlPojoMethod method) {
      return method.methodWriter("return $L.get()")
          .setPropertyName()
          .build();
    }

    @Override
    String accessor() {
      return "";
    }
  };

  public static ColumnSqlPojoBindType of(SimpleTypeInfo returnTypeInfo, TypeInfo columnClassTypeInfo) {
    return returnTypeInfo.isEnum()
        ? ofEnum(returnTypeInfo, columnClassTypeInfo)
        : ofStandard(returnTypeInfo, columnClassTypeInfo);
  }

  private static ColumnSqlPojoBindType ofBoolean(SimpleTypeInfo returnTypeInfo, TypeInfo columnClassTypeInfo) {
    String simpleName = columnClassTypeInfo.annotationInfo(ValueType.class)
        .flatMap(ann -> ann.simpleTypeInfoValue("value"))
        .map(SimpleTypeInfo::simpleName)
        .orElse("");
    return "int".equals(simpleName)
        ? BOOLEAN_INT
        : STANDARD;
  }

  private static ColumnSqlPojoBindType ofEnum(SimpleTypeInfo returnTypeInfo, TypeInfo columnClassTypeInfo) {
    EnumType enumType = columnClassTypeInfo.annotationInfo(EnumColumn.class)
        .flatMap(ann -> ann.enumConstantInfoValue("value"))
        .map(info -> info.getEnumValue(EnumType.class))
        .orElse(EnumType.VOID);

    switch (enumType) {
    default:
    case ORDINAL:
      return ENUM_ORDINAL;

    case STRING:
      return returnTypeInfo.typeInfo().get().getInterface(EnumSql.class).isPresent()
          ? ENUM_SQL
          : ENUM_STRING;
    }
  }

  private static ColumnSqlPojoBindType ofStandard(SimpleTypeInfo returnTypeInfo, TypeInfo columnClassTypeInfo) {
    return returnTypeInfo.equals(SimpleTypePrimitives.BOOLEAN)
        ? ofBoolean(returnTypeInfo, columnClassTypeInfo)
        : STANDARD;
  }

  public PojoProperty constructorStatement(ColumnSqlPojoMethod method) {
    return method.constructorStatementWriter(constructorCode())
        .setPropertyName()
        .setTableClassName()
        .setColumnAnnotationSimpleName()
        .setBuilderGet()
        .build();
  }

  public abstract PojoProperty method(ColumnSqlPojoMethod method);

  abstract String accessor();

  private String constructorCode() {
    return String.format("$L = $T.get().$L($L%s)", accessor());
  }

}