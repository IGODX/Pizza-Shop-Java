<%--
  Created by IntelliJ IDEA.
  User: Игорь
  Date: 25.10.2023
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="/templates/head.jsp" />
</head>
<body>
<jsp:include page="/templates/header.jsp" />
<main>
<div class="container">
    <h3>Custom pizza additions</h3>
<form action="CartServlet" method="post">
    <input type="hidden" name="photoUrl" value="${photoUrl}">
    <input type="hidden" name="pizzaName" value="${pizzaName}">
    <input type="hidden" name="id" value="${id}">
    <input type="hidden" name="isCustom" value="${isCustom}">
<c:forEach items="${pizzaTypes.values() }" var="pizzaType">
    <div class="mb-3">
        <label for="pizzaType${pizzaType.id}">${pizzaType.typeName}</label>
        <select class="form-select" id="pizzaType${pizzaType.id}" name="selectedIngredients">
    <c:forEach items="${pizzaType.customPizzaIngredients}" var="ingredient">
        <option value="${ingredient.id}">${ingredient.ingredientName}</option>
    </c:forEach>
        </select>
    </div>
</c:forEach>
    <input type="submit" class="btn btn-outline-primary" value="Add to cart">
</form>
</div>
</main>
<jsp:include page="/templates/footer.jsp" />
</body>
</html>
