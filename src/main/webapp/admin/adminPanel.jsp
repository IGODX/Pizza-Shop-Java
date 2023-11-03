<%--
  Created by IntelliJ IDEA.
  User: Игорь
  Date: 31.10.2023
  Time: 3:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="/templates/head.jsp" />
</head>
<body>
<jsp:include page="/templates/header.jsp" />
<main>
    <div class="mb-3">
<a href="NewDefaultPizzaServlet">Add new default pizza</a>
    </div>
    <div class="mb-3">
<a href="NewDefaultPizzaIngredientServlet">Add new default pizza ingredient</a>
    </div>
    <div class="mb-3">
<a href="NewCustomPizzaTypeServlet">Add new custom pizza ingredient type</a>
    </div>
    <div class="mb-3">
<a href="NewCustomPizzaIngredientServlet">Add new custom pizza ingredient</a>
    </div>
</main>
<jsp:include page="/templates/footer.jsp" />
</body>
</html>
