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
    <form:form modelAttribute="searchUser" >
        <h1 class="h3 mb-3 fw-normal">Search Users</h1>

        <div class="form-floating">
            <form:input type="number" class="form-control" id="floatingInput" path="maxPrice" name="maxPrice"/>
            <form:label for="floatingInput" path="maxPrice">Max Price</form:label>
            <p class="text-danger">${maxPrice}</p>
        </div>
        <div class="form-floating">
            <form:input type="number" class="form-control" id="floatingInput" path="minPrice" name="minPrice"/>
            <form:label for="floatingInput" path="minPrice">Min Price</form:label>
            <p class="text-danger">${minPrice}</p>
        </div>
        <div class="form-floating">
            <form:input type="date" class="form-control" id="floatingPassword" path="startDate" name="startDate"/>
            <form:label for="floatingPassword" path="startDate">Start Date</form:label>
            <p class="text-danger">${startDate}</p>
        </div>
        <div class="form-floating">
            <form:input type="date" class="form-control" id="floatingPassword" path="endDate" name="endDate"/>
            <form:label for="floatingPassword" path="endDate">End Date</form:label>
            <p class="text-danger">${endDate}</p>
        </div>
        <form:select path="situation">
            <form:option value="NONE" label="Select"/>
            <form:options items="${situationList}"/>
        </form:select>

        <div class="form-floating">

        </div>
        <p class="text-danger">${error}</p>
        <form:button class="w-100 btn btn-lg btn-primary" type="submit">Search</form:button>
    </form:form>
</div>
<div>
    <table class="table table-striped">
        <tr>
            <td>Name</td>
            <td>Last Name</td>
            <td>Email</td>
            <td>UserRole</td>
            <td>Registry Data</td>
            <td>Confirm Users</td>
            <td>Search a user orders</td>
        </tr>
        <c:forEach items="${usersList}" var="list">
            <tr>
                <td>${list.name}</td>
                <td>${list.lastName}</td>
                <td>${list.email}</td>
                <td>${list.role}</td>
                <td>${list.date}</td>
                <td>
                    <c:if test="${list.role eq 'Expert' && list.situation eq 'Pending_approval'}">
                        <a onclick="confirmUser(${list.id});" href="#" id="link">Confirm User</a>
                    </c:if>
                </td>
                <td>
                    <a onclick="searchUser(${list.id});" href="#" id="searchUserOrders">Search orders for user</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<script>
    function confirmUser(id) {
        console.log("hello" + id)
        window.location.href = "http://localhost:8080/user/confirmUser/" + id;
    }

    function searchUser(id) {
        console.log("hello" + id)
        window.location.href = "http://localhost:8080/user/searchUser/" + id;
    }

</script>
</body>
</html>


