## Example 1

## Working with Application Yaml
See [application.yml](example1/grails-app/conf/application.yml)

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
Running server port and context for each environment
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
To increase security move setting to Development environment. 
```yaml
environments:
  development:
      dataSource:
          driverClassName: org.h2.Driver
          username: sa
          password: ''
          pooled: true
          jmxExport: true
          dbCreate: create-drop
          url: jdbc:h2:mem:devDb;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE
```
Enable H2 database console in yml and go to [Console](http://localhost:8081/example1/h2-console/).
```yaml
environments:
    development:
        spring.h2.console.enabled: true
    production:
        spring.h2.console.enabled: false
```
[See doc](https://docs.grails.org/7.0.0-M1/guide/conf.html#databaseConsole)

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
Multilple data sources can be configured. Usually.
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
class Students {
    static mapping = {
        version false
        datasource 'myotherdb'
    }
}
```
For example in the above code we don't want versioning because this table is also used in other applications. 

#### Other Database Settings
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

## Tips and Tricks 
- [Print Mem Info](http://localhost:8081/example1/employee/getMemInfo)
