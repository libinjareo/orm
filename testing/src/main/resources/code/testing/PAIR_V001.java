package br.com.objectos.schema.it;

import br.com.objectos.db.ColumnOf;
import br.com.objectos.schema.meta.ColumnAnnotation;
import br.com.objectos.schema.meta.ColumnClass;
import br.com.objectos.schema.meta.ColumnName;
import br.com.objectos.schema.meta.ColumnSeq;
import br.com.objectos.schema.meta.MigrationPrefix;
import br.com.objectos.schema.meta.SchemaName;
import br.com.objectos.schema.meta.TableClassName;
import br.com.objectos.schema.meta.TableName;
import br.com.objectos.schema.type.IntColumn;
import br.com.objectos.schema.type.Table;
import br.com.objectos.schema.type.VarcharColumn;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.annotation.Generated;

@Generated("br.com.objectos.way.schema.compiler.TableProcessor")
@MigrationPrefix("V001")
@TableClassName("br.com.objectos.schema.it.PAIR")
public final class PAIR_V001 extends Table implements V001__First_Migration.PAIR {
  private static final PAIR_V001 INSTANCE = new PAIR_V001();

  private PAIR_V001() {
    super("OBJECTOS_ORM", "PAIR");
  }

  public static PAIR_V001 get() {
    return INSTANCE;
  }

  @Override
  public PAIR_ID ID() {
    return new PAIR_ID();
  }

  public PAIR_ID ID(int value) {
    return new PAIR_ID(value);
  }

  @Override
  public PAIR_NAME NAME() {
    return new PAIR_NAME();
  }

  public PAIR_NAME NAME(String value) {
    return new PAIR_NAME(value);
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("PAIR")
  @ColumnName("ID")
  @ColumnSeq(0)
  @ColumnClass(PAIR_ID.class)
  public @interface ID {
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("PAIR")
  @ColumnName("NAME")
  @ColumnSeq(1)
  @ColumnClass(PAIR_NAME.class)
  public @interface NAME {
  }

  public static final class PAIR_ID extends IntColumn implements ColumnOf<PAIR_V001> {
    private PAIR_ID() {
      super(INSTANCE, "ID");
    }

    private PAIR_ID(int value) {
      super(INSTANCE, "ID", value);
    }

    @Override
    public PAIR_ID nullValue() {
      return new PAIR_ID();
    }

    @Override
    public PAIR_ID withValue(int value) {
      return new PAIR_ID(value);
    }
  }

  public static final class PAIR_NAME extends VarcharColumn implements ColumnOf<PAIR_V001> {
    private PAIR_NAME() {
      super(INSTANCE, "NAME");
    }

    private PAIR_NAME(String value) {
      super(INSTANCE, "NAME", value);
    }

    @Override
    public PAIR_NAME withValue(String value) {
      return new PAIR_NAME(value);
    }
  }
}
