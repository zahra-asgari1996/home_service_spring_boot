<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.83.1">
    <title>add_comment</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

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
    <form:form modelAttribute="comment" method="post" action="/comment/addComment">
        <h1 class="h3 mb-3 fw-normal">Add New Comment</h1>

        <div class="form-floating">
            <form:input type="number" class="form-control" id="floatingInput" path="rate" name="rate"/>
            <form:label for="floatingInput" path="rate">Rate</form:label>
            <p class="text-danger">${rate}</p>
        </div>
        <div class="form-floating">
            <form:input type="text" class="form-control" id="floatingPassword" path="comment" name="comment"/>
            <form:label for="floatingPassword" path="comment">Comment</form:label>
<%--            <p class="text-danger">${comment}</p>--%>
        </div>
        <p class="text-danger">${error}</p>
        <form:button class="w-100 btn btn-lg btn-primary" type="submit">Add Comment</form:button>
    </form:form>
</main>
<footer class="footer mt-auto py-3 bg-light">
    <div class="container">
        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="/" class="nav-link px-2 link-secondary">Home</a></li>
            <li><a href="/customer/customerHomePage" class="nav-link px-2 link-secondary">Customer Home Page</a></li>
            <li><a href="/userLogout" class="nav-link px-2 link-secondary">log out</a></li>
        </ul>
    </div>
</footer>
</body>
</html>


