<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
    <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    <asset:stylesheet src="application.css"/>
    <g:layoutHead/>
</head>

<body>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="${createLink(uri: '/')}"><asset:image class="w-75" src="grails.svg" alt="Grails Logo"/></a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" aria-expanded="false" id="navbarContent">
            <ul class="navbar-nav">
                <g:pageProperty name="page.nav"/>
            </ul>
        </div>
    </div>
</nav>

<g:layoutBody/>



<div id="spinner" class="position-absolute top-0 end-0 p-1" style="display:none;">
    <div class="spinner-border spinner-border-sm" role="status">
        <span class="visually-hidden">Loading...</span>
    </div>
</div>


<asset:javascript src="application.js"/>

</body>
</html>
