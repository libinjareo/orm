package br.com.objectos.schema.it;

import br.com.objectos.db.ColumnOf;
import br.com.objectos.db.NumericComparison;
import br.com.objectos.db.SortOrder;
import br.com.objectos.schema.meta.ColumnAnnotation;
import br.com.objectos.schema.meta.ColumnAnnotationClassArray;
import br.com.objectos.schema.meta.ColumnClass;
import br.com.objectos.schema.meta.ColumnName;
import br.com.objectos.schema.meta.ColumnSeq;
import br.com.objectos.schema.meta.GeneratedValue;
import br.com.objectos.schema.meta.GenerationKind;
import br.com.objectos.schema.meta.MigrationPrefix;
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

@Generated("br.com.objectos.way.schema.compiler.SchemaProcessor")
@TableClass
@SchemaName("OBJECTOS_ORM")
@TableName("APP")
@ColumnAnnotationClassArray({ APP.ID.class })
@MigrationPrefix("V004")
public final class APP extends Table implements V004__More.APP {
  private static final APP INSTANCE = new APP();

  private APP() {
    super("OBJECTOS_ORM", "APP");
  }

  public static APP get() {
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
    NumericComparison comparison() default NumericComparison.EQ;

    SortOrder orderBy() default SortOrder.ASC;
  }

  public static final class APP_ID extends IntColumn implements ColumnOf<APP> {
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
