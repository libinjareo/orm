package br.com.objectos.pojo.plugin;

import javax.annotation.Generated;

@Generated({
    "br.com.objectos.orm.compiler.InjectPlugin",
    "br.com.objectos.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction"
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
