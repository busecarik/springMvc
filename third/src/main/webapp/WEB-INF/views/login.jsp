<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: buse1
  Date: 16/08/2019
  Time: 08:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
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
<br>
    <h2>Login Page</h2>

    <form:form action="${pageContext.request.contextPath}/authenticate" method="post">
        <table>
            <tr>
                <td>Name</td>
                <td><input type="text" name="username" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" /></td>
            </tr>
        </table>

        <br>

        <input type="submit" value="Login" />
    </form:form>
</body>
</html>
