package com.example.fourteenhw;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import Classes.Database;
import Classes.Pizza;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "PizzasServlet", value = "/PizzasServlet")
public class PizzasServlet extends HttpServlet {

    Database db = new Database();


    @Override
    public void init() throws ServletException {
        super.init();
        db.connectToDb();
    }

    @Override
    public void destroy() {
        super.destroy();
        db.connectToDb();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = "SELECT * FROM `pizza`;";
        ResultSet resultSet = null;
        try {
            ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
            resultSet = db.getStatement().executeQuery(command);
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String pizzaName = resultSet.getString("pizzaName");
                String photoUrl = resultSet.getString("photoUrl");
                boolean isCustom = resultSet.getBoolean("isCustom");
                Pizza pizza = new Pizza(id, pizzaName, photoUrl, isCustom);
                pizzas.add(pizza);
            }
            request.setAttribute("pizzas", pizzas);
            request.getRequestDispatcher("/Pizzas/pizzas.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}