<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  user: Максим
  Date: 12.04.2016
  Time: 4:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Routes page</title>
</head>
<body>
<table border="1">
    <tr>
        <td>Route Id</td>
        <td>Points in route</td>
        <td>Starting point</td>
        <td>Finishing point</td>
        <td>Total length</td>
        <td>Is used</td>
        <td>Creator</td>
    </tr>
    <c:if test="${routes != null}">
        <c:forEach var="route" items="${routes}">
            <tr>
                <td>${route.getId()}</td>
                <td>${route.getName()}</td>
                <td>${route.getPoints().get(0).getName()}</td>
                <td>${route.getPoints().get(route.getPoints().size() - 1).getName()}</td>
                <td>${route.getTotalLength()}</td>
                <td>${is_used}</td>
                <td>${route.getOwner().getInitials()}</td>
                <td>
                    <form method="post" action="edit_route.do?route_id=${route.getId()}">
                        <button type="submit" name="change">Change</button>
                    </form>
                </td>
                <td>
                    <form method="post" action="show_routes.do?delete=${route.getId()}">
                        <button type="submit" name="delete">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
