## Example 1

## Working with Application Yaml
See [application.yml](example1/grails-app/conf/application.yml)

### Variables
#### Global credentials
Sometimes it doesn't depend on which environment we are running. These variables are common between all of them. See myGlobalVariables 
Get values out of yml config
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

#### Environment based credentials
Sometimes we want to setup a global variable that are used regardless of environment our code is running. As example shows Groovy 
can get proper variable data type. 
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

#### Change log level
At every environment log level can be changed
```yaml
  development:
    logging.level.com.arjang: INFO
```

### Application Server settings 
We can chang server port and context for each environment
```yaml
    server:
      port: 8081
      servlet:
        context-path: '/example1'
```


### Data Source 
#### MySql
#### Other Settings
#### H2
#### Multiple Data source 
