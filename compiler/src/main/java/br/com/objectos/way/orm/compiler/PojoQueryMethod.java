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

import java.util.Optional;

import br.com.objectos.code.MethodInfo;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.pojo.plugin.Contribution;
import br.com.objectos.pojo.plugin.PojoInfo;
import br.com.objectos.testable.Equality;
import br.com.objectos.testable.Testable;
import br.com.objectos.testable.Tester;

import com.squareup.javapoet.MethodSpec;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class PojoQueryMethod implements Testable {

  private final MethodInfo methodInfo;
  private final QueryReturnType returnType;
  private final TypeInfo pojoTypeInfo;

  PojoQueryMethod(MethodInfo methodInfo, QueryReturnType returnType, TypeInfo pojoTypeInfo) {
    this.methodInfo = methodInfo;
    this.returnType = returnType;
    this.pojoTypeInfo = pojoTypeInfo;
  }

  public static Optional<PojoQueryMethod> of(MethodInfo methodInfo) {
    return QueryMethod.of(methodInfo, PojoQueryMethod::new);
  }

  public void accept(OrmPojoInfo ownerPojoInfo, Contribution.Builder builder) {
    Optional<OrmPojoInfo> maybePojoInfo = pojoInfo();
    if (maybePojoInfo.isPresent()) {
      OrmPojoInfo pojoInfo = maybePojoInfo.get();
      builder.addMethod(method(pojoInfo, ownerPojoInfo));
    }
  }

  @Override
  public Equality isEqualTo(Object that) {
    return Tester.of(PojoQueryMethod.class)
        .add("methodInfo", o -> o.methodInfo)
        .test(this, that);
  }

  private MethodSpec method(OrmPojoInfo pojoInfo, OrmPojoInfo ownerPojoInfo) {
    OrmRelationInfo relationInfo = pojoInfo.relationTo(ownerPojoInfo);
    QuerySelectExpression selectExpression = pojoInfo.tableInfoMap().selectExpression();
    return methodInfo.overrideWriter()
        .addCode(QueryMethodBody.builder(pojoInfo, returnType)
            .selectExpression(relationInfo.filter(selectExpression))
            .whereExpression(relationInfo.whereExpression())
            .orderByExpression(StandardQueryOrderByExpression.of(methodInfo))
            .collectExpression(relationInfo.collectExpression(returnType))
            .build())
        .write();
  }

  private Optional<OrmPojoInfo> pojoInfo() {
    PojoInfo pojoInfo = PojoInfo.of(pojoTypeInfo);
    return OrmPojoInfo.of(pojoInfo);
  }

}