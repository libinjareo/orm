/*
 * Copyright 2016 Objectos, Fábrica de Software LTDA.
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

import br.com.objectos.pojo.plugin.PojoInfo;

import com.squareup.javapoet.CodeBlock;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class OrmRelationInfoTest extends AbstractOrmCompilerTest {

  @DataProvider
  public Object[][] collectExpressionProvider() {
    return new Object[][] {
        { PojoInfoFake.Employee, PojoInfoFake.Salary, QueryReturnType.LIST, "employeeSalaryList" },
        { PojoInfoFake.Employee, PojoInfoFake.SalaryOpt, QueryReturnType.LIST, "employeeSalaryOptList" }
    };
  }

  @Test(dataProvider = "collectExpressionProvider")
  public void collectExpression(PojoInfo ownerPojoInfo, PojoInfo pojoInfo, QueryReturnType returnType, String exp) {
    OrmRelationInfo relationInfo = relationInfo(ownerPojoInfo, pojoInfo);
    QueryCollectExpression expression = relationInfo.collectExpression(QueryReturnType.LIST);
    CodeBlock res = expression.get();
    ASSERT.that(res).hasToStringEqualTo("OrmRelationInfoTest.collectExpression." + exp + ".poet");
  }

  private OrmRelationInfo relationInfo(PojoInfo ownerPojoInfo, PojoInfo pojoInfo) {
    return OrmPojoInfo.of(pojoInfo).get().relationTo(OrmPojoInfo.of(ownerPojoInfo).get());
  }

}