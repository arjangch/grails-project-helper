# **Data Source**
## **H2**
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
Enable H2 database [console](http://localhost:8082/example2/h2-console/) for each environment in yml. [See doc](https://docs.grails.org/7.0.0-M1/guide/conf.html#databaseConsole)
```yaml
environments:
    development:
        spring.h2.console.enabled: true
    production:
        spring.h2.console.enabled: false
```

## **MySql**
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

### **Multiple Data source**
Multiple data sources can be configured..
```yaml
environments:
    development:
      dataSource:
          dbCreate: create-drop
          username: adminExample2
          password: 'adminExample2'
          url: jdbc:h2:mem:devDb;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE
      dataSources:
        myotherdb:
          dbCreate: create-drop
          username: 'adminSeconddb2'
          password: 'adminSeconddb2'
          url: jdbc:h2:mem:secondDb;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE
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
For example in the above code we don't want versioning because this table is also used in other apps.

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

### **DataSource Properties**
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
