<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 6/2/2021
  Time: 10:18 PM
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
        <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><span class="fs-4">Select Field For Expert</span></svg>
    </a>
</header>
<form:form action="/expert/selectField" method="get">
    <table class="table table-striped">
        <tr>
            <td>Id</td>
            <td>Name</td>
            <td>Base Price</td>
            <td>Service Name</td>
            <td>Select Field</td>
        </tr>
        <c:forEach items="${listOfFields}" var="list">
            <tr>
                <td>${list.id}</td>
                <td>${list.name}</td>
                <td>${list.basePrice}</td>
                <td>${list.service.name}</td>
                <td>
                    <a onclick="selectField(${list.id});" href="#" id="link">select field</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</form:form>
<footer class="footer mt-auto py-3 bg-light">
    <div class="container">
        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="/expert" class="nav-link px-2 link-secondary">Expert Home Page</a></li>
        </ul>
    </div>
</footer>
<script>
    function selectField(id) {
        console.log("hello" + id)
        window.location.href = "http://localhost:8080/expert/selectField/" + id;
    }
</script>
</body>
</html>
