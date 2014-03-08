# Patere
Tiny library for building path of test resources based on package, class name and optionally test method name.
This can help keep test suite organized if it heavily uses some file resources. 
Developer can just drop resources belonging to the test into directory of certain structure and
use Patere in test to build the test resources path.  

It uses stack trace of current thread to build the path, **therefore it's not suitable for production**. 
Uses Java 7.

## Maven dependency
```xml
<dependency>
	<groupId>net.lkrnac.lib</groupId>
	<artifactId>patere</artifactId>
	<version>${version}</version>
	<scope>test</scope>
</dependency>
```
Find version in GitHub releases tab.

## API
JavaDoc of current development branch:
https://lkrnac.ci.cloudbees.com/job/patere/ws/target/site/apidocs/index.html

## Examples
Call from <code>net.lkrnac.someapp.AppTest.testSomeMethod()</code> 
```java
String testResourcesPath = new Patere().getResourcesPathForMethod(); 
//testResourcesPath == "src/test/resources/net.lkrnac.someapp/AppTest/testSomeMethod/"

testResourcesPath = new Patere(false).getResourcesPathForMethod(); 
//testResourcesPath == "src/test/resources/net/lkrnac/someapp/AppTest/testSomeMethod/"

testResourcesPath = new Patere().getResourcesPathForClass(this.getClass.getName()); 
//testResourcesPath == "src/test/resources/net.lkrnac.someapp/AppTest/"

testResourcesPath = new Patere(false).getResourcesPathForClass(this.getClass.getName()); 
//testResourcesPath == "src/test/resources/net/lkrnac/someapp/AppTest/"

testResourcesPath = new Patere("test").getResourcesPathForClass(this.getClass.getName()); 
//testResourcesPath == "test/net/lkrnac/someapp/AppTest/"
```

##Development
* Project page / source code repository: https://github.com/lkrnac/patere
* Continuous integration: https://lkrnac.ci.cloudbees.com/job/patere/ 
* Test coverage: https://lkrnac.ci.cloudbees.com/job/patere/ws/target/site/jacoco/index.html
* Issue tracking: https://github.com/lkrnac/patere/issues