<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Максим
  Date: 15.04.2016
  Time: 1:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Route edit</title>
</head>
<body>
${message}<br>
<%-- Если этот путь используется, то есть count != 0, то редактировать его нельзя;
это собственно в сообщение и выводится--%>
<c:if test="${route.getCount() != 0}">
    <label for="checkpoint_list">Checkpoints</label>
    <select id="checkpoint_list" name="checkpoint_list">
        <c:forEach var="checkpoint" items="${checkpoints}">
            <option value="${checkpoint.getName()}">${checkpoint.getName()}</option>
        </c:forEach>
    </select>
    <a href="edit_route.do?func=add_point_to_route">
        <button type="button">Add checkpoint to route</button>
    </a>
    <br><br>

    <label for="length">Total route length</label>
    <input id="length" type="text" name="length_value" value="${route.getTotalLength()}"/>
    <a href="edit_route.do?func=add_total_length">
        <button type="button">Change total length</button>
    </a>
    <br><br>

    <table border="1">
        <tr>
            <td>Checkpoint Name</td>
            <td>Checkpoint Type</td>
        </tr>
        <c:forEach var="point" items="${route.getPoints()}">
            <tr>
                <td>${point.getName()}</td>
                <td>${point.getPointType()}</td>
                <td>
                    <a href="edit_route.do?func=delete_point_from_route">
                        <button type="button" name="delete_button" value="${point.getName()}">Remove checkpoint</button>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="save_route_changes.do"><button type="button">Save changes to route</button></a>
</c:if>
</body>
</html>
