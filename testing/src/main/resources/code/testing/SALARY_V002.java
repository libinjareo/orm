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
import br.com.objectos.schema.type.DateColumn;
import br.com.objectos.schema.type.IntColumn;
import br.com.objectos.schema.type.Table;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import javax.annotation.Generated;

@Generated("br.com.objectos.way.schema.compiler.TableProcessor")
@MigrationPrefix("V002")
@TableClassName("br.com.objectos.schema.it.SALARY")
public final class SALARY_V002 extends Table implements V002__Employee_Salary.SALARY {
  private static final SALARY_V002 INSTANCE = new SALARY_V002();

  private SALARY_V002() {
    super("OBJECTOS_ORM", "SALARY");
  }

  public static SALARY_V002 get() {
    return INSTANCE;
  }

  @Override
  public SALARY_EMP_NO EMP_NO() {
    return new SALARY_EMP_NO();
  }

  public SALARY_EMP_NO EMP_NO(int value) {
    return new SALARY_EMP_NO(value);
  }

  @Override
  public SALARY_SALARY SALARY_() {
    return new SALARY_SALARY();
  }

  public SALARY_SALARY SALARY_(int value) {
    return new SALARY_SALARY(value);
  }

  @Override
  public SALARY_FROM_DATE FROM_DATE() {
    return new SALARY_FROM_DATE();
  }

  public SALARY_FROM_DATE FROM_DATE(LocalDate value) {
    return new SALARY_FROM_DATE(value);
  }

  @Override
  public SALARY_TO_DATE TO_DATE() {
    return new SALARY_TO_DATE();
  }

  public SALARY_TO_DATE TO_DATE(LocalDate value) {
    return new SALARY_TO_DATE(value);
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("SALARY")
  @ColumnName("EMP_NO")
  @ColumnSeq(0)
  @ColumnClass(SALARY_EMP_NO.class)
  public @interface EMP_NO {
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("SALARY")
  @ColumnName("SALARY")
  @ColumnSeq(1)
  @ColumnClass(SALARY_SALARY.class)
  public @interface SALARY_ {
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("SALARY")
  @ColumnName("FROM_DATE")
  @ColumnSeq(2)
  @ColumnClass(SALARY_FROM_DATE.class)
  public @interface FROM_DATE {
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("SALARY")
  @ColumnName("TO_DATE")
  @ColumnSeq(3)
  @ColumnClass(SALARY_TO_DATE.class)
  public @interface TO_DATE {
  }

  public static final class SALARY_EMP_NO extends IntColumn implements ColumnOf<SALARY_V002> {
    private SALARY_EMP_NO() {
      super(INSTANCE, "EMP_NO");
    }

    private SALARY_EMP_NO(int value) {
      super(INSTANCE, "EMP_NO", value);
    }

    @Override
    public SALARY_EMP_NO nullValue() {
      return new SALARY_EMP_NO();
    }

    @Override
    public SALARY_EMP_NO withValue(int value) {
      return new SALARY_EMP_NO(value);
    }
  }

  public static final class SALARY_SALARY extends IntColumn implements ColumnOf<SALARY_V002> {
    private SALARY_SALARY() {
      super(INSTANCE, "SALARY");
    }

    private SALARY_SALARY(int value) {
      super(INSTANCE, "SALARY", value);
    }

    @Override
    public SALARY_SALARY nullValue() {
      return new SALARY_SALARY();
    }

    @Override
    public SALARY_SALARY withValue(int value) {
      return new SALARY_SALARY(value);
    }
  }

  public static final class SALARY_FROM_DATE extends DateColumn implements ColumnOf<SALARY_V002> {
    private SALARY_FROM_DATE() {
      super(INSTANCE, "FROM_DATE");
    }

    private SALARY_FROM_DATE(LocalDate value) {
      super(INSTANCE, "FROM_DATE", value);
    }

    @Override
    public SALARY_FROM_DATE withValue(LocalDate value) {
      return new SALARY_FROM_DATE(value);
    }
  }

  public static final class SALARY_TO_DATE extends DateColumn implements ColumnOf<SALARY_V002> {
    private SALARY_TO_DATE() {
      super(INSTANCE, "TO_DATE");
    }

    private SALARY_TO_DATE(LocalDate value) {
      super(INSTANCE, "TO_DATE", value);
    }

    @Override
    public SALARY_TO_DATE withValue(LocalDate value) {
      return new SALARY_TO_DATE(value);
    }
  }
}
