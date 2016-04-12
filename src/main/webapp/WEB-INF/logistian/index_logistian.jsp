<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <table>
            <tr>
                <td>Route</td>
            </tr>
            <c:forEach var="route" items="${routes}">
                <tr>
                    <td>${route.getName()}</td>
                    <td><button type="submit" name="button" value="${route.getId()}">Change</button></td>
                    <td><button type="submit" name="button" value="${route.getId()}">Delete</button></td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <br>
    <form action="checkpoint.do" method="post">
        <button type="submit" name="button" value="create_checkpoint">Create new checkpoint</button>
        <table>
            <tr>
                <td>Points</td>
            </tr>
            <c:forEach var="checkpoint" items="${checkpoint}">
                <tr>
                    <td>${checkpoint.getName()}</td>
                    <td><button type="submit" name="button" value="${checkpoint.getId()}">Change</button></td>
                    <td><button type="submit" name="button" value="${checkpoint.getId()}">Delete</button></td>
                </tr>
            </c:forEach>
        </table>
    </form>
</body>
</html>
