package br.com.objectos.pojo.plugin;

import java.time.LocalDate;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction",
    "br.com.objectos.way.orm.compiler.InjectPlugin"
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
