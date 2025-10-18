<%@ page import="grails.util.Environment; org.springframework.core.SpringVersion; org.springframework.boot.SpringBootVersion"
%><!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Grails Example 3</title>
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

<div id="content" role="main">
    <div class="container">
        <section class="row colset-2-its">
            <h1>Grails Example 3</h1>

            <p>In this example we learn different kind of permission access and REST access to data.</p>

            <p>
                <sec:ifNotLoggedIn>
                    <g:link controller='login' action='auth'>Login</g:link>
                </sec:ifNotLoggedIn>
                <sec:ifLoggedIn>
                    <g:form controller="logout" action="index" method="POST">
                        Your username is <b><sec:username/></b>. <br><g:submitButton name="logout"/>
                    </g:form>
                </sec:ifLoggedIn>
            </p>

        </section>
    </div>
</div>
--
<div class="container">

    <div id="controllers" role="navigation">
        <h2>Examples: </h2>
        <ol>
            <li><a href="student" target="_blank">Student</a></li>
            <li><a href="teacher" target="_blank">Teacher</a></li>
            <li><a href="cars" target="_blank">Cars</a> Testing finegrained control. In this example Car show is restricted to ROLE_SUPPORT and edit is restricted to ROLE_ADMIN.
            See <a href="https://apache.github.io/grails-spring-security/7.0.x/core-plugin/guide/index.html#expressions" target="_blank">Doc</a> </li>

            <li><a href="bikes" target="_blank">Bikes</a> Show is restricted by username authentication.name == 'admin' </li>
            <li><a href="http://localhost:8083/example3/student/showAsJson/2" target="_blank">One student as JSON</a> or with
                <pre><code>curl -i -X GET -H "Accept:application/json" -H "Content-Type: application/json" http://localhost:8083/example3/student/showAsJson/2</code></pre></li>
            <li><a href="http://localhost:8083/example3/student/showAsXml/2" target="_blank">One student as XML</a> or with
                <pre><code>curl  -i -X GET -H "Accept:application/xml" -H "Content-Type: application/xml" http://localhost:8083/example3/student/showAsXml/2</code></pre></li>
            <li><a href="http://localhost:8083/example3/student/showRenderJson/2" target="_blank">One student render JSON</a></li>
            <li><a href="http://localhost:8083/example3/student/showRenderXml/2" target="_blank">One student render XML</a></li>
            <li>Add student: Url need to be added to POST in RequestMap.
                <pre><code>curl -i -X POST -H "Accept:application/json" -H "Content-Type: application/json" http://localhost:8083/example3/student/addStudent -d '{name:"studentA", studentId:"234"}'</code></pre>
            </li>
            <li>Static assets are located in <code>grails-app/assets</code>. Add subdirectories to keep things organized.
                Make sure <code>/assets/**</code> is added to RequestMap. For example <a href="http://localhost:8083/example3/assets/robots.txt">Robots.txt</a></li>
            <li><a href="http://localhost:8083/example3/testSpringTags">Testing Spring Security tags</a></li>
            <li></li>
        </ol>

        <h2>Docs:</h2>
        <ol>
            <li><a href="https://apache.github.io/grails-spring-security/7.0.x/core-plugin/guide/index.html" target="_blank">Spring Security Core Plugin - Reference Documentation</a></li>
            <li><a href="https://apache.github.io/grails-spring-security/7.0.x/ui-plugin/guide/index.html" target="_blank">Spring Security UI Plugin - Reference Documentation</a></li>
            <li><a href=""></a></li>
        </ol>
    </div>

    <div id="controllers" role="navigation">
        <h2>Available Controllers:</h2>

        <ul>
            <li><a href="http://localhost:8083/example3/h2-console/" target="_blank">h2-console</a></li>
            <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName }}">
                <li class="controller">
                    <g:link controller="${c.logicalPropertyName}" target="_blank">${c.fullName}</g:link>
                </li>
            </g:each>
        </ul>

    </div>
</div>

</body>
</html>
