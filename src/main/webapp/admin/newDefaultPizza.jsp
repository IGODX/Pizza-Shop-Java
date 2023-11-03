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
<form action="NewDefaultPizzaServlet" method="post">
    <div class="mb-3">
        <label for="pizzaName" class="form-label">Enter pizza name:</label>
        <input id="pizzaName" class="form-control" name="pizzaName" type="text" required>
    </div>
    <div class="mb-3">
        <label for="photoUrl" class="form-label">Enter photo url:</label>
        <input id="photoUrl" class="form-control" name="photoUrl" type="text" required>
    </div>
    <input class="btn btn-outline-primary" type="submit" value="Add">
</form>
    </div>
</main>
<jsp:include page="/templates/footer.jsp" />
</body>
</html>
