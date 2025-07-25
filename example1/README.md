# **Grails 7.0.0.M4**
Just fast and helpful notes. 

### Command Lines
Build war files
```csh
gradle -Dgrails.env=dev assemble
gradle -Dgrails.env=prod assemble
```
Build custom environment
```csh
gradle -Dgrails.env=staging assemble
gradle -Dgrails.env=preproduction assemble
```
### Running app locally
```csh
grails run-app
./grailsw run-app
./grailsw run
```
Run custom environments
```csh
./grailsw -Dgrails.env=staging run-app
./grailsw -Dgrails.env=preproduction run-app
```
or running app locally with gradle
```csh
gradle bootRun
./gradlew bootRun
./gradlew -Dgrails.env=staging  bootRun
```
Run specific commends grails with gradle
```csh
./gradlew runCommand -Pargs="generate-all com.micompany.MyStaff"
./gradlew runCommand -Pargs="create-domain-class com.micompany.MyStaff"
./grailsw create-domain-class com.micompany.Products
```
### Upgrade wrapper
```csh
./grailsw update-wrapper
```
List Gradle tasks
```csh
./gradlew tasks
```
Publish to local maven repo.
```csh
./gradlew publishToMavenLocal
```
### DB
H2 DB console [http://localhost:8081/example1/h2-console](http://localhost:8081/example1/h2-console)

### Running System Info
See example running in [index.gsp](grails-app/views/index.gsp)

### Runtime Memory information
Get system runtime memory usage in controller. 
[Print Mem Info into console](http://localhost:8081/example1/employee/getMemInfo)

### Access Configuration Information
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
### Environment Configuration variables
This has been deprecated in Gradle 8
```groovy
${grailsApplication.config.myEnvironmentVariables}
```
Use this instead
```groovy
grailsApplication.config.get("myEnvironmentVariables")
```

### Access System Information
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

Also see [Grails config](https://docs.grails.org/7.0.0-M1/guide/conf.html)

### application.yml 
Configuration File See [application.yml](grails-app/conf/application.yml)

### Global Variables
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

### Environment Variables
Variable that are dependent on running environment. As example shows Groovy
can get proper variable data type.
```yaml
environments:
  development:
    myEnvironmentVariables: 'This is Dev variable'
    tomcatDirectoryPath: '/opt/stage_tomcat/'
    testMode: true //For development runs as test
    fileAge: 30 //different for each environment
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

### Change log level
Change environment log level can be changed
```yaml
  development:
    logging.level.com.arjang: INFO
```

### Application Server settings
Change server port and context for each environment can be set.
```yaml
environments:
  development:
    server:
      port: 8081
      servlet:
        context-path: '/example1'
```
See [Example2](../example2/README.md) for DataSources

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
