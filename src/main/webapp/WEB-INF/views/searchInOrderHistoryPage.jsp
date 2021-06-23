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
    <form   name="myForm">
        <h1 class="h3 mb-3 fw-normal">Search Users</h1>
        <select id="situation">
            <option value="NONE" label="select"  />
            <c:forEach items="${situationList}" var="list">
                <option value="${list}"   name="situation" >${list}</option>
            </c:forEach>
        </select>
        <select id="serviceName"  >
            <option  value="NONE" label="select service name"  />
            <c:forEach items="${serviceList}" var="list">
                <option value="${list.name}"   name="serviceName">${list.name}</option>
            </c:forEach>
        </select>
        <select  id="subServiceName">
            <option  value="NONE" label="select service name" />
            <c:forEach items="${subServiceList}" var="list">
                <option value="${list.name}"   name="subServiceName">${list.name}</option>
            </c:forEach>
        </select>

        <div class="form-floating">
            <input type="date" class="form-control"  id="startDate" name="startDate"/>
            <label for="startDate" >Start Date</label>
<%--            <p class="text-danger">${startDate}</p>--%>
        </div>
        <div class="form-floating">
            <input type="date" class="form-control" id="endDate" name="endDate"/>
            <label for="endDate" >End Date</label>
<%--            <p class="text-danger">${endDate}</p>--%>
        </div>


        <div class="form-floating">

        </div>
        <p class="text-danger">${error}</p>
    </form>
    <button class="w-100 btn btn-lg btn-primary" type="submit" onclick="searchOrders()">Search</button>
</div>
<div>
    <table class="table table-striped" id="showOrders">
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
        </tr>
        </thead>
    </table>
</div>
<script>
    function searchOrders(){
        console.log("successssss")
        var situation=document.getElementById("situation").value;
        var startDate=document.getElementById("startDate").value;
        var endDate=document.getElementById("endDate").value;
        var serviceName=document.getElementById("serviceName").value;
        var subServiceName=document.getElementById("subServiceName").value;
        var json='{"situation":"'+ situation+'", "serviceName":"'+ serviceName+
            '", "subServiceName":"'+ subServiceName+'", "startDate": "'+startDate+'", "endDate": "'+endDate+'"}';
            console.log(json)
        $.ajax({
            method:"POST",
            contentType:"application/json",
            data:json,
            dataType: 'json',
            url:"/managerRestController/filterOrders",
            success:function (result){
                for(const entry of result) {
                    console.log(entry.jobDescription)
                }
            }
        });
    }
</script>
</body>
</html>

