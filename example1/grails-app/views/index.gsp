<%@ page import="grails.util.Environment; org.springframework.core.SpringVersion; org.springframework.boot.SpringBootVersion"
%><!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Example 1</title>
    <asset:javascript src="jquery-3.7.1.min.js"/>
    <script>
        $(function () {
            $("#buildinfocontent").load("buildinfo")
        });
    </script>

</head>
<body>
<content tag="nav">
    <li class="nav-item dropdown">
        <a href="#" class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">Application Status <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">Server: ${request.getServletContext().getServerInfo()}</a></li>
            <li><a class="dropdown-item" href="#">Host: ${InetAddress.getLocalHost()}</a></li>
            <li><a class="dropdown-item" href="#">Environment: ${Environment.current.name}</a></li>
            <li><a class="dropdown-item" href="#">App version:
                <g:meta name="info.app.version"/></a>
            </li>
            <li><a class="dropdown-item" href="#">App profile: ${grailsApplication.config.getProperty('grails.profile')}</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Grails version:
                <g:meta name="info.app.grailsVersion"/></a>
            </li>
            <li><a class="dropdown-item" href="#">Groovy version: ${GroovySystem.getVersion()}</a></li>
            <li><a class="dropdown-item" href="#">JVM version: ${System.getProperty('java.version')}</a></li>
            <li><a class="dropdown-item" href="#">Spring Boot version: ${SpringBootVersion.getVersion()}</a></li>
            <li><a class="dropdown-item" href="#">Spring version: ${SpringVersion.getVersion()}</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Reloading active: ${Environment.reloadingAgentEnabled}</a></li>
        </ul>
    </li>
    <li class="nav-item dropdown">
        <a href="#" class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">Artefacts <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">Controllers: ${grailsApplication.controllerClasses.size()}</a></li>
            <li><a class="dropdown-item" href="#">Domains: ${grailsApplication.domainClasses.size()}</a></li>
            <li><a class="dropdown-item" href="#">Services: ${grailsApplication.serviceClasses.size()}</a></li>
            <li><a class="dropdown-item" href="#">Tag Libraries: ${grailsApplication.tagLibClasses.size()}</a></li>
        </ul>
    </li>
    <li class="nav-item dropdown">
        <a href="#" class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">Installed Plugins<span class="caret"></span></a>
        <ul class="dropdown-menu dropdown-menu-right">
            <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                <li><a class="dropdown-item" href="#">${plugin.name} - ${plugin.version}</a></li>
            </g:each>
        </ul>
    </li>
</content>

<div class="svg" role="presentation">
    <div class="bg-dark-subtle text-center">
        <asset:image src="grails-cupsonly-logo-white.svg" class="w-50"/>
    </div>
</div>

<div id="content" role="main">
    <div class="container">
        <section class="row colset-2-its">
            <h1>Welcome to Example1</h1>

            <p>
                Congratulations, you have successfully started your first Grails application! At the moment
                this is the default page, feel free to modify it to either redirect to a controller or display
                whatever content you may choose. Below is a list of controllers that are currently deployed in
                this application, click on each to execute its default action:
            </p>

            <div id="controllers" role="navigation">
                <h2>Available Controllers:</h2>
                <ul>
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                        <li class="controller">
                            <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
                        </li>
                    </g:each>
                </ul>
            </div>

            <div id="otherthings" role="navigation">
                <h2>Tips and Tricks:</h2>
                <ul>
                    <li class="controller">Reading tomcatDirectoryPath from application.yml file: ${grailsApplication.config.tomcatDirectoryPath}</li>
                    <li class="controller"><g:link controller="employee" action="printMemInfo">Print Memory Used Info</g:link></li>
                </ul>
            </div>

            <div id="RunningSystemInfo" role="navigation">
                <h2>Running System Info</h2>
                <ul style="list-style-type: none;">
                    <li>App fullname: ${grails.util.Metadata.current.getApplicationName()}</li>
                    <li>Build Date: ${grails.util.Metadata.current.getApplicationVersion()}</li>
                    <li>Build Environment: ${grails.util.Environment.current.name}</li>
                    <li>---</li>
                    <li>TestMode: ${grailsApplication.config.myEnvironmentVariables}</li>
                    <li>---</li>
                    <li>Groovy Version: ${GroovySystem.getVersion()}</li>
                    <li>JVM Version: ${System.getProperty('java.version')}</li>
                    <li>Grails Version: ${grailsApplication.metadata.getGrailsVersion()}</li>
                    <li>---</li>
                    <li>environmment = ${grails.util.Metadata.current.getProperties()}</li>
                    <li>---</li>
                    <div id="buildinfocontent">test</div>
                </ul>
            </div>


        </section>
    </div>
</div>

</body>
</html>
