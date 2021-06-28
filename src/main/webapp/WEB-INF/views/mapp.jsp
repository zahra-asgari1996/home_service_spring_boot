
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/static/dist/css/mapp.min.css" />">
    <link rel="stylesheet" href="<c:url value="/static/dist/css/fa/style.css"/>" data-locale="true">
    <link rel="stylesheet" href="<c:url value="/static/dist/app.css"/>">
</head>

<body>
<form action="/map/address">
    <div id="app"></div>
    <input type="hidden" name="lat" id="lat">
    <input type="hidden" name="lng" id="lng">
    <input type="submit" name="submit" value="submit">
</form>

<%--<script type="text/javascript" src="/static/dist/js/jquery-3.2.1.min.js"></script>--%>
<script type="text/javascript" src="<c:url  value="/static/dist/js/jquery-3.2.1.min.js"/>"></script>
<%--<script type="text/javascript" src="/static/dist/js/mapp.env.js"></script>--%>
<script type="text/javascript" src="<c:url value="/static/dist/js/mapp.env.js" />"></script>
<%--<script type="text/javascript" src="/static/dist/js/mapp.min.js"></script>--%>
<script type="text/javascript" src="<c:url value="/static/dist/js/mapp.min.js"/>"></script>
<%--<script type="text/javascript" src="/dist/app.js"></script>--%>
<script type="text/javascript" src="<c:url  value="/static/dist/app.js"/>"></script>
</body>
</html>
