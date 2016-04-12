package br.com.objectos.pojo.plugin;

import java.time.LocalDate;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.way.orm.compiler.InjectPlugin",
    "br.com.objectos.way.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.way.pojo.plugin.StandardBuilderPropertyAction"
})
interface EmployeeBuilder {
  EmployeeBuilderEmpNo empNo(int empNo);

  interface EmployeeBuilderEmpNo {
    EmployeeBuilderBirthDate birthDate(LocalDate birthDate);
  }

  interface EmployeeBuilderBirthDate {
    EmployeeBuilderFirstName firstName(String firstName);
  }

  interface EmployeeBuilderFirstName {
    EmployeeBuilderLastName lastName(String lastName);
  }

  interface EmployeeBuilderLastName {
    EmployeeBuilderHireDate hireDate(LocalDate hireDate);
  }

  interface EmployeeBuilderHireDate {
    Employee build();
  }
}
