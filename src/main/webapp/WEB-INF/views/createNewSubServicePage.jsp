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
    <title>Add New Sub Service</title>

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
    <form:form modelAttribute="newSubService" method="post" action="addNewSubService">
        <h1 class="h3 mb-3 fw-normal">Add New Sub Service</h1>


        <div class="form-floating">
            <form:input type="text" class="form-control" id="floatingPassword" path="name" name="name"/>
            <form:label for="floatingPassword" path="name">Name</form:label>
            <p class="text-danger">${name}</p>
        </div>

        <div class="form-floating">
            <form:input type="text" class="form-control" id="floatingPassword" path="description" name="description"/>
            <form:label for="floatingPassword" path="description">Description</form:label>
            <p class="text-danger">${description}</p>
        </div>

        <div class="form-floating">
            <form:input type="number" class="form-control" id="floatingPassword" path="basePrice" name="basePrice"/>
            <form:label for="floatingPassword" path="basePrice">Base Price</form:label>
            <p class="text-danger">${basePrice}</p>
        </div>

        <form:select path="service.name">
            <form:option value="None">Select</form:option>
            <c:forEach items="${serviceList}" var="list">
                <form:option value="${list.name}" label="name">${list.name}</form:option>
            </c:forEach>
        </form:select>

        <p class="text-danger">${error}</p>
        <form:button class="w-100 btn btn-lg btn-primary" type="submit">Add New Sub Service</form:button>
    </form:form>
</main>

</body>
</html>


