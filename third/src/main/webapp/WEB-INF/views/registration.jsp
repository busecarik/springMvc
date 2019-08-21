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

    <form action="/postRegistration" method="post">
        <table>
            <tr>
                <td>Username</td>
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
                <td><input type="password" name="password_confirm"></td>
            </tr>
        </table>
        <br>
        <input style="alignment: center" type="submit" value="Sign up" />
    </form>

</body>
</html>
