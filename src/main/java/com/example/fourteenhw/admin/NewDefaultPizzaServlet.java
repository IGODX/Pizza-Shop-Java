package com.example.fourteenhw.admin;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Classes.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "NewDefaultPizzaServlet", value = "/NewDefaultPizzaServlet")
public class NewDefaultPizzaServlet extends HttpServlet {
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
        request.getRequestDispatcher("/admin/newDefaultPizza.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pizzaName = request.getParameter("pizzaName");
        String photoUrl = request.getParameter("photoUrl");

        String query = "INSERT INTO `pizza`(`pizzaName`, `photoUrl`) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query)){
            preparedStatement.setString(1, pizzaName);
            preparedStatement.setString(2, photoUrl);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("admin/adminPanel.jsp").forward(request,response);
    }
}