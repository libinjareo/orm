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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

import br.com.objectos.core.util.ImmutableMap;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
enum RelationalTypeName {

  STRING("getString", TypeName.get(String.class)),

  BOOLEAN("getBoolean", TypeName.BOOLEAN),

  INT("getInt", TypeName.INT),

  LONG("getLong", TypeName.LONG),

  FLOAT("getFloat", TypeName.FLOAT),

  DOUBLE("getDouble", TypeName.DOUBLE),

  JODA_LOCAL_DATE("getLocalDate", ClassName.get("org.joda.time", "LocalDate")),

  DATE("getDate", TypeName.get(Date.class)),

  JODA_DATE_TIME("getDateTime", ClassName.get("org.joda.time", "DateTime")),

  BIG_DECIMAL("getBigDecimal", TypeName.get(BigDecimal.class)),

  LOCAL_DATE("localDate", TypeName.get(LocalDate.class)),

  LOCAL_DATE_TIME("localDateTime", TypeName.get(LocalDateTime.class));

  private static final Map<String, RelationalTypeName> CLASS_NAME_MAP = ImmutableMap.<String, RelationalTypeName> builder()
      .put(String.class.getName(), STRING)
      .put(boolean.class.getName(), BOOLEAN)
      .put(Boolean.class.getName(), BOOLEAN)
      .put(int.class.getName(), INT)
      .put(Integer.class.getName(), INT)
      .put(long.class.getName(), LONG)
      .put(Long.class.getName(), LONG)
      .put(float.class.getName(), FLOAT)
      .put(Float.class.getName(), FLOAT)
      .put(double.class.getName(), DOUBLE)
      .put(Double.class.getName(), DOUBLE)
      .put("org.joda.time.LocalDate", JODA_LOCAL_DATE)
      .put(Date.class.getName(), DATE)
      .put("org.joda.time.DateTime", JODA_DATE_TIME)
      .put(BigDecimal.class.getName(), BIG_DECIMAL)
      .put(LocalDate.class.getName(), LOCAL_DATE)
      .put(LocalDateTime.class.getName(), LOCAL_DATE_TIME)
      .build();

  private final String resultSetMethod;
  private final TypeName typeName;

  private RelationalTypeName(String resultSetMethod, TypeName typeName) {
    this.resultSetMethod = resultSetMethod;
    this.typeName = typeName;
  }

  public static RelationalTypeName of(TypeName typeName) {
    String qualifiedName = typeName.toString();
    return CLASS_NAME_MAP.get(qualifiedName);
  }

  public String resultSetMethod() {
    return resultSetMethod;
  }

  public TypeName typeName() {
    return typeName;
  }

}