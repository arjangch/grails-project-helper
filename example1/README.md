## Example 1

## Gradle 8

## Command Lines
Build war files
```csh 
gradle -Dgrails.env=dev assemble
gradle -Dgrails.env=prod assemble
```
If custom environment is setup in yml file 
```csh
gradle -Dgrails.env=staging assemble
gradle -Dgrails.env=preproduction assemble
```
Running app locally
```csh 
./grailsw run-app
./grailsw -Dgrails.env=staging run-app
./grailsw -Dgrails.env=preproduction run-app
```
or running app locally with gradle 
```csh 
./gradlew bootRun 
./gradlew -Dgrails.env=staging  bootRun
```
Run specific commends grails with gradle 
```csh 
./gradlew runCommand -Pargs="generate-all com.micompany.MyStaff"
./gradlew runCommand -Pargs="create-domain-class com.micompany.MyStaff"
./grailsw create-domain-class com.micompany.Products
```
Upgrade wrapper
```csh
./grailsw update-wrapper
```
Other gradle commands. 
```csh
./gradlew tasks
./gradlew publishToMavenLocal 
```

## Running System Info 
See example running in [index.gsp](grails-app/views/index.gsp)

#### Runtime Memory information 
TODO
[Print Mem Info](http://localhost:8081/example1/employee/getMemInfo)

#### Access Configuration Information
Also read 
Application name
```groovy 
${grails.util.Metadata.current.getApplicationName()}
```
Application Version
```groovy
${grails.util.Metadata.current.getApplicationVersion()}
```
Environment name
```groovy
${grails.util.Environment.current.name}
```
#### Environment Configuration variables
This has been depricated in Gradle 8
```groovy
${grailsApplication.config.myEnvironmentVariables}
```
Use this insetead
```groovy
grailsApplication.config.get("myEnvironmentVariables")
```

#### Access System Information
Java version 
```groovy
${System.getProperty('java.version')}
```
Groovy version 
```groovy
${GroovySystem.getVersion()}
```
Grails version
```groovy
${grailsApplication.metadata.getGrailsVersion()}
```

Also see https://docs.grails.org/7.0.0-M1/guide/conf.html

## Working with Application Yaml
See [application.yml](grails-app/conf/application.yml)

### Variables
#### Global credentials
same global variables are used in all running environment.
```yaml
myGlobalVariables:
  username: 'adminu'
  password: 'adminp'
  other: 'somethingelse'
```
```groovy
class BootStrap {
    def grailsApplication // inject
    def init = { servletContext ->
        //Global Variables
        def username = grailsApplication.config.myGlobalVariables.username
        username = grailsApplication.config.get("myGlobalVariables.username")
        username = grailsApplication.config.myGlobalVariables.get("username")
    }
}
```

#### Environment Variables
Variable that are dependent on environment code is running. As example shows Groovy
can get proper variable data type.
```yaml
environments:
  development:
    myEnvironmentVariables: 'This is Dev variable'
    tomcatDirectoryPath: '/opt/stage_tomcat/'
    testMode: true
    fileAge: 30
```
```groovy
class BootStrap {
    def grailsApplication // inject
    def init = { servletContext ->
        // Environment variables
        def myEnvironmentVariables = grailsApplication.config.get("myEnvironmentVariables")
        boolean testmode = grailsApplication.config.get("testMode")
        int fileage = grailsApplication.config.get("fileAge")
    }
}
```

In GSP files get values from yml file. See file /view/index.gsp.
```groovy
${grailsApplication.config.tomcatDirectoryPath}
```

#### Change log level
Running environment log level can be changed
```yaml
  development:
    logging.level.com.arjang: INFO
```

### Application Server settings
Server port and context for each environment can be set.
```yaml
    server:
      port: 8081
      servlet:
        context-path: '/example1'
```

### Data Source
#### H2
By default, there is an H2 database installed you can disable it. Comment out or change password in yml
```yaml
dataSource:
  driverClassName: org.h2.Driver
  username: sa
  password: ''
  pooled: true
  jmxExport: true
```
To increase security use different credentials in each environment.
```yaml
environments:
  development:
      dataSource:
          dbCreate: create-drop
          username: sa
          password: ''
          pooled: true
          jmxExport: true
          url: jdbc:h2:mem:devDb;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE
  production:
      dataSource:
          dbCreate: none
          username: sa
          password: ''
          pooled: true
          jmxExport: false
          url: jdbc:h2:./prodDb;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE
```
Enable H2 database [console](http://localhost:8081/example1/h2-console/) for each environment in yml. [See doc](https://docs.grails.org/7.0.0-M1/guide/conf.html#databaseConsole)
```yaml
environments:
    development:
        spring.h2.console.enabled: true
    production:
        spring.h2.console.enabled: false
```


#### MySql
Other databases need to be injected. For example, add following to build.gradle
```groovy
dependencies {
    // mysql 8
    // https://mvnrepository.com/artifact/mysql/mysql-connector-java
    implementation 'mysql:mysql-connector-java:8.0.33'
}
```
Then in yml setup connection to DB
```yaml
environments:
  development:
      dataSource:
          dbCreate: update
          driverClassName: "com.mysql.cj.jdbc.Driver"
          dialect: "org.hibernate.dialect.MySQL8Dialect"
          url: "jdbc:mysql://localhost:3306/example1?useUnicode=yes&characterEncoding=UTF-8&useSSL=false"
          username: "exampleAdmin"
          password: "examplePassword"
```
#### Multiple Data source
Multiple data sources can be configured..
```yaml
environments:
    development:
        dataSources:
          myotherdb:
            dbCreate: update
            driverClassName: "com.mysql.cj.jdbc.Driver"
            dialect: "org.hibernate.dialect.MySQL8Dialect"
            url: "jdbc:mysql://localhost:3306/example1?useUnicode=yes&characterEncoding=UTF-8&useSSL=false"
            username: "exampleAdmin"
            password: "examplePassword"
```
Then map the domain class to use the secondary DB.
```groovy
class Employee {
    static mapping = {
        version false
        datasource 'myotherdb'
    }
}
```
For example in the above code we don't want versioning because this table is also used in other applications.

To work with the secondary DB in the controller add transactional to class.
```groovy
@Transactional(readOnly = true, connection = "myotherdb")
class EmployeeController {}
```
or add transactional to individual method.
```groovy
@Transactional(readOnly = true, connection = "myotherdb")
def index(Integer max) { }
```
Also we can use the secondary DB in the service class or just in section of a code block. See [Grails Documentation](https://docs.grails.org/latest/guide/services.html#multipleDataSources)
```groovy
def usingTransaction() {
    Employee.withTransaction {
        def oneemployee = new Employee(name: "Louis2", lastname: "mandy2", employeeId: 22222, employeeNotes: "my 2 robot112").save(flush: true, failOnError: true)
        log.info '-2.1--=' + Employee.list()
    }
    return "back from usingTransaction"
}

def createNewSession(){
    Employee.withNewSession { session ->
        log.info '-2.2--='+Employee.list()
    }
    return "back from createNewSession"
}

def usingSession(){
    Employee.withSession { session ->
        log.info '-3.2--='+Employee.list()
    }
    return "back from usingSession"
}
```

#### DataSource Properties
Usually Production DB needs other setting to manage high volume and high availability. See [The Tomcat JDBC Connection Pool](https://tomcat.apache.org/tomcat-10.0-doc/jdbc-pool.html)

```yaml
  production:
    dataSource:
      dbCreate: none
      url: jdbc:h2:./prodDb;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE
      properties:
        jmxEnabled: true
        initialSize: 5
        maxActive: 50
        minIdle: 5
        maxIdle: 25
        maxWait: 10000
        maxAge: 600000
        timeBetweenEvictionRunsMillis: 5000
        minEvictableIdleTimeMillis: 60000
        validationQuery: SELECT 1
        validationQueryTimeout: 3
        validationInterval: 15000
        testOnBorrow: true
        testWhileIdle: true
        testOnReturn: false
        jdbcInterceptors: ConnectionState
        defaultTransactionIsolation: 2
```
## Others
### Inject text file into GSP
* Add following to UrlMappings.goovy 
```groovy
"/buildinfo"(view: "/buildinfo")
```
* Add file buildinfo.gsp to /views/ directory 
* Add jquery-3.7.1.min.js to /assets/javascripts/
* Add following to gsp page
```html
<asset:javascript src="jquery-3.7.1.min.js"/>
```
* Add a JS function in < header > to load the file 
```javascript
    <script>
        $(function () {
            $("#buildinfocontent").load("buildinfo")
        });
    </script>
```
* Add < div > to include content of the file. 
```html
<div id="buildinfocontent"></div>
```

