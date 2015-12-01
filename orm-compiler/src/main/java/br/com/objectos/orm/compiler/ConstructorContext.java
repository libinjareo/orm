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

import br.com.objectos.code.ConstructorInfo;
import br.com.objectos.code.ParameterInfo;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.pojo.plugin.Contribution;

import com.squareup.javapoet.ParameterSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class ConstructorContext {

  abstract OrmPojoInfo pojoInfo();
  abstract OrmInject inject();
  abstract ConstructorInfo constructorInfo();
  abstract List<ParameterSpec> parameterSpecList();

  ConstructorContext() {
  }

  public static ConstructorContext of(OrmPojoInfo pojoInfo, ConstructorInfo constructorInfo) {
    return ConstructorContext.builder()
        .pojoInfo(pojoInfo)
        .inject(pojoInfo.inject())
        .constructorInfo(constructorInfo)
        .parameterSpecList(constructorInfo.parameterInfoStream()
            .map(ParameterInfo::parameterSpec)
            .collect(Collectors.toList()))
        .build();
  }

  private static ConstructorContextBuilder builder() {
    return new ConstructorContextBuilderPojo();
  }

  public void accept(Contribution.Builder builder) {
    builder
        .addMethod(RowConstructor.of(this).execute())
        .addMethod(ColumnsConstructor.of(this).execute());

    if (!pojoInfo().foreignKeyPropertyList().isEmpty()) {
      builder
          .addMethod(ForeignKeyRowConstructor.of(this).execute())
          .addMethod(ForeignKeyColumnsConstructor.of(this).execute());
    }
  }

}