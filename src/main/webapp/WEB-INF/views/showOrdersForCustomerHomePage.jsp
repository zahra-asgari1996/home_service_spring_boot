<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 6/12/2021
  Time: 11:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Orders</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
    <a href="#" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
        <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><span class="fs-4">Show Orders</span></svg>
    </a>

</header>
<form:form action="/customer/showSuggestions" method="get">
    <table class="table table-striped">
        <thead>
        <tr>
            <td>Id</td>
            <td>Job description</td>
            <td>Proposed price</td>
            <td>Situation</td>
            <td>Date Of Work</td>
            <td>Date Of Create Order</td>
            <td colspan="2">Address</td>
            <td colspan="2">Expert Info</td>
            <td colspan="2">Customer Info</td>
            <td colspan="2">Add Comment</td>
            <td colspan="2">Account Balance Payment</td>
            <td colspan="2">Online Payment</td>
            <td colspan="2">Show Offers</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ordersList}" var="list">
            <tr>
                <td rowspan="4">${list.id}</td>
                <td rowspan="4">${list.jobDescription}</td>
                <td rowspan="4">${list.proposedPrice}</td>
                <td rowspan="4">${list.situation}</td>
                <td rowspan="4">${list.dateOfWork}</td>
                <td rowspan="4">${list.dateOfOrderRegistration}</td>
                <td>City</td>
                <td>${list.address.city}</td>
                <td>Id</td>
                <td>${list.expert.id}</td>
                <td>Id</td>
                <td>${list.customer.id}</td>
                <td rowspan="4">
                    <c:if test="${list.situation eq 'FINISHED'}">
                        <a onclick="sendOffer(${list.id});" href="#" id="link">Add a Comment</a>
                    </c:if>
                </td>
                <td rowspan="4">
                    <c:if test="${list.situation eq 'DONE'}">
                        <a onclick="paymentFromAccountCredit(${list.id});" href="#" id="paymentLink">Pay From Account
                            Balance</a>
                    </c:if>
                </td>
                <td rowspan="4">
                    <c:if test="${list.situation eq 'DONE'}">
                        <a onclick="onlinePayment(${list.id});" href="#" id="onlinePayment">Online Payment</a>

                    </c:if>
                </td>
                <td rowspan="4">
                    <c:if test="${list.situation eq 'Waiting_for_expert_selection'}">
                        <a onclick="showOffers(${list.id});" href="#" id="showOffers">Show Offers</a>

                    </c:if>
                </td>
            </tr>
            <tr>
                <td>Street</td>
                <td>${list.address.street}</td>
                <td>Name</td>
                <td>${list.expert.name}</td>
                <td>Name</td>
                <td>${list.customer.name}</td>
            </tr>
            <tr>
                <td>Alley</td>
                <td>${list.address.alley}</td>
                <td>Last Name</td>
                <td>${list.expert.lastName}</td>
                <td>Last Name</td>
                <td>${list.customer.lastName}</td>
            </tr>
            <tr>
                <td>Plaque</td>
                <td>${list.address.plaque}</td>
                <td>Email</td>
                <td>${list.expert.email}</td>
                <td>Email</td>
                <td>${list.customer.email}</td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
</form:form>
<footer class="footer mt-auto py-3 bg-light">
    <div class="container">
        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="/" class="nav-link px-2 link-secondary">Home</a></li>
            <li><a href="/customer/customerHomePage" class="nav-link px-2 link-secondary">Customer Home Page</a></li>
            <li><a href="/userLogout" class="nav-link px-2 link-secondary">log out</a></li>
        </ul>
    </div>
</footer>
<script>
    function sendOffer(id) {
        console.log("hello" + id)
        window.location.href = "http://localhost:8080/comment/addComment/" + id;
    }

    function paymentFromAccountCredit(id) {
        window.location.href = "http://localhost:8080/customer/paymentFromAccountCredit/" + id;
    }

    function onlinePayment(id) {
        window.location.href = "http://localhost:8080/customer/onlinePayment/" + id;
    }

    function showOffers(id) {
        window.location.href = "http://localhost:8080/customer/showOffers/" + id;
    }
</script>
</body>
</html>
