# Patere
Tiny library for building path of test resources based on package, class name and optionally test method name.

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
