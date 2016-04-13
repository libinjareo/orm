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

import java.util.Map;

import br.com.objectos.code.TypeInfo;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class TypeInfoFake {

  public static final Map<String, TypeInfo> qualifiedNameMap = TestingProcessor.loadAll(
      TestingProcessor::new,
      "code/testing",
      "OBJECTOS_ORM.java",
      "Pair.java",
      "PAIR.java",
      "V001__First_Migration.java",
      "V002__Employee_Salary.java",
      "V003__Revision.java",
      "V004__More.java",
      "EMPLOYEE_V002.java",
      "Employee.java",
      "EMPLOYEE.java",
      "REVISION_V003.java",
      "Revision.java",
      "REVISION.java",
      "Salary.java",
      "SalaryOpt.java",
      "SALARY.java");

  public static final TypeInfo Employee = get("Employee");
  public static final TypeInfo EMPLOYEE_EMP_NO = get("EMPLOYEE.EMP_NO");
  public static final TypeInfo EMPLOYEE_BIRTH_DATE = get("EMPLOYEE.BIRTH_DATE");
  public static final TypeInfo EMPLOYEE_FIRST_NAME = get("EMPLOYEE.FIRST_NAME");
  public static final TypeInfo EMPLOYEE_LAST_NAME = get("EMPLOYEE.LAST_NAME");
  public static final TypeInfo EMPLOYEE_HIRE_DATE = get("EMPLOYEE.HIRE_DATE");
  public static final TypeInfo Pair = get("Pair");
  public static final TypeInfo PAIR = get("PAIR");
  public static final TypeInfo PAIR_ID = get("PAIR.ID");
  public static final TypeInfo PAIR_NAME = get("PAIR.NAME");
  public static final TypeInfo PAIR_PAIR_ID = get("PAIR.PAIR_ID");
  public static final TypeInfo PAIR_PAIR_NAME = get("PAIR.PAIR_NAME");
  public static final TypeInfo Revision = get("Revision");
  public static final TypeInfo REVISION_SEQ = get("REVISION.SEQ");
  public static final TypeInfo REVISION_DATE = get("REVISION.DATE");
  public static final TypeInfo REVISION_DESCRIPTION = get("REVISION.DESCRIPTION");
  public static final TypeInfo REVISION_REVISION_DATE = get("REVISION.REVISION_DATE");
  public static final TypeInfo REVISION_REVISION_DESCRIPTION = get("REVISION.REVISION_DESCRIPTION");
  public static final TypeInfo REVISION_REVISION_SEQ = get("REVISION.REVISION_SEQ");
  public static final TypeInfo Salary = get("Salary");
  public static final TypeInfo SalaryOpt = get("SalaryOpt");
  public static final TypeInfo SALARY = get("SALARY");
  public static final TypeInfo SALARY_EMP_NO = get("SALARY.EMP_NO");
  public static final TypeInfo SALARY_SALARY_ = get("SALARY.SALARY_");
  public static final TypeInfo SALARY_FROM_DATE = get("SALARY.FROM_DATE");
  public static final TypeInfo SALARY_TO_DATE = get("SALARY.TO_DATE");

  private TypeInfoFake() {
  }

  private static TypeInfo get(String name) {
    TypeInfo typeInfo = qualifiedNameMap.get("br.com.objectos.schema.it." + name);
    if (typeInfo == null) {
      throw new NullPointerException(name);
    }
    return typeInfo;
  }

}