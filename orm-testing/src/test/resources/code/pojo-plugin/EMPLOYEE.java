package br.com.objectos.way.schema.it;

import br.com.objectos.way.db.ColumnOf;
import br.com.objectos.way.db.DateComparison;
import br.com.objectos.way.db.NumericComparison;
import br.com.objectos.way.db.SortOrder;
import br.com.objectos.way.db.StringComparison;
import br.com.objectos.way.schema.meta.ColumnAnnotation;
import br.com.objectos.way.schema.meta.ColumnAnnotationClassArray;
import br.com.objectos.way.schema.meta.ColumnClass;
import br.com.objectos.way.schema.meta.ColumnName;
import br.com.objectos.way.schema.meta.ColumnSeq;
import br.com.objectos.way.schema.meta.MigrationPrefix;
import br.com.objectos.way.schema.meta.PrimaryKeyClassArray;
import br.com.objectos.way.schema.meta.PrimaryKeyName;
import br.com.objectos.way.schema.meta.SchemaName;
import br.com.objectos.way.schema.meta.TableClass;
import br.com.objectos.way.schema.meta.TableName;
import br.com.objectos.way.schema.type.DateColumn;
import br.com.objectos.way.schema.type.IntColumn;
import br.com.objectos.way.schema.type.Table;
import br.com.objectos.way.schema.type.VarcharColumn;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import javax.annotation.Generated;

@Generated("br.com.objectos.way.schema.compiler.SchemaProcessor")
@TableClass
@SchemaName("OBJECTOS_ORM")
@TableName("EMPLOYEE")
@ColumnAnnotationClassArray({ EMPLOYEE.EMP_NO.class, EMPLOYEE.BIRTH_DATE.class, EMPLOYEE.FIRST_NAME.class, EMPLOYEE.LAST_NAME.class, EMPLOYEE.HIRE_DATE.class })
@PrimaryKeyName("EMPLOYEE_PK")
@PrimaryKeyClassArray({ EMPLOYEE.EMP_NO.class })
@MigrationPrefix("V002")
public final class EMPLOYEE extends Table implements V002__Employee_Salary.EMPLOYEE {
  private static final EMPLOYEE INSTANCE = new EMPLOYEE();

  private EMPLOYEE() {
    super("OBJECTOS_ORM", "EMPLOYEE");
  }

  public static EMPLOYEE get() {
    return INSTANCE;
  }

  @Override
  public EMPLOYEE_EMP_NO EMP_NO() {
    return new EMPLOYEE_EMP_NO();
  }

  public EMPLOYEE_EMP_NO EMP_NO(int value) {
    return new EMPLOYEE_EMP_NO(value);
  }

  @Override
  public EMPLOYEE_BIRTH_DATE BIRTH_DATE() {
    return new EMPLOYEE_BIRTH_DATE();
  }

  public EMPLOYEE_BIRTH_DATE BIRTH_DATE(LocalDate value) {
    return new EMPLOYEE_BIRTH_DATE(value);
  }

  @Override
  public EMPLOYEE_FIRST_NAME FIRST_NAME() {
    return new EMPLOYEE_FIRST_NAME();
  }

  public EMPLOYEE_FIRST_NAME FIRST_NAME(String value) {
    return new EMPLOYEE_FIRST_NAME(value);
  }

  @Override
  public EMPLOYEE_LAST_NAME LAST_NAME() {
    return new EMPLOYEE_LAST_NAME();
  }

  public EMPLOYEE_LAST_NAME LAST_NAME(String value) {
    return new EMPLOYEE_LAST_NAME(value);
  }

  @Override
  public EMPLOYEE_HIRE_DATE HIRE_DATE() {
    return new EMPLOYEE_HIRE_DATE();
  }

  public EMPLOYEE_HIRE_DATE HIRE_DATE(LocalDate value) {
    return new EMPLOYEE_HIRE_DATE(value);
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("EMPLOYEE")
  @ColumnName("EMP_NO")
  @ColumnSeq(0)
  @ColumnClass(EMPLOYEE_EMP_NO.class)
  public @interface EMP_NO {
    NumericComparison comparison() default NumericComparison.EQ;

    SortOrder orderBy() default SortOrder.ASC;
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("EMPLOYEE")
  @ColumnName("BIRTH_DATE")
  @ColumnSeq(1)
  @ColumnClass(EMPLOYEE_BIRTH_DATE.class)
  public @interface BIRTH_DATE {
    DateComparison comparison() default DateComparison.EQ;

    SortOrder orderBy() default SortOrder.ASC;
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("EMPLOYEE")
  @ColumnName("FIRST_NAME")
  @ColumnSeq(2)
  @ColumnClass(EMPLOYEE_FIRST_NAME.class)
  public @interface FIRST_NAME {
    StringComparison comparison() default StringComparison.EQ;

    SortOrder orderBy() default SortOrder.ASC;
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("EMPLOYEE")
  @ColumnName("LAST_NAME")
  @ColumnSeq(3)
  @ColumnClass(EMPLOYEE_LAST_NAME.class)
  public @interface LAST_NAME {
    StringComparison comparison() default StringComparison.EQ;

    SortOrder orderBy() default SortOrder.ASC;
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target({ ElementType.METHOD, ElementType.PARAMETER })
  @ColumnAnnotation
  @SchemaName("OBJECTOS_ORM")
  @TableName("EMPLOYEE")
  @ColumnName("HIRE_DATE")
  @ColumnSeq(4)
  @ColumnClass(EMPLOYEE_HIRE_DATE.class)
  public @interface HIRE_DATE {
    DateComparison comparison() default DateComparison.EQ;

    SortOrder orderBy() default SortOrder.ASC;
  }

  public static final class EMPLOYEE_EMP_NO extends IntColumn implements ColumnOf<EMPLOYEE> {
    private EMPLOYEE_EMP_NO() {
      super(INSTANCE, "EMP_NO");
    }

    private EMPLOYEE_EMP_NO(int value) {
      super(INSTANCE, "EMP_NO", value);
    }

    @Override
    public EMPLOYEE_EMP_NO nullValue() {
      return new EMPLOYEE_EMP_NO();
    }

    @Override
    public EMPLOYEE_EMP_NO withValue(int value) {
      return new EMPLOYEE_EMP_NO(value);
    }
  }

  public static final class EMPLOYEE_BIRTH_DATE extends DateColumn implements ColumnOf<EMPLOYEE> {
    private EMPLOYEE_BIRTH_DATE() {
      super(INSTANCE, "BIRTH_DATE");
    }

    private EMPLOYEE_BIRTH_DATE(LocalDate value) {
      super(INSTANCE, "BIRTH_DATE", value);
    }

    @Override
    public EMPLOYEE_BIRTH_DATE withValue(LocalDate value) {
      return new EMPLOYEE_BIRTH_DATE(value);
    }
  }

  public static final class EMPLOYEE_FIRST_NAME extends VarcharColumn implements ColumnOf<EMPLOYEE> {
    private EMPLOYEE_FIRST_NAME() {
      super(INSTANCE, "FIRST_NAME");
    }

    private EMPLOYEE_FIRST_NAME(String value) {
      super(INSTANCE, "FIRST_NAME", value);
    }

    @Override
    public EMPLOYEE_FIRST_NAME withValue(String value) {
      return new EMPLOYEE_FIRST_NAME(value);
    }
  }

  public static final class EMPLOYEE_LAST_NAME extends VarcharColumn implements ColumnOf<EMPLOYEE> {
    private EMPLOYEE_LAST_NAME() {
      super(INSTANCE, "LAST_NAME");
    }

    private EMPLOYEE_LAST_NAME(String value) {
      super(INSTANCE, "LAST_NAME", value);
    }

    @Override
    public EMPLOYEE_LAST_NAME withValue(String value) {
      return new EMPLOYEE_LAST_NAME(value);
    }
  }

  public static final class EMPLOYEE_HIRE_DATE extends DateColumn implements ColumnOf<EMPLOYEE> {
    private EMPLOYEE_HIRE_DATE() {
      super(INSTANCE, "HIRE_DATE");
    }

    private EMPLOYEE_HIRE_DATE(LocalDate value) {
      super(INSTANCE, "HIRE_DATE", value);
    }

    @Override
    public EMPLOYEE_HIRE_DATE withValue(LocalDate value) {
      return new EMPLOYEE_HIRE_DATE(value);
    }
  }
}
