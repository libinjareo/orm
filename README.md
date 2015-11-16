# objectos::orm [![Build Status](https://travis-ci.org/objectos/orm.svg?branch=master)](https://travis-ci.org/objectos/orm)

A lightweight Java8 ORM library.

## A simple O/RM

__objectos::orm__ is __not__ meant to be a Hibernate replacement. 
It is a simpler and lighter weight alternative.

Also, it uses annotation processing to generate code, meaning that,
when debugging, all code is visible.

__objectos::orm__ is nothing more than a collection of 
__objectos::pojo__ plugins leveraging two more projects:

- __objectos::schema__ for Java based database schema definition
- __objectos::sql__ for a Java based SQL query writing

## Maven

__objectos::orm__ is at Maven central.

```xml
<dependency>
    <groupId>br.com.objectos</groupId>
    <artifactId>orm</artifactId>
    <version>0.1.3</version>
</dependency>
<dependency>
    <groupId>br.com.objectos</groupId>
    <artifactId>orm-compiler</artifactId>
    <version>0.1.3</version>
</dependency>
```

## License

Copyright 2015 [objectos, fábrica de software LTDA](http://www.objectos.com.br)

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. 
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, 
software distributed under the License is distributed on an "AS IS" BASIS, 
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions 
and limitations under the License.

[autovalue]: https://github.com/google/auto/tree/master/value
[snap]: https://oss.sonatype.org/content/repositories/snapshots/br/com/objectos/pojo/