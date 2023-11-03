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
    <h2>${pizzaName}</h2>
    <img style="width: 420px; height: 420px; object-fit: cover;" src="${photoUrl}" alt="${pizzaName}">
<h3>Additions</h3>
<form method="post" action="CartServlet">
    <input type="hidden" name="photoUrl" value="${photoUrl}">
    <input type="hidden" name="pizzaName" value="${pizzaName}">
    <input type="hidden" name="id" value="${id}">
    <input type="hidden" name="isCustom" value="${isCustom}">
    <c:forEach items="${ingredients}" var="ingredient">
        <div class="p-1 mb-3">
        <label class="form-label" for="ingredient${ingredient.id}">${ingredient.ingredientName}</label>
        <input class="form-check-label" id="ingredient${ingredient.id}" name="selectedIngredients" value="${ingredient.id}" type="checkbox">
        </div>
    </c:forEach>
    <input type="submit" class="btn btn-outline-primary" value="Add to cart">
</form>
</main>
<jsp:include page="/templates/footer.jsp" />
</body>
</html>
