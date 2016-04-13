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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class Joiner {

  private final List<String> parts = new ArrayList<>();
  private final String separator;

  private Joiner(String separator) {
    this.separator = separator;
  }

  public static Joiner on(String separator) {
    Objects.requireNonNull(separator);
    return new Joiner(separator);
  }

  public Joiner addAll(Stream<?> stream) {
    stream.forEach(this::add);
    return this;
  }

  public Joiner add(Object part) {
    Objects.requireNonNull(part);
    parts.add(part.toString());
    return this;
  }

  public String join() {
    return parts.stream().collect(Collectors.joining(separator));
  }

}