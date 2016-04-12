package br.com.objectos.pojo.plugin;

import br.com.objectos.way.orm.Orm;
import java.util.Optional;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.way.orm.compiler.ColumnPropertyBuilderPropertyAction",
    "br.com.objectos.way.orm.compiler.InjectPlugin",
    "br.com.objectos.way.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.way.pojo.plugin.OptionalPlugin",
    "br.com.objectos.way.pojo.plugin.StandardBuilderPropertyAction"
})
final class MergeBuilderPojo implements MergeBuilder, MergeBuilder.MergeBuilderParentA, MergeBuilder.MergeBuilderParentB {
  private final Orm orm;

  private Revision parentA;

  private Optional<Revision> parentB;

  public MergeBuilderPojo(Orm orm) {
    this.orm = orm;
  }

  @Override
  public Merge build() {
    return new MergePojo(orm, this);
  }

  @Override
  public MergeBuilder.MergeBuilderParentA parentA(Revision parentA) {
    if (parentA == null) {
      throw new NullPointerException();
    }
    this.parentA = parentA;
    return this;
  }

  Revision ___get___parentA() {
    return parentA;
  }

  @Override
  public MergeBuilder.MergeBuilderParentB parentB(Optional<Revision> parentB) {
    if (parentB == null) {
      throw new NullPointerException();
    }
    this.parentB = parentB;
    return this;
  }

  Optional<Revision> ___get___parentB() {
    return parentB;
  }

  @Override
  public MergeBuilder.MergeBuilderParentB parentB() {
    this.parentB = Optional.empty();
    return this;
  }

  @Override
  public MergeBuilder.MergeBuilderParentB parentBOf(Revision parentB) {
    this.parentB = Optional.of(parentB);
    return this;
  }

  @Override
  public MergeBuilder.MergeBuilderParentB parentBOfNullable(Revision parentB) {
    this.parentB = Optional.ofNullable(parentB);
    return this;
  }
}
