package com.example.fourteenhw.cart;

import java.io.*;

import Classes.Cart.ShoppingCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "DeleteFromCartServlet", value = "/DeleteFromCartServlet")
public class DeleteFromCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (cart == null) {
            cart = new ShoppingCart();
        }
        cart.removeItem(id);
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("cart/cart.jsp").forward(request, response);
    }
}