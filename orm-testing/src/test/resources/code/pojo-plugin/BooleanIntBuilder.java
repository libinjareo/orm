package br.com.objectos.pojo.plugin;

import javax.annotation.Generated;

@Generated({
    "br.com.objectos.way.orm.compiler.InjectPlugin",
    "br.com.objectos.way.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.way.pojo.plugin.StandardBuilderPropertyAction"
})
interface BooleanIntBuilder {
  BooleanIntBuilderId id(boolean id);

  interface BooleanIntBuilderId {
    BooleanIntBuilderName name(String name);
  }

  interface BooleanIntBuilderName {
    BooleanInt build();
  }
}
