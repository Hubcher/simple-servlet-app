<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <span> CONTENT РУССКИЙ</span>
    <p>Size: ${requestScope.pizzerias.size()}</p>
    <p>description: ${requestScope.pizzerias.get(0).description()}</p>
    <p>id: ${requestScope.pizzerias[1].id()}</p>
    <p>JSESSIONID: ${cookie.get("JSESSIONID")}</p>
    <p>PARAM id : ${param.id}</p>
    <p>HEADER id : ${header["cookie"]}</p>
    <p>NOT EMPTY : ${not empty pizzerias}</p>
</div>
</body>
</html>
