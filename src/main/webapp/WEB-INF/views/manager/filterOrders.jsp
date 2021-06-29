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
    <title>filter</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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
    <form name="myForm">
        <h1 class="h3 mb-3 fw-normal">Filter Orders</h1>
        <select id="situations">
            <option value="NONE" label="select"/>
            <c:forEach items="${situationList}" var="list">
                <option value="${list}" name="situations">${list}</option>
            </c:forEach>
        </select>
        <select id="serviceName">
            <option value="NONE" label="select service name"/>
            <c:forEach items="${serviceList}" var="list">
                <option value="${list.name}" name="serviceName">${list.name}</option>
            </c:forEach>
        </select>
        <select id="subServiceName">
            <option value="NONE" label="select service name"/>
            <c:forEach items="${subServiceList}" var="list">
                <option value="${list.name}" name="subServiceName">${list.name}</option>
            </c:forEach>
        </select>

        <div class="form-floating">
            <input type="date" class="form-control" id="startDate" name="startDate"/>
            <label for="startDate">Start Date</label>
        </div>
        <div class="form-floating">
            <input type="date" class="form-control" id="endDate" name="endDate"/>
            <label for="endDate">End Date</label>
        </div>


        <div class="form-floating">

        </div>
        <div>
            <c:if test="${errorAlert ne null}">
                <blockquote class="blockquote">
                    <p style="  margin: auto;width: 100%;border: 3px solid #be081d;padding: 10px;"
                       class="mb-0">${errorAlert}</p>
                </blockquote>
            </c:if>
        </div>
    </form>
    <button class="w-100 btn btn-lg btn-primary" type="submit" onclick="searchOrders()">Search</button>
</div>
<div>
    <table class="table table-striped" id="showOrders">
        <thead>
        <tr>
            <td>Job description</td>
            <td>Proposed price</td>
            <td>Situation</td>
            <td>Date Of Work</td>
            <td>Date Of Create Order</td>
            <td colspan="2">Address</td>
            <td colspan="2">Expert Info</td>
            <td colspan="2">Customer Info</td>
        </tr>
        </thead>
    </table>
</div>
<footer class="footer mt-auto py-3 bg-light">
    <div class="container">
        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="/" class="nav-link px-2 link-secondary">Home</a></li>
            <li><a href="/managerPage/managerHomePage" class="nav-link px-2 link-secondary">ManagerHomePage Home Page</a></li>
            <li><a href="/managerLogout" class="nav-link px-2 link-secondary">Logout</a></li>
        </ul>
    </div>
</footer>
<script>
    function searchOrders() {
        $("#showOrders tbody tr").remove();
        console.log("successssss")
        var situation = document.getElementById("situations").value;
        var startDate = document.getElementById("startDate").value;
        var endDate = document.getElementById("endDate").value;
        var serviceName = document.getElementById("serviceName").value;
        var subServiceName = document.getElementById("subServiceName").value;
        var json = '{"situation":"' + situation + '", "serviceName":"' + serviceName +
            '", "subServiceName":"' + subServiceName + '", "startDate": "' + startDate + '", "endDate": "' + endDate + '"}';
        console.log(json)
        $.ajax({
            method: "POST",
            contentType: "application/json",
            data: json,
            dataType: 'json',
            url: "/managerRestController/filterOrders",
            success: function (result) {
                console.log(result);
                var row = "";
                for (const entry of result) {
                    console.log(entry.situation)
                    row += "<tr><td rowspan='4'>" + entry.jobDescription + "</td>" +
                        "<td rowspan='4'>" + entry.proposedPrice + "</td>" +
                        "<td rowspan='4'>" + entry.situation + "</td>" +
                        "<td rowspan='4'>" + entry.dateOfWork + "</td>" +
                        "<td rowspan='4'>" + entry.dateOfOrderRegistration + "</td>" +
                        "<td> City </td>" +
                        "<td>" + entry.address.city + "</td>" +
                        "<td> Name </td>" +
                        "<td>" + (!entry.expert?"":entry.expert.name )+ "</td>" +
                        "<td>Name </td>" +
                        "<td>" + entry.customer.name + "</td></tr>" +
                        "<tr><td>Street</td>" +
                        "<td>" + entry.address.street + "</td>" +
                        "<td>Last Name </td>" +
                        "<td>" +(!entry.expert?"": entry.expert.lastName) + "</td>" +
                        "<td> Last Name</td>" +
                        "<td>" + entry.customer.lastName + "</td></tr>" +
                        "<tr><td>alley</td>" +
                        "<td>" + entry.address.alley + "</td>" +
                        "<td>Email </td>" +
                        "<td>" + (!entry.expert?"":entry.expert.email )+ "</td>" +
                        "<td> Email </td>" +
                        "<td>" + entry.customer.email + "</td></tr>" +
                        "<tr><td>Plaque</td>" +
                        "<td>" + entry.address.plaque + "</td>" +
                        "<td>Situation</td>" +
                        "<td>" + (!entry.expert?"":entry.expert.userSituation)+ "</td>" +
                        "<td>Situation</td>" +
                        "<td>" + entry.customer.userSituation + "</td></tr>";

                }
                $("#showOrders").append("<tbody>"+row+"</tbody>");
            }
        });
    }
</script>

</body>
</html>


