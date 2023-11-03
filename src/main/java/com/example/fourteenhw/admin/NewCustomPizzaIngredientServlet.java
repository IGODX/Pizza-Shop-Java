package com.example.fourteenhw.admin;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Classes.Database;
import Classes.PizzaType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "NewCustomPizzaIngredientServlet", value = "/NewCustomPizzaIngredientServlet")
public class NewCustomPizzaIngredientServlet extends HttpServlet {
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
        ArrayList<PizzaType> types = new ArrayList<>();
        String query =  "SELECT * FROM `custompizzatype`";
        ResultSet resultSet = null;
        try {
            resultSet = db.getStatement().executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String typeName = resultSet.getString("typeName");
                types.add(new PizzaType(id, typeName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("types", types);
        request.getRequestDispatcher("admin/newCustomPizzaIngredient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ingredientName = request.getParameter("ingredientName");
        String typeIdStr = request.getParameter("typeId");
        if(typeIdStr.isEmpty()){
            request.setAttribute("error", "Type id is not set!");
            request.getRequestDispatcher("admin/newNewCustomPizzaIngredient.jsp").forward(request, response);
        }
        int typeId = Integer.parseInt(typeIdStr);
        String query = "INSERT INTO `custompizzaingredient`(`ingredientName`, `typeId`) VALUES (?,?)";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query)){
            preparedStatement.setString(1, ingredientName);
            preparedStatement.setInt(2, typeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("admin/adminPanel.jsp").forward(request,response);
    }
}