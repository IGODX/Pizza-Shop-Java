package com.example.fourteenhw;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Classes.CustomPizzaIngredient;
import Classes.Database;
import Classes.Pizza;
import Classes.PizzaType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "CustomPizzaServlet", value = "/CustomPizzaServlet")
public class CustomPizzaServlet extends HttpServlet {

   private Database db = new Database();

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pizzaId = request.getParameter("id");
        String pizzaName = request.getParameter("pizzaName");
        String photoUrl = request.getParameter("photoUrl");
        String isCustom = request.getParameter("isCustom");
        String query =  "SELECT * FROM `custompizzatype`";
        ResultSet resultSet = null;
        try {
            Map<Integer, PizzaType> pizzaTypes = new HashMap();
            resultSet = db.getStatement().executeQuery(query);
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String typeName = resultSet.getString("typeName");
                pizzaTypes.put(id, new PizzaType(id, typeName));
            }
            query = "SELECT * FROM `custompizzaingredient`";
            resultSet = db.getStatement().executeQuery(query);
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String ingredientName = resultSet.getString("ingredientName");
                int typeId = resultSet.getInt("typeId");
                var pizzaType = pizzaTypes.get(typeId);
                pizzaType.addIngredient(new CustomPizzaIngredient(id, ingredientName, typeId));
            }
            request.setAttribute("pizzaTypes", pizzaTypes);
            request.setAttribute("pizzaName", pizzaName);
            request.setAttribute("photoUrl", photoUrl);
            request.setAttribute("id", pizzaId);
            request.setAttribute("isCustom", isCustom);
            request.getRequestDispatcher("Pizzas/customPizza.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}