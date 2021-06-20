<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.83.1">
    <title>Add New Service</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sign-in/">


    <!-- Bootstrap core CSS -->
    <link href="/static/bootstrap.min.css" rel="stylesheet">

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
    <link href="/static/loginPageStyleSheet.css" rel="stylesheet">
</head>
<body class="text-center">

<main class="form-signin">
    <form:form modelAttribute="newService" action="addNewService" method="post">
        <h1 class="h3 mb-3 fw-normal">Add New Service</h1>


        <div class="form-floating">
            <form:input type="text" class="form-control" id="floatingPassword" path="name" name="name"/>
            <form:label for="floatingPassword" path="name">Name</form:label>
            <p class="text-danger">${name}</p>
        </div>

        <p class="text-danger">${error}</p>
        <form:button class="w-100 btn btn-lg btn-primary" type="submit">Add New Service</form:button>
    </form:form>
</main>

</body>
</html>


