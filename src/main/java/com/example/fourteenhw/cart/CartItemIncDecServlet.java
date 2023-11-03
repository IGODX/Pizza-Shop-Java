package com.example.fourteenhw.cart;

import java.io.*;

import Classes.Cart.CartItem;
import Classes.Cart.ShoppingCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "CartItemIncDecServlet", value = "/CartItemIncDecServlet")
public class CartItemIncDecServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        String actionStr = request.getParameter("action");
        String quantityStr = request.getParameter("quantity");

        int quantity = Integer.parseInt(quantityStr);
        if(actionStr.equals("inc"))
            quantity++;
        else{
            if(quantity > 1)
                quantity--;
        }
        CartItem  cartItem = cart.getCartItem(id);
        cartItem.setQuantity(quantity);
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("cart/cart.jsp").forward(request,response);
    }
}