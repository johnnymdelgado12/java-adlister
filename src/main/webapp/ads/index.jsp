<%--
  Created by IntelliJ IDEA.
  User: johnnydelgado
  Date: 4/22/20
  Time: 4:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Here are all the Ads:</h1>

<c:forEach var="ad" items="${ads}">
    <div class="ad">
        <h2><c:out value="${ad.title}"/></h2>
        <p>Description: <c:out value="${ad.description}"/> </p>
    </div>
</c:forEach>
</body>
</html>
