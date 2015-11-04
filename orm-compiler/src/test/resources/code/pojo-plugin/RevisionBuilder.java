package br.com.objectos.pojo.plugin;

import java.time.LocalDate;
import javax.annotation.Generated;

@Generated({
    "br.com.objectos.pojo.compiler.PojoCompiler",
    "br.com.objectos.pojo.plugin.StandardBuilderPropertyAction"
})
interface RevisionBuilder {
  RevisionBuilderSeq seq(int seq);

  interface RevisionBuilderSeq {
    RevisionBuilderDate date(LocalDate date);
  }

  interface RevisionBuilderDate {
    RevisionBuilderDescription description(String description);
  }

  interface RevisionBuilderDescription {
    Revision build();
  }
}