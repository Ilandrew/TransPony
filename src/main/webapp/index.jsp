<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 02.02.16
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>$Title$</title>
  </head>
  <body>
  $END$

  <form action="getcar.do" method="post">
    <button type="submit" name="button" value="get_car">Get car</button>
    <button type="submit" name="button" value="show_user">Show user</button>
    <button type="submit" name="button" value="login_str">Login</button>
  </form>
  <%-- <fmt:redirect url="WEB-INF/login.jsp"/>--%>
  </body>
</html>
