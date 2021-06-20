<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

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


    <form:input path="address.city" placeHolder="city name"></form:input>
    <form:input path="address.street" placeHolder="street name"></form:input>
    <form:input path="address.alley" placeHolder="alley name"></form:input>
    <form:input path="address.plaque" placeHolder="plaque"></form:input>
    <form:select path="subService.name">
        <form:option value="NONE" label="Select"/>
        <form:options items="${subServiceList}"/>
    </form:select>
    <form:button value="create">create</form:button>
</form:form>


<script>
    function submitForm() {
        console.log("success")
        document.getElementById("serviceForm").submit();
        console.log("error")

    }
</script>
</body>
</html>
