<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Максим
  Date: 12.04.2016
  Time: 0:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Route Creation</title>
</head>
<body>
${message}<br>
<form method="post" action="create_route.do?func=add_point_to_route">
    <label for="checkpoint_list">Checkpoints</label>
    <select id="checkpoint_list" name="checkpoint_list">
        <c:forEach var="checkpoint" items="${checkpoints}">
            <option value="${checkpoint.getName()}">${checkpoint.getName()}</option>
        </c:forEach>
    </select>
    <button type="submit">Add checkpoint to route</button>
</form>
<br><br>
<c:if test="${new_route != null}">
    <form action="create_route.do?func=add_total_length" method="post">
        <label for="length">Total route length</label>
        <input id="length" type="text" name="length_value" value="${new_route.getTotalLength()}"/>
        <button type="submit">Set total length</button>
    </form>
    <form method="post" action="create_route.do?func=delete_point_from_route">
        <table border="1">
            <tr>
                <td>Checkpoint Name</td>
                <td>Checkpoint Type</td>
            </tr>
            <c:forEach var="point" items="${new_route.getPoints()}">
                <tr>
                    <td>${point.getName()}</td>
                    <td>${point.getPointType()}</td>
                    <td>
                        <button type="submit" name="delete_button" value="${point.getName()}">Remove checkpoint</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <a href="save_route.do"><button type="button">Save route</button></a>
</c:if>
</body>
</html>
