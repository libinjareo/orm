package br.com.objectos.pojo.plugin;

import javax.annotation.Generated;

@Generated({
    "br.com.objectos.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction",
    "br.com.objectos.way.orm.compiler.InjectPlugin"
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
