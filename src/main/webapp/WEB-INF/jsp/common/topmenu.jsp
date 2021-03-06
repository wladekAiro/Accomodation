<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">EGERTON UNIVERSITY STUDENTS ACCOMMODATION PORTAL</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAnonymous()">
                    <li><a href="/users/signupform">Signup</a></li>
                    <li><a href="/users/loginform">Login</a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <sec:authentication property="principal" var="principal" />
                    <li><a href="#">${principal.user.name}</a></li>
                    <li><a href="/users/logout">Logout</a></li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</div>