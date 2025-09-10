<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>\</title>
</head>
<body>
<%@include file="header.jsp"%>
<h1>Пицца в пиццериях:</h1>
<ul>
    <c:if test="{not empty requestScope.menu}">
        <c:forEach var="menu" items="{requuestScope.menu">
            <li>${fn:toLowercase(menu.pizzaName())}</li>
        </c:forEach>
    </c:if>
</ul>


</ul>
</body>
</html>
