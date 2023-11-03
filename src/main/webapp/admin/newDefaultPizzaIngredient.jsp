<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="/templates/head.jsp" />
</head>
<body>
<jsp:include page="/templates/header.jsp" />
<main>
    <div class="container">
    <form action="NewDefaultPizzaIngredientServlet" method="post">
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
