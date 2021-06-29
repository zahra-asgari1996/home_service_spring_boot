<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><span class="fs-4">Show Rates</span></svg>
    </a>
    <a href="#" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
        <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><span class="fs-4">  <span class="fs-4">Average rate :  ${rate}</span></svg>
    </a>
</header>
<form:form action="/comment/showRate" method="get">
    <table class="table table-striped">
        <tr>
            <td>Rate</td>
            <td>Comment</td>
        </tr>
        <c:forEach items="${commentList}" var="list">
            <tr>
                <td>
                        ${list.rate}
                </td>
                <td>
                        ${list.comment}
                </td>
            </tr>
        </c:forEach>
    </table>
</form:form>
<footer class="footer mt-auto py-3 bg-light">
    <div class="container">
        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="/" class="nav-link px-2 link-secondary">Home</a></li>
            <li><a href="/expert/expertHomePage" class="nav-link px-2 link-secondary">Expert Home Page</a></li>
            <li><a href="/userLogout" class="nav-link px-2 link-secondary">Logout</a></li>
        </ul>
    </div>
</footer>
</body>
</html>
