<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: buse1
  Date: 16/08/2019
  Time: 09:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
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
    <h2>Registration Page</h2>

    <form:form action="${pageContext.request.contextPath}/postRegistration" modelAttribute="userDto" method="post">
        <table>
            <tr>
                <td>Username</td>
                <form:errors path="username" />
                <td><input type="text" name="username" /></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="email" name="email" /></td>
            </tr>
            <tr>
                <td>Birthday</td>
                <td><input type="date" name="birthday" /></td>
            </tr>
            <tr>
                <td>Sex</td>
                <td>
                    <input type="radio" name="sex" value="female">Female
                    <input type="radio" name="sex" value="male">Male
                </td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" /></td>
            </tr>
            <tr>
                <td>Password Confirmation</td>
                <td><input type="password" name="matchingPassword"></td>
            </tr>

            <c:if test="${usernameError != null}">
                <tr>
                    <td style="color: red; text-align: center">${usernameError}</td>
                </tr>
            </c:if>
            <c:if test="${emailError != null}">
                <tr>
                    <td style="color: red; text-align: center">${emailError}</td>
                </tr>
            </c:if>
            <c:if test="${passwordError != null}">
                <tr>
                    <td style="color: red; text-align: center">${passwordError}</td>
                </tr>
            </c:if>
        </table>
        <br>
        <input style="alignment: center" type="submit" value="Sign up" />


    </form:form>

</body>
</html>
