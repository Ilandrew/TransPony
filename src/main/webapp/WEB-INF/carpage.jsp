<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 05.04.16
  Time: 19:11
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
        <th>licensePlate</th>
        <th>vendor</th>
        <th>model name</th>
        <th>type</th>
        <th>fuelConsumption</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="car" items="${collection}">
        <tr>
            <td>${car.getId()}</td>
            <td>${car.getLicensePlate()}</td>
            <td>${car.getVendor()}</td>
            <td>${car.getModel()}</td>
            <td>${car.getType()}</td>
            <td>${car.getFuelConsumption()}</td>
            <td></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
