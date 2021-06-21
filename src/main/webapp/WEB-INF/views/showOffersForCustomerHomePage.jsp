<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 6/14/2021
  Time: 10:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
    <a href="#" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
        <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><span class="fs-4">Show Offers</span></svg>
    </a>
</header>
<form:form method="get" action="/customer/showOffers">
    <table class="table table-striped">
        <tr>
            <td>id</td>
            <td>Duration Of Work</td>
            <td>Offer Price</td>
            <td>Start Time</td>
            <td colspan="2">Expert Info</td>
            <td colspan="2">Order Info</td>
            <td>Select Offer</td>
        </tr>
        <c:forEach items="${offersList}" var="list">
            <tr>
                <td rowspan="3" >${list.id}</td>
                <td rowspan="3" >${list.durationOfWork}</td>
                <td rowspan="3" >${list.offerPrice}</td>
                <td rowspan="3" >${list.startTime}</td>
                <td>Name</td>
                <td >${list.expert.name}</td>
                <td>Id</td>
                <td >${list.orders.id}</td>
                <td>
                    <a onclick="selectOffer(${list.id});" href="#" id="link">select offer</a>
                </td>

            </tr>
            <tr>
                <td>Last Name</td>
                <td>${list.expert.lastName}</td>
                <td>Job Description</td>
                <td>${list.orders.jobDescription}</td>
            </tr>
            <tr>
                <td>Email</td>
                <td>${list.expert.email}</td>
                <td>Date Of Work</td>
                <td>${list.orders.dateOfWork}</td>
            </tr>
        </c:forEach>

    </table>
</form:form>
<footer class="footer mt-auto py-3 bg-light">
    <div class="container">
        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="/customer" class="nav-link px-2 link-secondary">Customer Home Page</a></li>
        </ul>
    </div>
</footer>
<script>
    function selectOffer(id) {
        console.log("hello" + id)
        window.location.href = "http://localhost:8080/offer/selectOffer/" + id;
    }
</script>
</body>
</html>
