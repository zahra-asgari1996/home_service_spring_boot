<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.83.1">
    <title>Search Users</title>

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

<div style="margin-right: 150px;margin-left: 150px">
    <form:form modelAttribute="users" action="/user/searchUser" method="post">
        <h1 class="h3 mb-3 fw-normal">Search Users</h1>

        <div class="form-floating">
            <form:input type="text" class="form-control" id="floatingInput" path="name" name="name"/>
            <form:label for="floatingInput" path="name">Name</form:label>
            <p class="text-danger">${name}</p>
        </div>
        <div class="form-floating">
            <form:input type="text" class="form-control" id="floatingPassword" path="lastName" name="lastName"/>
            <form:label for="floatingPassword" path="lastName">Last Name</form:label>
            <p class="text-danger">${lastName}</p>
        </div>
        <div class="form-floating">
            <form:input type="text" class="form-control" id="floatingPassword" path="email" name="email"/>
            <form:label for="floatingPassword" path="email">Email</form:label>
            <p class="text-danger">${email}</p>
        </div>
        <div class="form-floating">
            <form:input type="text" class="form-control" id="floatingPassword" path="rate" name="rate"/>
            <form:label for="floatingPassword" path="rate">Rate</form:label>
            <p class="text-danger">${rate}</p>
        </div>

        <div class="form-floating">
            Expert  <form:checkbox path="role" value="Expert"></form:checkbox>
            Customer  <form:checkbox path="role" value="Customer"></form:checkbox>
            <p class="text-danger">${rate}</p>
        </div>
        <p class="text-danger">${error}</p>
        <form:button class="w-100 btn btn-lg btn-primary" type="submit">Search</form:button>
    </form:form>
</div>
<div>
    <table  class="table table-striped">
        <tr>
            <td>Name</td>
            <td>Last Name</td>
            <td>Email</td>
            <td>UserRole</td>
            <td>Registry Data</td>
        </tr>
        <c:forEach items="${usersList}" var="list">
            <tr>
                <td>${list.name}</td>
                <td>${list.lastName}</td>
                <td>${list.email}</td>
                <td>${list.role}</td>
                <td>${list.date}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>


