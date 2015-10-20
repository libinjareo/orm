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
package br.com.objectos.schema.info;

import br.com.objectos.code.PackageInfo;
import br.com.objectos.code.PackageInfoBuilder;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
class PackageInfoFake {

  public static final PackageInfo BR_COM_OBJECTOS_SCHEMA_IT = builder()
      .name("br.com.objectos.schema.it")
      .build();

  public static final PackageInfo JAVA_LANG = builder()
      .name("java.lang")
      .build();
  public static final PackageInfo JAVA_TIME = builder()
      .name("java.time")
      .build();

  private PackageInfoFake() {
  }

  private static PackageInfoBuilder builder() {
    return PackageInfo.builder();
  }

}