<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: buse1
  Date: 16/08/2019
  Time: 09:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h2>Home Page</h2>

    <h2><security:authentication property="principal.username" /> is at home </h2>

    <security:authorize access="hasRole('admin')" >
        <a href="${pageContext.request.contextPath}/list-users">
            List Users
        </a>
        <br><br>
    </security:authorize>


    <form:form action="${pageContext.request.contextPath}/logout" method="POST">

        <input type="submit" value="Logout" />

    </form:form>
</body>
</html>
