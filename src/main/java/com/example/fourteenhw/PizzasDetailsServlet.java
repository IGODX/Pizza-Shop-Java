package com.example.fourteenhw;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import Classes.Database;
import Classes.Ingredient;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "PizzasDetailsServlet", value = "/PizzasDetailsServlet")
public class PizzasDetailsServlet extends HttpServlet {

    Database db = new Database();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pizzaId = request.getParameter("id");
        String pizzaName = request.getParameter("pizzaName");
        String photoUrl = request.getParameter("photoUrl");
        String isCustom = request.getParameter("isCustom");
        String command = "SELECT * FROM `ingredient`;";
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = db.getStatement().executeQuery(command);
            while(resultSet.next()) {
                ingredients.add(new Ingredient(resultSet.getInt("id"),
                        resultSet.getString("ingredientName")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("id", pizzaId);
        request.setAttribute("pizzaName", pizzaName);
        request.setAttribute("photoUrl", photoUrl);
        request.setAttribute("ingredients", ingredients);
        request.setAttribute("isCustom", isCustom);
        request.getRequestDispatcher("Pizzas/pizzasDetails.jsp").forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        db.connectToDb();
    }

    @Override
    public void destroy() {
        super.destroy();
        db.disconnectFromDb();
    }
}