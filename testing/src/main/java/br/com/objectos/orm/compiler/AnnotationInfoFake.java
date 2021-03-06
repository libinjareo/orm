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

import br.com.objectos.code.AnnotationInfo;
import br.com.objectos.code.TypeInfo;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class AnnotationInfoFake {

  public static final AnnotationInfo EMPLOYEE_EMP_NO = methodFirst(TypeInfoFake.Employee, "empNo");
  public static final AnnotationInfo PAIR_ID = methodFirst(TypeInfoFake.Pair, "id");
  public static final AnnotationInfo PAIR_NAME = methodFirst(TypeInfoFake.Pair, "name");
  public static final AnnotationInfo REVISION_SEQ = methodFirst(TypeInfoFake.Revision, "seq");
  public static final AnnotationInfo SALARY_EMP_NO_FK = methodFirst(TypeInfoFake.Salary, "employee");
  public static final AnnotationInfo SALARY_FROM_DATE = methodFirst(TypeInfoFake.Salary, "fromDate");
  public static final AnnotationInfo SALARY_SALARY_ = methodFirst(TypeInfoFake.Salary, "salary");
  public static final AnnotationInfo SALARY_TO_DATE = methodFirst(TypeInfoFake.Salary, "toDate");

  private AnnotationInfoFake() {
  }

  private static AnnotationInfo methodFirst(TypeInfo typeInfo, String methodName) {
    return typeInfo.methodInfoStream()
        .filter(info -> info.name().equals(methodName))
        .flatMap(method -> method.annotationInfoStream())
        .findFirst()
        .get();
  }

}