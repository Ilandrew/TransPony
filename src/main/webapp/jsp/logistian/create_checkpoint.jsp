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
    <title>Checkpoint creation</title>
</head>
<body>
${message}<br>
<form action="add_checkpoint.do" method="post">
    <label for="types_list">Checkpoint types</label>
    <select id="types_list" name="types_list">
        <c:forEach var="type" items="${types}">
            <option value="${type.getName()}">${type.getName()}</option>
        </c:forEach>
    </select><br>
    <label for="name_text">Checkpoint name: </label>
    <input type="text" id="name_text" name="name_text"><br>
    <label for="x_text">X coordinate:    </label>
    <input type="text" id="x_text" name="x_text"><br>
    <label for="y_text">Y coordinate:    </label>
    <input type="text" id="y_text" name="y_text"><br>
    <br>
    <button type="submit">Save checkpoint</button>
</form>
</body>
</html>
