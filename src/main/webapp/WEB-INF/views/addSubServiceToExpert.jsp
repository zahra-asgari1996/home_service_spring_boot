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
    <title>Add Sub Service To Expert List</title>

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
    <form:form modelAttribute="addSubServiceToExpert" action="/managerPage/addSubServiceToExert" method="post">
        <h1 class="h3 mb-3 fw-normal">Add Sub Service To Expert List</h1>
        <form:select path="expertId">
            <form:option disabled="true"  value="None">---Select Expert Id</form:option>
            <c:forEach items="${expertList}" var="list">
                <form:option value="${list.id}" >${list.id},${list.name} ${list.lastName}</form:option>
            </c:forEach>
        </form:select>
        <p class="text-danger">${expertId}</p>
        <form:select path="subServiceId">
            <form:option disabled="true"  value="None">---Select Sub Service Id</form:option>
            <c:forEach items="${subServiceList}" var="list">
                <form:option value="${list.id}" >${list.id},${list.name}</form:option>
            </c:forEach>
        </form:select>
        <p class="text-danger">${subServiceId}</p>

        <p class="text-danger">${error}</p>
        <form:button class="w-100 btn btn-lg btn-primary" type="submit">Add</form:button>
    </form:form>
</div>
</body>
</html>


