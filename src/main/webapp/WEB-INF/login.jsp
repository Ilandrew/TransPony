<%--
  Created by IntelliJ IDEA.
  User: vadim
  Date: 11.04.16
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="login.do" method="post">
  <label for="login">Login</label><br>
  <input id="login" type="text" name="login" size="30"><br><br>
  <label for="password">Password</label><br>
  <input type="password" id="password" name="password" size="30"><br><br>
  <span style="color:red">${errorMessage}</span><br>
  <button type="submit" name="button" value="login">Login</button>
</form>
</body>
</html>
