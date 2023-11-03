package com.example.fourteenhw.cart;

import java.io.*;

import Classes.Cart.CartItem;
import Classes.Cart.ShoppingCart;
import Classes.Pizza;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("cart/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idStr = request.getParameter("id");
        String pizzaName = request.getParameter("pizzaName");
        String photoUrl = request.getParameter("photoUrl");
        String isCustomStr = request.getParameter("isCustom");
        String[] selectedIngredients = request.getParameterValues("selectedIngredients");


        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        int id = Integer.parseInt(idStr);
        boolean isCustom = Boolean.parseBoolean(isCustomStr);

        if (cart == null)
            cart = new ShoppingCart();

        CartItem item = cart.getCartItem(id);

        if(item == null)
        cart.addItem(new Pizza(id, pizzaName, photoUrl, isCustom, selectedIngredients), 1);
        else {
            item.setQuantity(item.getQuantity() + 1);
        }

        session.setAttribute("cart", cart);

        response.sendRedirect("PizzasServlet");
    }
}