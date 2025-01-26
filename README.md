# grails-project-helper

Purpose of this project is to help Grails bigginer. To show developer tips and tricks, plugin implementation, 
internal commands, config files, etc. 

## Minimum System Requirements
These project are bulid with Grails 7 but sometime I will explain how it is done in older versions. 

| Tech   | Version |
|--------|---------|
| Java   | 17      |
| gradle | 8       |
| groovy | 4       |
| Grails | 7       |

## Examples 
Examples are divided into different grails projects. 
Developers intention should be to learn from examples. Study the code and copy them to their own project. 
Examples may not be runnable.


## Install Grails
The easiest way to setup Grails is using [sdkamn](https://sdkman.io/).
Then add the file ".sdkmanrc" into every root directory of a project.
Every time entring the directory it will load tech stack listed in .sdkman.
Essentially you can run multiple different versions in the same local environment. For example this .sdkman
```groovy
# Enable auto-env through the sdkman_auto_env config
# Add key=value pairs of SDKs to use below
java=17.0.13-zulu
gradle=8.10.2
groovy=4.0.24
grails=7.0.0-M1
```

[Example1](example1.md): Contains basic Grails tips and tricks. 

[Example2](example2.md): Contains examples of Oauth2. 

