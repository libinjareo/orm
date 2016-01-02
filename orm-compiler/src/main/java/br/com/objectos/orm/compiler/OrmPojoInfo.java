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
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import br.com.objectos.code.AnnotationInfo;
import br.com.objectos.code.CanGenerateCompilationError;
import br.com.objectos.code.SimpleTypeInfo;
import br.com.objectos.code.TypeInfo;
import br.com.objectos.code.TypeParameterInfo;
import br.com.objectos.collections.MoreCollectors;
import br.com.objectos.lazy.annotation.Lazy;
import br.com.objectos.orm.Query;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.pojo.plugin.Naming;
import br.com.objectos.pojo.plugin.PojoInfo;
import br.com.objectos.sql.query.Row;
import br.com.objectos.testable.Testable;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class OrmPojoInfo implements CanGenerateCompilationError, Testable {

  private static final Map<PojoInfo, Optional<OrmPojoInfo>> CACHE = new ConcurrentHashMap<>(16, 0.75f, 2);

  abstract PojoInfo pojoInfo();
  abstract List<OrmProperty> propertyList();
  abstract List<ColumnOrmProperty> columnPropertyList();
  abstract List<ForeignKeyOrmProperty> foreignKeyPropertyList();
  abstract List<PojoQueryMethod> queryMethodList();
  abstract TableInfoMap tableInfoMap();
  abstract OrmInsertable insertable();

  OrmPojoInfo() {
  }

  public static void invalidate() {
    CACHE.clear();
  }

  public static Optional<OrmPojoInfo> of(PojoInfo pojoInfo) {
    return CACHE.computeIfAbsent(pojoInfo, OrmPojoInfo::of0);
  }

  public static Optional<OrmPojoInfo> of(SimpleTypeInfo returnTypeInfo) {
    TypeInfo typeInfo = returnTypeInfo.isInfoOf(Optional.class)
        ? returnTypeInfo.getTypeParameterInfoStream()
            .findFirst()
            .flatMap(TypeParameterInfo::typeInfo)
            .get()
        : returnTypeInfo.typeInfo().get();
    PojoInfo pojoInfo = PojoInfo.of(typeInfo);
    return of(pojoInfo);
  }

  static OrmPojoInfoBuilder builder() {
    return new OrmPojoInfoBuilderPojo();
  }

  private static Optional<OrmPojoInfo> of0(PojoInfo pojoInfo) {
    OrmPropertyHelper helper = OrmPropertyHelper.get();

    pojoInfo.propertyStream()
        .map(OrmProperty::of)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .forEach(property -> property.acceptOrmPropertyHelper(helper));

    List<OrmProperty> propertyList = helper.propertyList();

    return propertyList.isEmpty()
        ? Optional.empty()
        : Optional.of(of1(pojoInfo, propertyList, helper));
  }

  private static OrmPojoInfo of1(PojoInfo pojoInfo, List<OrmProperty> propertyList, OrmPropertyHelper helper) {
    TableInfoMap tableInfoMap = helper.tableInfoMap();
    return OrmPojoInfo.builder()
        .pojoInfo(pojoInfo)
        .propertyList(propertyList)
        .columnPropertyList(helper.columnPropertyList())
        .foreignKeyPropertyList(helper.foreignKeyPropertyList())
        .queryMethodList(pojoInfo.methodInfoStream()
            .filter(m -> m.hasAnnotation(Query.class))
            .map(PojoQueryMethod::of)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(MoreCollectors.toImmutableList()))
        .tableInfoMap(tableInfoMap)
        .insertable(tableInfoMap.toOrmInsertable(pojoInfo))
        .build();
  }

  public Optional<ColumnOrmProperty> columnPropertyAnnotatedWith(SimpleTypeInfo annotationTypeInfo) {
    return columnPropertyList().stream()
        .filter(m -> m.columnAnnotationMatches(annotationTypeInfo))
        .findFirst();
  }

  public CompanionType companionType() {
    return CompanionType.of(this);
  }

  @Override
  public void compilationError(String message) {
    pojoInfo().compilationError(message);
  }

  @Lazy
  public List<ConstructorContext> constructorContextList() {
    return pojoInfo().constructorInfoStream()
        .map(constructor -> ConstructorContext.of(this, constructor))
        .collect(MoreCollectors.toImmutableList());
  }

  @Lazy
  public List<ParameterSpec> foreignKeyParameterSpecList() {
    return foreignKeyPropertyList().stream()
        .map(OrmProperty::parameterSpec)
        .collect(MoreCollectors.toImmutableList());
  }

  public boolean hasForeignKeys() {
    return foreignKeyPropertyList().size() > 0;
  }

  @Lazy
  public OrmInject inject() {
    return OrmInject.of(pojoInfo());
  }

  public Optional<OrmProperty> propertyAnnotatedWith(AnnotationInfo annotationInfo) {
    return propertyList().stream()
        .filter(property -> property.matches(annotationInfo))
        .findAny();
  }

  public OrmRelationInfo relationTo(OrmPojoInfo ownerPojoInfo) {
    OrmRelationInfo.Builder builder = OrmRelationInfo.builder();
    foreignKeyPropertyList().stream()
        .filter(property -> property.references(ownerPojoInfo))
        .forEach(builder::add);
    return builder.build();
  }

  public ParameterSpec rowParameterSpec() {
    return rowParameterSpec(propertyList());
  }

  public ParameterSpec rowParameterSpecColumns() {
    return rowParameterSpec(columnPropertyList());
  }

  Naming naming() {
    return pojoInfo().naming();
  }

  private ParameterSpec rowParameterSpec(List<? extends OrmProperty> propertyList) {
    return ParameterSpec.builder(rowTypeName(propertyList), "row").build();
  }

  private ParameterizedTypeName rowTypeName(List<? extends OrmProperty> propertyList) {
    ClassName rowClassName = ClassName.get(Row.class).peerClass("Row" + propertyList.size());
    ClassName[] columnClassNameArray = propertyList.stream()
        .sorted()
        .flatMap(OrmProperty::columnClassNameStream)
        .toArray(ClassName[]::new);
    return ParameterizedTypeName.get(rowClassName, columnClassNameArray);
  }

}