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

import br.com.objectos.collections.MoreCollectors;
import br.com.objectos.pojo.Pojo;
import br.com.objectos.pojo.plugin.Naming;
import br.com.objectos.pojo.plugin.PojoInfo;
import br.com.objectos.testable.Testable;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
@Pojo
abstract class OrmPojoInfo implements Testable {

  private static final Map<PojoInfo, Optional<OrmPojoInfo>> CACHE = new ConcurrentHashMap<>();

  abstract PojoInfo pojoInfo();
  abstract List<OrmProperty> propertyList();
  abstract OrmInsertable insertable();

  OrmPojoInfo() {
  }

  public static void invalidate() {
    CACHE.clear();
  }

  public static Optional<OrmPojoInfo> of(PojoInfo pojoInfo) {
    return CACHE.computeIfAbsent(pojoInfo, OrmPojoInfo::of0);
  }

  static OrmPojoInfoBuilder builder() {
    return new OrmPojoInfoBuilderPojo();
  }

  private static Optional<OrmPojoInfo> of0(PojoInfo pojoInfo) {
    List<OrmProperty> propertyList = pojoInfo.propertyStream()
        .map(OrmProperty::of)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(MoreCollectors.toImmutableList());

    return propertyList.isEmpty()
        ? Optional.empty()
        : Optional.of(of1(pojoInfo, propertyList));
  }

  private static OrmPojoInfo of1(PojoInfo pojoInfo, List<OrmProperty> propertyList) {
    return OrmPojoInfo.builder()
        .pojoInfo(pojoInfo)
        .propertyList(propertyList)
        .insertable(OrmInsertable.of(pojoInfo, propertyList))
        .build();
  }

  public CompanionType companionType() {
    return CompanionType.of(this);
  }

  Naming naming() {
    return pojoInfo().naming();
  }

}