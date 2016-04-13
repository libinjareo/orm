package br.com.objectos.schema.it;

import br.com.objectos.code.Testing;
import br.com.objectos.db.ColumnOf;
import br.com.objectos.db.DateComparison;
import br.com.objectos.db.NumericComparison;
import br.com.objectos.db.SortOrder;
import br.com.objectos.db.StringComparison;
import br.com.objectos.schema.meta.ColumnAnnotation;
import br.com.objectos.schema.meta.ColumnAnnotationClassArray;
import br.com.objectos.schema.meta.ColumnClass;
import br.com.objectos.schema.meta.ColumnName;
import br.com.objectos.schema.meta.ColumnSeq;
import br.com.objectos.schema.meta.GeneratedValue;
import br.com.objectos.schema.meta.GenerationKind;
import br.com.objectos.schema.meta.MigrationPrefix;
import br.com.objectos.schema.meta.PrimaryKeyClassArray;
import br.com.objectos.schema.meta.PrimaryKeyName;
import br.com.objectos.schema.meta.SchemaName;
import br.com.objectos.schema.meta.TableClass;
import br.com.objectos.schema.meta.TableName;
import br.com.objectos.schema.type.DateColumn;
import br.com.objectos.schema.type.IntColumn;
import br.com.objectos.schema.type.Table;
import br.com.objectos.schema.type.VarcharColumn;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import javax.annotation.Generated;

@Generated("br.com.objectos.way.schema.compiler.SchemaProcessor")
@TableClass
@SchemaName("OBJECTOS_ORM")
@TableName("REVISION")
@ColumnAnnotationClassArray({ REVISION.SEQ.class, REVISION.DATE.class, REVISION.DESCRIPTION.class })
@PrimaryKeyName("REVISION_PK")
@PrimaryKeyClassArray({ REVISION.SEQ.class })
@MigrationPrefix("V003")
public final class REVISION extends Table implements V003__Revision.REVISION {
  private static final REVISION INSTANCE = new REVISION();

  private REVISION() {
    super("OBJECTOS_ORM", "REVISION");
  }

  public static REVISION get() {
    return INSTANCE;
  }

  @Override
  public REVISION_SEQ SEQ() {
    return new REVISION_SEQ();
  }

  public REVISION_SEQ SEQ(int value) {
    return new REVISION_SEQ(value);
  }

  @Override
  public REVISION_DATE DATE() {
    return new REVISION_DATE();
  }

  public REVISION_DATE DATE(LocalDate value) {
    return new REVISION_DATE(value);
  }

  @Override
  public REVISION_DESCRIPTION DESCRIPTION() {
    return new REVISION_DESCRIPTION();
  }

  public REVISION_DESCRIPTION DESCRIPTION(String value) {
    return new REVISION_DESCRIPTION(value);
  }

  @Testing
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("REVISION")
  @ColumnName("SEQ")
  @ColumnSeq(0)
  @ColumnClass(REVISION_SEQ.class)
  @GeneratedValue(GenerationKind.AUTO_INCREMENT)
  public @interface SEQ {
    NumericComparison comparison() default NumericComparison.EQ;

    SortOrder orderBy() default SortOrder.ASC;
  }

  @Testing
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("REVISION")
  @ColumnName("DATE")
  @ColumnSeq(1)
  @ColumnClass(REVISION_DATE.class)
  public @interface DATE {
    DateComparison comparison() default DateComparison.EQ;

    SortOrder orderBy() default SortOrder.ASC;
  }

  @Testing
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("REVISION")
  @ColumnName("DESCRIPTION")
  @ColumnSeq(2)
  @ColumnClass(REVISION_DESCRIPTION.class)
  public @interface DESCRIPTION {
    StringComparison comparison() default StringComparison.EQ;

    SortOrder orderBy() default SortOrder.ASC;
  }

  @Testing
  public static final class REVISION_SEQ extends IntColumn implements ColumnOf<REVISION> {
    private REVISION_SEQ() {
      super(INSTANCE, "SEQ");
    }

    private REVISION_SEQ(int value) {
      super(INSTANCE, "SEQ", value);
    }

    @Override
    public REVISION_SEQ nullValue() {
      return new REVISION_SEQ();
    }

    @Override
    public REVISION_SEQ withValue(int value) {
      return new REVISION_SEQ(value);
    }
  }

  @Testing
  public static final class REVISION_DATE extends DateColumn implements ColumnOf<REVISION> {
    private REVISION_DATE() {
      super(INSTANCE, "DATE");
    }

    private REVISION_DATE(LocalDate value) {
      super(INSTANCE, "DATE", value);
    }

    @Override
    public REVISION_DATE withValue(LocalDate value) {
      return new REVISION_DATE(value);
    }
  }

  @Testing
  public static final class REVISION_DESCRIPTION extends VarcharColumn implements ColumnOf<REVISION> {
    private REVISION_DESCRIPTION() {
      super(INSTANCE, "DESCRIPTION");
    }

    private REVISION_DESCRIPTION(String value) {
      super(INSTANCE, "DESCRIPTION", value);
    }

    @Override
    public REVISION_DESCRIPTION withValue(String value) {
      return new REVISION_DESCRIPTION(value);
    }
  }
}
