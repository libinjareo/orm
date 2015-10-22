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

import br.com.objectos.code.AnnotationInfo;
import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.pojo.plugin.PojoProperty;
import br.com.objectos.pojo.plugin.Property;

import com.squareup.javapoet.ClassName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class StandardColumnProperty extends ColumnProperty {

  public StandardColumnProperty(Property property,
                                AnnotationInfo columnAnnotationInfo,
                                ClassName columnClassName,
                                SimpleTypeInfo returnTypeInfo,
                                ColumnPropertyBindType bindType) {
    super(property, columnAnnotationInfo, columnClassName, returnTypeInfo, bindType);
  }

  public static ColumnProperty of(Property property) {
    return of0(property, (p, ann, cName, returnTypeInfo, columnClassTypeInfo) -> {
      ColumnPropertyBindType bindType = ColumnPropertyBindType.of(returnTypeInfo, columnClassTypeInfo);
      return new StandardColumnProperty(p, ann, cName, returnTypeInfo, bindType);
    });
  }

  @Override
  PojoProperty constructorStatement() {
    return bindType.standardConstructorStatement(this);
  }

  @Override
  PojoProperty method() {
    return bindType.standardMethod(this);
  }

}