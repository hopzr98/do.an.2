<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin - Product</title>
</head>
<body>
    <h1>Admin - Product</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Unit Price</th>
        </tr>
        <tbody>
        <c:forEach items="${listP}" var="product">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.unitPrice}</td>
            </tr>
        </c:forEach>
    </tbody>
    </table>
</body>
</html>