<%--
  Created by IntelliJ IDEA.
  User: china
  Date: 2025-10-12
  Time: 07:05
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Testing Spring Security Tags</title>

    <style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
    table.center {
        margin-left: auto;
        margin-right: auto;
    }
    </style>
</head>

<body>
Testing Spring Security Tags.

<table style="width:60%" class="center">
    <tr>
        <th>Tag Name</th>
        <th>Tag Result</th>
        <th>Notes</th>
    </tr>
    <tr>
        <td>sec:ifNotLoggedIn</td>
        <td><sec:ifNotLoggedIn>you are not login.</sec:ifNotLoggedIn></td>
        <td></td>
    </tr>
    <tr>
        <td>sec:ifLoggedIn</td>
        <td><sec:ifLoggedIn>you are login.</sec:ifLoggedIn></td>
        <td></td>
    </tr>
    <tr>
        <td>sec:ifAllGranted</td>
        <td>
            <sec:ifAllGranted roles='ROLE_ADMIN'>user is ROLE_ADMIN</sec:ifAllGranted>
        </td>
        <td></td>
    </tr>
    <tr>
        <td>sec:ifAnyGranted</td>
        <td><sec:ifAnyGranted roles='ROLE_SUPPORT,ROLE_SUPERVISOR'>Only ROLE_SUPPORT and ROLE_SUPERVISOR</sec:ifAnyGranted></td>
        <td></td>
    </tr>
    <tr>
        <td>sec:ifNotGranted</td>
        <td><sec:ifNotGranted roles='ROLE_USER'>Not a ROLE_USER</sec:ifNotGranted>        </td>
        <td></td>
    </tr>
    <tr>
        <td>sec:loggedInUserInfo</td>
        <td><sec:loggedInUserInfo field='username'/></td>
        <td></td>
    </tr>
    <tr>
        <td>Original username if switched</td>
        <td>

            <sec:ifSwitched>
                <form action='${request.contextPath}/logout/impersonate' method='POST'>
                    <input type='submit' value="Resume as ${grails.plugin.springsecurity.SpringSecurityUtils.switchedUserOriginalUsername}"/>
                </form>
            </sec:ifSwitched>

        </td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
    </tr>

</table>
</body>
</html>