<%--
  Created by IntelliJ IDEA.
  User: Максим
  Date: 12.04.2016
  Time: 0:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <form action="route.do" method="post">
        <button type="submit" name="button" value="create_route">Create new route</button>
        <button type="submit" name="button" value="show_routes">Show all routes</button>
    </form>
    <br>
    <form action="checkpoint.do" method="post">
        <button type="submit" name="button" value="create_checkpoint">Create new checkpoint</button>
        <button type="submit" name="button" value="show_checkpoints">Show all checkpoints</button>
    </form>
</body>
</html>
