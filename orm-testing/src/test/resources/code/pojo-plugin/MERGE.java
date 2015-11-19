package br.com.objectos.schema.it;

import br.com.objectos.db.query.NumericComparison;
import br.com.objectos.db.query.SortOrder;
import br.com.objectos.db.type.ColumnOf;
import br.com.objectos.schema.meta.ColumnAnnotation;
import br.com.objectos.schema.meta.ColumnAnnotationClassArray;
import br.com.objectos.schema.meta.ColumnClass;
import br.com.objectos.schema.meta.ColumnName;
import br.com.objectos.schema.meta.ColumnSeq;
import br.com.objectos.schema.meta.ForeignKeyAnnotation;
import br.com.objectos.schema.meta.GeneratedValue;
import br.com.objectos.schema.meta.GenerationKind;
import br.com.objectos.schema.meta.MigrationPrefix;
import br.com.objectos.schema.meta.Nullable;
import br.com.objectos.schema.meta.PrimaryKeyClassArray;
import br.com.objectos.schema.meta.PrimaryKeyName;
import br.com.objectos.schema.meta.ReferencesAnnotationClassArray;
import br.com.objectos.schema.meta.SchemaName;
import br.com.objectos.schema.meta.TableClass;
import br.com.objectos.schema.meta.TableName;
import br.com.objectos.schema.type.IntColumn;
import br.com.objectos.schema.type.Table;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.annotation.Generated;

@Generated("br.com.objectos.schema.compiler.SchemaProcessor")
@TableClass
@SchemaName("OBJECTOS_ORM")
@TableName("MERGE")
@ColumnAnnotationClassArray({ MERGE.SEQ.class, MERGE.PARENT_A.class, MERGE.PARENT_B.class })
@PrimaryKeyName("MERGE_PK")
@PrimaryKeyClassArray({ MERGE.SEQ.class })
@MigrationPrefix("V004")
public final class MERGE extends Table implements V004__More.MERGE {
  private static final MERGE INSTANCE = new MERGE();

  private MERGE() {
    super("OBJECTOS_ORM", "MERGE");
  }

  public static MERGE get() {
    return INSTANCE;
  }

  @Override
  public MERGE_SEQ SEQ() {
    return new MERGE_SEQ();
  }

  public MERGE_SEQ SEQ(int value) {
    return new MERGE_SEQ(value);
  }

  @Override
  public MERGE_PARENT_A PARENT_A() {
    return new MERGE_PARENT_A();
  }

  public MERGE_PARENT_A PARENT_A(int value) {
    return new MERGE_PARENT_A(value);
  }

  @Override
  public MERGE_PARENT_B PARENT_B() {
    return new MERGE_PARENT_B();
  }

  public MERGE_PARENT_B PARENT_B(int value) {
    return new MERGE_PARENT_B(value);
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("MERGE")
  @ColumnName("SEQ")
  @ColumnSeq(0)
  @ColumnClass(MERGE_SEQ.class)
  @GeneratedValue(GenerationKind.AUTO_INCREMENT)
  public @interface SEQ {
    NumericComparison comparison() default NumericComparison.EQ;

    SortOrder orderBy() default SortOrder.ASC;
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("MERGE")
  @ColumnName("PARENT_A")
  @ColumnSeq(1)
  @ColumnClass(MERGE_PARENT_A.class)
  public @interface PARENT_A {
    NumericComparison comparison() default NumericComparison.EQ;

    SortOrder orderBy() default SortOrder.ASC;
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("MERGE")
  @ColumnName("PARENT_B")
  @ColumnSeq(2)
  @ColumnClass(MERGE_PARENT_B.class)
  @Nullable
  public @interface PARENT_B {
    NumericComparison comparison() default NumericComparison.EQ;

    SortOrder orderBy() default SortOrder.ASC;
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD })
  @ForeignKeyAnnotation
  @ColumnAnnotationClassArray({ PARENT_B.class })
  @ReferencesAnnotationClassArray({ REVISION.SEQ.class })
  public @interface MERGE_REVISION_PARENT_B_FK {
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD })
  @ForeignKeyAnnotation
  @ColumnAnnotationClassArray({ PARENT_A.class })
  @ReferencesAnnotationClassArray({ REVISION.SEQ.class })
  public @interface MERGE_REVISION_PARENT_A_FK {
  }

  public static final class MERGE_SEQ extends IntColumn implements ColumnOf<MERGE> {
    private MERGE_SEQ() {
      super(INSTANCE, "SEQ");
    }

    private MERGE_SEQ(int value) {
      super(INSTANCE, "SEQ", value);
    }

    @Override
    public MERGE_SEQ nullValue() {
      return new MERGE_SEQ();
    }

    @Override
    public MERGE_SEQ withValue(int value) {
      return new MERGE_SEQ(value);
    }
  }

  public static final class MERGE_PARENT_A extends IntColumn implements ColumnOf<MERGE> {
    private MERGE_PARENT_A() {
      super(INSTANCE, "PARENT_A");
    }

    private MERGE_PARENT_A(int value) {
      super(INSTANCE, "PARENT_A", value);
    }

    public REVISION.REVISION_SEQ REVISION_SEQ() {
      REVISION table = REVISION.get();
      return table.SEQ(get());
    }

    @Override
    public MERGE_PARENT_A nullValue() {
      return new MERGE_PARENT_A();
    }

    @Override
    public MERGE_PARENT_A withValue(int value) {
      return new MERGE_PARENT_A(value);
    }
  }

  public static final class MERGE_PARENT_B extends IntColumn implements ColumnOf<MERGE> {
    private MERGE_PARENT_B() {
      super(INSTANCE, "PARENT_B");
    }

    private MERGE_PARENT_B(int value) {
      super(INSTANCE, "PARENT_B", value);
    }

    public REVISION.REVISION_SEQ REVISION_SEQ() {
      REVISION table = REVISION.get();
      return table.SEQ(get());
    }

    @Override
    public MERGE_PARENT_B nullValue() {
      return new MERGE_PARENT_B();
    }

    @Override
    public MERGE_PARENT_B withValue(int value) {
      return new MERGE_PARENT_B(value);
    }
  }
}
