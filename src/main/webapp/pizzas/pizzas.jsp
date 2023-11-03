<%--
  Created by IntelliJ IDEA.
  User: Игорь
  Date: 23.10.2023
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="/templates/head.jsp" />
    <style>
        a{
            text-decoration: none !important;
        }
        .card{
            transition: transform 0.7s;
        }
        .card:hover{
            transform: scale(1.1);
        }
        .pizza-title{
            color: rgb(253, 216, 22);
        }
        img{
            object-fit: cover;
        }
    </style>
</head>
<body>
<jsp:include page="/templates/header.jsp" />
<main>
<div class="container">
<div class="d-flex flex-wrap">
<c:forEach items="${pizzas}" var="pizza">
    <c:choose>
    <c:when test="${!pizza.isCustom}">
    <div style="padding: 15px">
        <a href="PizzasDetailsServlet?id=${pizza.id}&photoUrl=${pizza.photoUrl}&pizzaName=${pizza.pizzaName}&isCustom=${pizza.isCustom}">
            <div class="card" style="width: 18rem;">
                <img src="${pizza.photoUrl}" style="height: 300px" class="img-responsive gallery-img" alt="${pizza.pizzaName}">
                <div class="card-body">
                    <h3 class="text-center pizza-title">${pizza.pizzaName}</h3>
                </div>
            </div>
        </a>
    </div>
    </c:when>
    <c:otherwise>
    <div style="padding: 15px">
        <a href="CustomPizzaServlet?id=${pizza.id}&photoUrl=${pizza.photoUrl}&pizzaName=${pizza.pizzaName}&isCustom=${pizza.isCustom}">
            <div class="card" style="width: 18rem;">
                <img src="${pizza.photoUrl}" style="height: 300px" class="img-responsive gallery-img" alt="${pizza.pizzaName}">
                <div class="card-body">
                    <h3 class="text-center pizza-title">${pizza.pizzaName}</h3>
                </div>
            </div>
        </a>
    </div>
    </c:otherwise>
    </c:choose>

</c:forEach>
</main>
<jsp:include page="/templates/footer.jsp" />
</body>
</html>
