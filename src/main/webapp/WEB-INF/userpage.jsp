<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 02.04.16
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>id</th>
        <th>First name</th>
        <th>Second name</th>
        <th>Middle name</th>
        <th>Initials</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="user" items="${collection}">
    <tr>
        <td>${user.getId()}</td>
        <td>${user.getFirstName()}</td>
        <td>${user.getSecondName()}</td>
        <td>${user.getMiddleName()}</td>
        <td>${user.getInitials()}</td>
        <td></td>
    </tr>
</c:forEach>
    </tbody>
</table>
</body>
</html>
