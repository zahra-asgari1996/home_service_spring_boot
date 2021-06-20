<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 6/6/2021
  Time: 9:31 PM
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
        <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><span class="fs-4">Show Orders</span></svg>
    </a>
</header>
<form action="/expert/showOrders" method="get" id="serviceForm">
    <table class="table table-striped">
        <tr>
            <td>Id</td>
            <td>Date Of Work</td>
            <td>Situation</td>
            <td colspan="2">Sub Service</td>
            <td colspan="2">Customer Info</td>
            <td colspan="2">Address</td>
            <td colspan="2">Send Offer</td>
<%--            <td colspan="2">end of work button</td>--%>

        </tr>


        <c:forEach items="${ordersList}" var="list">
            <tr>
                <td rowspan="4">${list.id}</td>
                <td rowspan="4">${list.dateOfWork}</td>
                <td rowspan="4">${list.situation}</td>
                <td rowspan="2">Name</td>
                <td rowspan="2">${list.subService.name}</td>
                <td rowspan="2">Name</td>
                <td rowspan="2">${list.customer.name}</td>
                <td>City</td>
                <td>${list.address.city}</td>
                <c:if test="${list.situation eq 'Waiting_for_expert_suggestions'}">
                    <td rowspan="4">
                        <a onclick="sendOffer(${list.id});" href="#" id="link">click</a>
                    </td>

                </c:if>
            </tr>
            <tr>
                <td>Street</td>
                <td>${list.address.street}</td>

            </tr>
            <tr>
                <td rowspan="2">Base Price</td>
                <td rowspan="2">${list.subService.basePrice}</td>
                <td rowspan="2">Last Name</td>
                <td rowspan="2">${list.customer.lastName}</td>
                <td>Alley</td>
                <td>${list.address.alley}</td>
            </tr>
            <tr>
                <td>Plaque</td>
                <td>${list.address.plaque}</td>

            </tr>
        </c:forEach>

    </table>
</form>
<footer class="footer mt-auto py-3 bg-light">
    <div class="container">
        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="/expert" class="nav-link px-2 link-secondary">Expert Home Page</a></li>
        </ul>
    </div>
</footer>
<script>
    function sendOffer(id) {
        // document.getElementById("link").href="/offer/sendOffer/ "+id;
        //document.getElementById("link").setAttribute("onclick", "location.href='http://localhost:8739'");
        console.log("hello" + id)
        window.location.href = "http://localhost:8739/offer/sendOffer/" + id;
    }

    // function endOfWork(id) {
    //     // document.getElementById("link").href="/offer/sendOffer/ "+id;
    //     //document.getElementById("link").setAttribute("onclick", "location.href='http://localhost:8739'");
    //     console.log("hello" + id)
    //     window.location.href = "http://localhost:8739/order/endOfWork/" + id;
    // }
</script>
</body>
</html>
