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
  <form action="NewCustomPizzaIngredientServlet" method="post">
    <div class="mb-3">
      <label for="ingredientName" class="form-label">Enter ingredient name:</label>
      <select class="form-select" name="typeId">
        <c:forEach items="${types}" var="type">
          <option value="${type.id}">${type.typeName}</option>
        </c:forEach>
      </select>
      <c:if test="${not empty error}">
        <div style="color: red">
            ${error}
        </div>
      </c:if>
    </div>
    <div class="mb-3">
      <label for="ingredientName" class="form-label">Enter ingredient name:</label>
      <input id="ingredientName" class="form-control" name="ingredientName" type="text" required>
    </div>
    <input class="btn btn-outline-primary" type="submit" value="Add">
  </form>
  </div>
</main>
<jsp:include page="/templates/footer.jsp" />
</body>
</html>