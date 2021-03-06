<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.83.1">
    <title>user login</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sign-in/">



    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/static/bootstrap.min.css"/>" rel="stylesheet">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>


    <!-- Custom styles for this template -->
    <link href="<c:url value="/static/loginPageStyleSheet.css"/>" rel="stylesheet">
</head>
<body class="text-center">

<main class="form-signin">
    <form:form modelAttribute="loginUser" action="/userLogin" method="post">
        <h1 class="h3 mb-3 fw-normal">User Login Page</h1>
        <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

        <div class="form-floating">
            <form:input type="text" class="form-control" id="email" path="email" name="email"/>
            <form:label for="email" path="email">Email</form:label>
            <p class="text-danger">${email}</p>
        </div>
        <div class="form-floating">
            <form:input type="password" class="form-control" id="password" path="password" name="password" />
            <form:label for="password" path="password">Password</form:label>
            <p class="text-danger">${password}</p>
        </div>
        <div>
            <c:if test="${errorAlert ne null}">
                <blockquote class="blockquote">
                    <p style="  margin: auto;width: 100%;border: 3px solid #be081d;padding: 10px;"
                       class="mb-0">${errorAlert}</p>
                </blockquote>
            </c:if>
        </div>
        <form:button class="w-100 btn btn-lg btn-primary" type="submit">Login</form:button>
    </form:form>
</main>
</body>
</html>


