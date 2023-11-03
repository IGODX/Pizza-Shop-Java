<%@ page import="Classes.Cart.ShoppingCart" %>
<%
    HttpSession requestSession = request.getSession();
    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

    if (cart == null) {
        cart = new ShoppingCart();
        session.setAttribute("cart", cart);
    }
%>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a href="PizzasServlet" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
            <img style="height: 60px; margin-left: 20px" src="https://searchlogovector.com/wp-content/uploads/2019/11/the-pizza-company-logo-vector.png" alt="logo">
        </a>
        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
        <li class="nav-item"><a href="#" class="nav-link active" aria-current="page">Home</a></li>
        <li class="nav-item"><a class="nav-link" href="PizzasServlet">Pizzas</a></li>
        <li class="nav-item"><a href="AdminServlet" class="nav-link">Admin</a></li>
        <li class="nav-item"><a href="#" class="nav-link">FAQs</a></li>
                <li class="nav-item">
                    <a class="nav-link" href="CartServlet">
                        <span><i class="fas fa-shopping-cart"></i></span>
                        <span class="badge badge-pill bg-danger">${cart.items.size()}</span>
                    </a>
                </li>
    </ul>
</nav>
</header>