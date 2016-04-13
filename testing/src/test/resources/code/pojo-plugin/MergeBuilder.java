package br.com.objectos.pojo.plugin;

import java.util.Optional;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.pojo.plugin.OptionalPlugin",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction",
    "br.com.objectos.way.orm.compiler.ColumnPropertyBuilderPropertyAction",
    "br.com.objectos.way.orm.compiler.InjectPlugin"
})
interface MergeBuilder {
  MergeBuilderParentA parentA(Revision parentA);

  interface MergeBuilderParentA {
    MergeBuilderParentB parentB(Optional<Revision> parentB);

    MergeBuilderParentB parentB();

    MergeBuilderParentB parentBOf(Revision parentB);

    MergeBuilderParentB parentBOfNullable(Revision parentB);
  }

  interface MergeBuilderParentB {
    Merge build();
  }
}
