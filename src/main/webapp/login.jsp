<%--
  Created by IntelliJ IDEA.
  User: johnnydelgado
  Date: 4/21/20
  Time: 11:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getMethod().equalsIgnoreCase("post")){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("password")){
            response.sendRedirect("/profile.jsp");
        }
    }
%>
<html>
<head>
    <title>Login Form</title>
</head>
<body>
<%@include file="partials/navbar.jsp"%>
<form method="POST" action= ${pageContext.request.contextPath}/login.jsp>
    <label for="username">Username</label>
    <input id="username" name="username" type="text">
    <br>
    <label for="password">Password</label>
    <input id="password" name="password" type="password">
    <br>
    <input type="submit">
</form>
</body>
</html>
