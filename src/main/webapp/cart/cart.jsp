<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="/templates/head.jsp" />
    <style>
        .cart-img{
            height: 126px;
            width: 126px;
            object-fit: cover;
        }
    </style>
</head>
<body>
<jsp:include page="/templates/header.jsp" />
<main>
<section class="h-100" style="background-color: #eee;">
    <div class="container h-100 py-5">
        <div class=" d-flex justify-content-center h-100">
            <div class="col-10">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h3 class="fw-normal mb-0 text-black">Shopping Cart</h3>
                </div>
                <c:forEach items="${cart.items.values()}" var="item">
                <div class="card rounded-3 mb-4">
                    <div class="card-body p-4">
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="col-md-2 col-lg-2 col-xl-2">
                                <img src="${item.pizza.photoUrl}" class="img-fluid rounded-3 cart-img" alt="${item.pizza.pizzaName}">
                            </div>
                            <div class="col-md-3 col-lg-3 col-xl-3">
                                <p class="lead fw-normal mb-2">${item.pizza.pizzaName}</p>
                            </div>
                            <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                                <form method="post" action="CartItemIncDecServlet">
                                    <input type="hidden" name="quantity" value="${item.quantity}">
                                    <input type="hidden" name="action"  value="dec">
                                    <input type="hidden" name="id"  value="${item.pizza.id}">
                                    <button class="btn btn-link px-2">
                                        <i class="fas fa-minus"></i>
                                    </button>
                                </form>
                                    <input id="form1" min="1" name="quantity" value="${item.quantity}" type="number"
                                           class="form-control form-control-sm" disabled />
                                <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                                    <form method="post" action="CartItemIncDecServlet">
                                    <input type="hidden" name="quantity"  value="${item.quantity}">
                                    <input type="hidden" name="action"  value="inc">
                                    <input type="hidden" name="id"  value="${item.pizza.id}">
                                    <button type="submit" class="btn btn-link px-2">
                                        <i class="fas fa-plus"></i>
                                    </button>
                                    </form>
                            </div>
                            <form action="DeleteFromCartServlet" method="post">
                            <input type="hidden" name="id" value="${item.pizza.id}">
                            <div style="padding-top: 5px" class="col-md-1 col-lg-1 col-xl-1 text-end">
                                <button class="btn btn-link px-2 text-danger"><i class="fas fa-trash fa-lg"></i></button>
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
                </div>
                </c:forEach>
                <c:choose>
                    <c:when test="${cart.items.size() != 0}">
                <div class="card">
                    <form action="OrderAddressServlet" method="get">
                    <div class="card-body">
                        <button type="submit" class="btn btn-warning btn-block btn-lg">Order</button>
                    </div>
                    </form>
                </div>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
</section>
</main>
 <jsp:include page="/templates/footer.jsp" />
<script>

</script>
</body>
</html>
