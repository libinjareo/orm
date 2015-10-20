package br.com.objectos.schema.it;

import br.com.objectos.code.Testing;
import br.com.objectos.schema.annotation.Schema;

@Schema(migrations = { V001__First_Migration.class })
@Testing
public class OBJECTOS_SQL {

}