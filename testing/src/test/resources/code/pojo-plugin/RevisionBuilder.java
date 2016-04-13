package br.com.objectos.pojo.plugin;

import java.time.LocalDate;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.pojo.compiler.WritingPojoCompiler",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction",
    "br.com.objectos.way.orm.compiler.ColumnPropertyBuilderPropertyAction",
    "br.com.objectos.way.orm.compiler.InjectPlugin"
})
interface RevisionBuilder {
  RevisionBuilderDate date(LocalDate date);

  interface RevisionBuilderDate {
    RevisionBuilderDescription description(String description);
  }

  interface RevisionBuilderDescription {
    Revision build();
  }
}
