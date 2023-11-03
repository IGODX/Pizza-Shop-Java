package com.example.fourteenhw.admin;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Classes.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "NewDefaultPizzaIngredientServlet", value = "/NewDefaultPizzaIngredientServlet")
public class NewDefaultPizzaIngredientServlet extends HttpServlet {
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
        request.getRequestDispatcher("admin/newDefaultPizzaIngredient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ingredientName = request.getParameter("ingredientName");

        String query = "INSERT INTO `ingredient`(`ingredientName`) VALUES (?)";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query)){
            preparedStatement.setString(1, ingredientName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("admin/adminPanel.jsp").forward(request,response);
    }
}