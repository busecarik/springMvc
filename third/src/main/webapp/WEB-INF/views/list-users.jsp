<%--
  Created by IntelliJ IDEA.
  User: buse1
  Date: 16/08/2019
  Time: 09:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users List</title>
</head>

<style>
    table, th, td {
        border: 1px solid black;
    }
    th, td {
        padding: 8px;
    }
</style>

<body>

    <h2>List of Users</h2>

    <table>
        <thead>
            <tr>
                <th>Username</th>
                <th>Email</th>
                <th>Birthday</th>
                <th>Sex</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.birthday}</td>
                <td><c:out value="${user.sex == 1 ? 'female' : 'male'}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

        <br>

        <input type="submit" value="Print Table" />

    <br><br>

    <a href="${pageContext.request.contextPath}/print">Back to Home</a>

</body>
</html>
