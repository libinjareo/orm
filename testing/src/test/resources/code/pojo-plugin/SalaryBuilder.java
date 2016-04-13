package br.com.objectos.pojo.plugin;

import java.time.LocalDate;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction"
})
interface SalaryBuilder {
  SalaryBuilderEmployee employee(Employee employee);

  interface SalaryBuilderEmployee {
    SalaryBuilderSalary salary(int salary);
  }

  interface SalaryBuilderSalary {
    SalaryBuilderFromDate fromDate(LocalDate fromDate);
  }

  interface SalaryBuilderFromDate {
    SalaryBuilderToDate toDate(LocalDate toDate);
  }

  interface SalaryBuilderToDate {
    Salary build();
  }
}
