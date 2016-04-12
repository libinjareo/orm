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
import br.com.objectos.way.schema.type.Table;
import br.com.objectos.way.schema.type.TinyIntColumn;
import br.com.objectos.way.schema.type.VarcharColumn;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.annotation.Generated;

@Generated("br.com.objectos.way.schema.compiler.TableProcessor")
@MigrationPrefix("V001")
@TableClassName("br.com.objectos.way.schema.it.category")
public final class category_V001 extends Table implements V001__First_Migration.category {
  private static final category_V001 INSTANCE = new category_V001();

  private category_V001() {
    super("sakila", "category");
  }

  public static category_V001 get() {
    return INSTANCE;
  }

  @Override
  public category_category_id category_id() {
    return new category_category_id();
  }

  public category_category_id category_id(int value) {
    return new category_category_id(value);
  }

  @Override
  public category_name name_() {
    return new category_name();
  }

  public category_name name_(String value) {
    return new category_name(value);
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("sakila")
  @TableName("category")
  @ColumnName("category_id")
  @ColumnSeq(0)
  @ColumnClass(category_category_id.class)
  @GeneratedValue(GenerationKind.AUTO_INCREMENT)
  public @interface category_id {
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("sakila")
  @TableName("category")
  @ColumnName("name")
  @ColumnSeq(1)
  @ColumnClass(category_name.class)
  public @interface name_ {
  }

  public static final class category_category_id extends TinyIntColumn implements ColumnOf<category_V001> {
    private category_category_id() {
      super(INSTANCE, "category_id");
    }

    private category_category_id(int value) {
      super(INSTANCE, "category_id", value);
    }

    @Override
    public category_category_id nullValue() {
      return new category_category_id();
    }

    @Override
    public category_category_id withValue(int value) {
      return new category_category_id(value);
    }
  }

  public static final class category_name extends VarcharColumn implements ColumnOf<category_V001> {
    private category_name() {
      super(INSTANCE, "name");
    }

    private category_name(String value) {
      super(INSTANCE, "name", value);
    }

    @Override
    public category_name withValue(String value) {
      return new category_name(value);
    }
  }
}
