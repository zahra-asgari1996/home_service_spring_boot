<!doctype html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <h1 class="h3 mb-3 fw-normal">Add New Service</h1>
    <form action="/subService/getSubService" method="get" id="serviceForm">
        <select name="service" onchange="submitForm()">
            <option value="NONE" label="${selectedService}">${selectedService}</option>
            <c:forEach items="${serviceList}" var="list">
                <c:if test="${list.name ne selectedService}">
                    <option value="${list.name}">${list.name}</option>
                </c:if>
            </c:forEach>
        </select>
    </form>
    <form:form modelAttribute="newOrder" method="post" action="/order/createOrder">
        <div class="form-floating">
            <form:input type="number" class="form-control" id="floatingPassword" path="proposedPrice" name="proposedPrice"/>
            <form:label for="floatingPassword" path="proposedPrice">Proposed Price</form:label>
            <p class="text-danger">${proposedPrice}</p>
        </div>

        <div class="form-floating">
            <form:input type="text" class="form-control" id="floatingPassword" path="jobDescription" name="jobDescription"/>
            <form:label for="floatingPassword" path="jobDescription">Job Description</form:label>
            <p class="text-danger">${jobDescription}</p>
        </div>
        <div class="form-floating">
            <form:input type="date" class="form-control" id="floatingPassword" path="dateOfWork" name="dateOfWork"/>
            <form:label for="floatingPassword" path="dateOfWork">Date Of Work</form:label>
            <p class="text-danger">${dateOfWork}</p>
        </div>
        <div class="form-floating">
            <form:input type="text" class="form-control" id="floatingPassword" path="address.city" name="city"/>
            <form:label for="floatingPassword" path="address.city">City</form:label>
            <p class="text-danger">${address.city}</p>
        </div>
        <div class="form-floating">
            <form:input type="text" class="form-control" id="floatingPassword" path="address.street" name="street"/>
            <form:label for="floatingPassword" path="address.street">Street</form:label>
            <p class="text-danger">${address.street}</p>
        </div>
        <div class="form-floating">
            <form:input type="text" class="form-control" id="floatingPassword" path="address.alley" name="alley"/>
            <form:label for="floatingPassword" path="address.alley">Alley</form:label>
            <p class="text-danger">${address.alley}</p>
        </div>
        <div class="form-floating">
            <form:input type="text" class="form-control" id="floatingPassword" path="address.plaque" name="plaque"/>
            <form:label for="floatingPassword" path="address.plaque">Plaque</form:label>
            <p class="text-danger">${address.plaque}</p>
        </div>
        <form:select path="subService.name">
            <form:option value="NONE" label="Select"/>
            <form:options items="${subServiceList}"/>
        </form:select>

        <p class="text-danger">${error}</p>
        <form:button class="w-100 btn btn-lg btn-primary" type="submit">Create</form:button>
    </form:form>
</main>
<script>
    function submitForm() {
        console.log("success")
        document.getElementById("serviceForm").submit();
        console.log("error")
    }
</script>
</body>
</html>


