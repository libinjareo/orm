package br.com.objectos.way.schema.it;

import br.com.objectos.way.db.ColumnOf;
import br.com.objectos.way.db.NumericComparison;
import br.com.objectos.way.db.SortOrder;
import br.com.objectos.way.db.StringComparison;
import br.com.objectos.way.schema.meta.ColumnAnnotation;
import br.com.objectos.way.schema.meta.ColumnAnnotationClassArray;
import br.com.objectos.way.schema.meta.ColumnClass;
import br.com.objectos.way.schema.meta.ColumnName;
import br.com.objectos.way.schema.meta.ColumnSeq;
import br.com.objectos.way.schema.meta.MigrationPrefix;
import br.com.objectos.way.schema.meta.Nullable;
import br.com.objectos.way.schema.meta.SchemaName;
import br.com.objectos.way.schema.meta.TableClass;
import br.com.objectos.way.schema.meta.TableName;
import br.com.objectos.way.schema.type.IntColumn;
import br.com.objectos.way.schema.type.Table;
import br.com.objectos.way.schema.type.VarcharColumn;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.annotation.Generated;

@Generated("br.com.objectos.way.schema.compiler.SchemaProcessor")
@TableClass
@SchemaName("OBJECTOS_ORM")
@TableName("DUO")
@ColumnAnnotationClassArray({ DUO.ID.class, DUO.NAME.class })
@MigrationPrefix("V004")
public final class DUO extends Table implements V004__More.DUO {
  private static final DUO INSTANCE = new DUO();

  private DUO() {
    super("OBJECTOS_ORM", "DUO");
  }

  public static DUO get() {
    return INSTANCE;
  }

  @Override
  public DUO_ID ID() {
    return new DUO_ID();
  }

  public DUO_ID ID(int value) {
    return new DUO_ID(value);
  }

  @Override
  public DUO_NAME NAME() {
    return new DUO_NAME();
  }

  public DUO_NAME NAME(String value) {
    return new DUO_NAME(value);
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("DUO")
  @ColumnName("ID")
  @ColumnSeq(0)
  @ColumnClass(DUO_ID.class)
  @Nullable
  public @interface ID {
    NumericComparison comparison() default NumericComparison.EQ;

    SortOrder orderBy() default SortOrder.ASC;
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("DUO")
  @ColumnName("NAME")
  @ColumnSeq(1)
  @ColumnClass(DUO_NAME.class)
  @Nullable
  public @interface NAME {
    StringComparison comparison() default StringComparison.EQ;

    SortOrder orderBy() default SortOrder.ASC;
  }

  public static final class DUO_ID extends IntColumn implements ColumnOf<DUO> {
    private DUO_ID() {
      super(INSTANCE, "ID");
    }

    private DUO_ID(int value) {
      super(INSTANCE, "ID", value);
    }

    @Override
    public DUO_ID nullValue() {
      return new DUO_ID();
    }

    @Override
    public DUO_ID withValue(int value) {
      return new DUO_ID(value);
    }
  }

  public static final class DUO_NAME extends VarcharColumn implements ColumnOf<DUO> {
    private DUO_NAME() {
      super(INSTANCE, "NAME");
    }

    private DUO_NAME(String value) {
      super(INSTANCE, "NAME", value);
    }

    @Override
    public DUO_NAME withValue(String value) {
      return new DUO_NAME(value);
    }
  }
}
