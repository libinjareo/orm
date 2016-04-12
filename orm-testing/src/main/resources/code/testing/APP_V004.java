package br.com.objectos.way.schema.it;

import br.com.objectos.way.db.ColumnOf;
import br.com.objectos.way.schema.meta.ColumnAnnotation;
import br.com.objectos.way.schema.meta.ColumnClass;
import br.com.objectos.way.schema.meta.ColumnName;
import br.com.objectos.way.schema.meta.ColumnSeq;
import br.com.objectos.way.schema.meta.GeneratedValue;
import br.com.objectos.way.schema.meta.GenerationKind;
import br.com.objectos.way.schema.meta.MigrationPrefix;
import br.com.objectos.way.schema.meta.SchemaName;
import br.com.objectos.way.schema.meta.TableClassName;
import br.com.objectos.way.schema.meta.TableName;
import br.com.objectos.way.schema.type.IntColumn;
import br.com.objectos.way.schema.type.Table;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.annotation.Generated;

@Generated("br.com.objectos.way.schema.compiler.TableProcessor")
@MigrationPrefix("V004")
@TableClassName("br.com.objectos.way.schema.it.APP")
public final class APP_V004 extends Table implements V004__More.APP {
  private static final APP_V004 INSTANCE = new APP_V004();

  private APP_V004() {
    super("OBJECTOS_ORM", "APP");
  }

  public static APP_V004 get() {
    return INSTANCE;
  }

  @Override
  public APP_ID ID() {
    return new APP_ID();
  }

  public APP_ID ID(int value) {
    return new APP_ID(value);
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("APP")
  @ColumnName("ID")
  @ColumnSeq(0)
  @ColumnClass(APP_ID.class)
  @GeneratedValue(GenerationKind.AUTO_INCREMENT)
  public @interface ID {
  }

  public static final class APP_ID extends IntColumn implements ColumnOf<APP_V004> {
    private APP_ID() {
      super(INSTANCE, "ID");
    }

    private APP_ID(int value) {
      super(INSTANCE, "ID", value);
    }

    @Override
    public APP_ID nullValue() {
      return new APP_ID();
    }

    @Override
    public APP_ID withValue(int value) {
      return new APP_ID(value);
    }
  }
}
