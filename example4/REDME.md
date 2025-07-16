
### 1Password CLI 
#### Setup CLI
Install CLI app in your laptop following instructions [here](https://developer.1password.com/docs/cli/get-started/)
#### Enable CLI in app
- open 1Passowrd app 
- Open settings --> Developer 
- Check "Show 1Password Developler"
- Check "Integrate with 1Password CLI"

#### Common command lines 
- op signin 
- op --help
- op vault list
- op item get Testing-Login --reveal --format json
- op item get Testing-Login --fields password --reveal
- op item get Testing-Login --fields password --reveal --format json
- op read op://Employee/Testing-Login/username

### Gradle 
#### Option 1
Set System environment variable and fill it with 1Password. Then use it in gradle or your code. <br>
`export MY_USERNAME=$(op read "op://Employee/Testing-Login/username")` <br>
Then build your code <br>
`./gradlew build`

#### Option 2
Send variable as you build with gradle <br>
`./gradlew build -PmyUsername=$(op read "op://Employee/Testing-Login/username")` <br>
Then use it in the code <br>
`def username = project.findProperty("myUsername") ?: "defaultUser"` <br>

#### Option 3
Run system command line from withing Gradle. <br>
```
task fetchUsername {
    doLast {
        def command = ["op", "read", "op://Employee/Testing-Login/username"]
        def process = command.execute()
        def output = new StringBuffer()
        def error = new StringBuffer()

        process.waitForProcessOutput(output, error)

        if (process.exitValue() == 0) {
            println "Username: ${output.toString().trim()}"
        } else {
            println "Failed to fetch username: ${error.toString()}"
        }
    }
}
```
Then run task 
```./gradlew fetchUsername```


### Docs
[1Password CLI](https://developer.1password.com/docs/cli#quick-st)

[1Password Gradle](https://github.com/ValtechMobility/gradle-onepassword-credentials-plugin)

[Maven Repository](https://mvnrepository.com/artifact/com.github.anonymousmister.gradle.plugin/1password-plugin/0.0.8)

[1Password Github](https://github.com/1Password)

[1Password Intellij Plugin](https://plugins.jetbrains.com/plugin/19698-1password) Source is in [Github](https://github.com/shyim/idea-1password)

[Uninstall 1Password](https://support.1password.com/uninstall-1password/?mac)