package br.com.objectos.schema.it;

import br.com.objectos.db.ColumnOf;
import br.com.objectos.schema.meta.ColumnAnnotation;
import br.com.objectos.schema.meta.ColumnClass;
import br.com.objectos.schema.meta.ColumnName;
import br.com.objectos.schema.meta.ColumnSeq;
import br.com.objectos.schema.meta.GeneratedValue;
import br.com.objectos.schema.meta.GenerationKind;
import br.com.objectos.schema.meta.MigrationPrefix;
import br.com.objectos.schema.meta.SchemaName;
import br.com.objectos.schema.meta.TableClassName;
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

@Generated("br.com.objectos.way.schema.compiler.TableProcessor")
@MigrationPrefix("V003")
@TableClassName("br.com.objectos.schema.it.REVISION")
public final class REVISION_V003 extends Table implements V003__Revision.REVISION {
  private static final REVISION_V003 INSTANCE = new REVISION_V003();

  private REVISION_V003() {
    super("OBJECTOS_ORM", "REVISION");
  }

  public static REVISION_V003 get() {
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
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("REVISION")
  @ColumnName("DATE")
  @ColumnSeq(1)
  @ColumnClass(REVISION_DATE.class)
  public @interface DATE {
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("REVISION")
  @ColumnName("DESCRIPTION")
  @ColumnSeq(2)
  @ColumnClass(REVISION_DESCRIPTION.class)
  public @interface DESCRIPTION {
  }

  public static final class REVISION_SEQ extends IntColumn implements ColumnOf<REVISION_V003> {
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

  public static final class REVISION_DATE extends DateColumn implements ColumnOf<REVISION_V003> {
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

  public static final class REVISION_DESCRIPTION extends VarcharColumn implements ColumnOf<REVISION_V003> {
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
