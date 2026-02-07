# grails-project-helper

Purpose of this project is to help Grails bigginer. To show developer tips and tricks, plugin implementation,
internal commands, config files, etc.

## Grails 7.0.7 Documentation

- [User Guide](https://docs.grails.org/7.0.0-M1/guide/index.html)
- [API Reference](https://docs.grails.org/7.0.0-M1/api/index.html)
- [Grails Guides](https://guides.grails.org/index.html)
---

## Feature asset-pipeline-grails documentation

- [Grails Asset Pipeline Core documentation](https://github.com/bertramdev/asset-pipeline#readme)

## Feature geb-with-testcontainers documentation

- [Grails Geb Functional Testing for Grails with Testcontainers documentation](https://github.com/grails/geb#readme)

- [https://www.gebish.org/manual/current/](https://www.gebish.org/manual/current/)

## Feature scaffolding documentation

- [Grails Scaffolding Plugin documentation](https://docs.grails.org/7.0.0-M1/guide/scaffolding.html)

- [https://github.com/gpc/fields#readme](https://github.com/gpc/fields#readme)


## Minimum System Requirements

| Tech   | Version |
|--------|-------|
| Java   | 21.0.10-zulu |
| gradle | 8     |
| groovy | 4     |
| Grails | 7     |

#### java 24 Warning
You can enable and disable warning using --enable-native-access and --illegal-native-access.
See more information  [here](https://inside.java/2024/12/09/quality-heads-up/) 
also read this Grails issue [ticket](https://github.com/apache/grails-core/issues/13752)


```angular2html
WARNING: A restricted method in java.lang.System has been called
WARNING: java.lang.System::loadLibrary has been called by org.fusesource.hawtjni.runtime.Library in an unnamed module (file:/Users/china/.sdkman/candidates/grails/7.0.0-RC1/lib/grails-cli-7.0.0-RC1-all.jar)
WARNING: Use --enable-native-access=ALL-UNNAMED to avoid a warning for callers in this module
WARNING: Restricted methods will be blocked in a future release unless native access is enabled
| Running application...
WARNING: A terminally deprecated method in sun.misc.Unsafe has been called
WARNING: sun.misc.Unsafe::objectFieldOffset has been called by org.gradle.internal.impldep.com.google.common.util.concurrent.AbstractFuture$UnsafeAtomicHelper (file:/Users/china/.sdkman/candidates/grails/7.0.0-RC1/lib/grails-cli-7.0.0-RC1-all.jar)
WARNING: Please consider reporting this to the maintainers of class org.gradle.internal.impldep.com.google.common.util.concurrent.AbstractFuture$UnsafeAtomicHelper
WARNING: sun.misc.Unsafe::objectFieldOffset will be removed in a future release
Grails application running at http://localhost:8083/example3 in environment: development
```

## Install Grails
The easiest way to setup Grails in your local environment is by using [sdkamn](https://sdkman.io/).
Then add the file ".sdkmanrc" in every root directory of a project.
Every time entering the directory it will load tech stack listed in .sdkman.
Essentially you can run multiple different versions in the same local environment. For example this .sdkmanrc

```groovy
# Enable auto-env through the sdkman_auto_env config
# Add key=value pairs of SDKs to use below
java=21.0.10-zulu
gradle=8.14.4
groovy=4.0.30
grails=7.0.7
```

## Examples
Examples are divided into different grails projects.
Developers intention should be to learn from examples. Study the code and copy them to their own project.
Examples may not be runnable.

- [Example1](example1/README.md): Basic Grails tips and tricks.
- [Example2](example2/README.md): Multiple DataSources
- [Example3](example3/README.md): Spring Seruciry Core and REST API.
- [Example4](example4/README.md): Quartz Scheduler.
- [Example5](example5/README.md): Consume REST API. Example include connecting to Netsuite, Salesforce, JIRA.


