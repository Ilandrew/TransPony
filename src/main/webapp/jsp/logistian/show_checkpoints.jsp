<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Максим
  Date: 12.04.2016
  Time: 7:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkpoints page</title>
</head>
<body>
<table border="1">
    <tr>
        <td>Checkpoint Id</td>
        <td>Name</td>
        <td>X coordinate</td>
        <td>Y coordinate</td>
        <td>Checkpoint Type</td>
    </tr>
    <c:if test="${checkpoints != null}">
        <c:forEach var="checkpoint" items="${checkpoints}">
            <tr>
                <td>${checkpoint.getId()}</td>
                <td>${checkpoint.getName()}</td>
                <td>${checkpoint.getX()}</td>
                <td>${checkpoint.getY()}</td>
                <td>${checkpoint.getPointType()}</td>
                <td>
                    <form method="post" action="edit_checkpoint.do?checkpoint_id=${checkpoint.getId()}">
                        <button type="submit" name="change">Change</button>
                    </form>
                </td>
                <td>
                    <form method="post" action="show_routes.do?delete=${checkpoint.getId()}">
                        <button type="submit" name="delete">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
