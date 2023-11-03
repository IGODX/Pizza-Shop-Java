package com.example.fourteenhw;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Classes.Cart.CartItem;
import Classes.Cart.ShoppingCart;
import Classes.Database;
import Classes.DistanceManager;
import Classes.Pizza;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "OrderAddressServlet", value = "/OrderAddressServlet")
public class OrderAddressServlet extends HttpServlet {
    Database db = new Database();
    DistanceManager distanceManager = new DistanceManager(); // Are there any services in JSP?)

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
        String[] selectedIngredients = request.getParameterValues("selectedIngredients");
        String id = request.getParameter("id");
        request.setAttribute("selectedIngredients",selectedIngredients);
        request.setAttribute("id",id);
        request.getRequestDispatcher("/order/orderAddress.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lonStr = request.getParameter("lon");
        String latStr = request.getParameter("lat");
        String locationName = request.getParameter("locationName");
        String firstname = request.getParameter("firstname");
        String telephoneNumber = request.getParameter("telephoneNumber");
        String email = request.getParameter("email");

        if(!validateParameters(request, lonStr, latStr, firstname, telephoneNumber, email))
            request.getRequestDispatcher("/order/orderAddress.jsp").forward(request, response);
else {
            HttpSession session = request.getSession();
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            var cartItems = cart.getItems().values();

            int generatedOrderId = insertOrderLocation(locationName);

            insertUser(firstname, telephoneNumber, email, generatedOrderId);

            for (CartItem item : cartItems ) {
               Pizza pizza = item.getPizza();
               String[] selectedIngredients = pizza.getSelectedIngredients();

               if(pizza.getIsCustom())
                   orderCustomPizza(pizza.getId(), selectedIngredients, generatedOrderId);
               else
                   orderDefaultPizza(pizza.getId(), selectedIngredients, generatedOrderId);
            }
            cart.clear();
            session.setAttribute("cart", cart);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private boolean validateParameters(HttpServletRequest request, String lonStr, String latStr, String firstname, String telephoneNumber, String email){
        if (lonStr.isEmpty() || latStr.isEmpty()) {
            request.setAttribute("latError", "Address is not set!");
            return false;
        }

        double lon;
        double lat;
        try {
            lon = Double.parseDouble(lonStr);
            lat = Double.parseDouble(latStr);
        } catch (NumberFormatException e) {
            request.setAttribute("locationError", "Invalid location coordinates!");
            return false;
        }

        if (!isInRange(lon, lat)) {
            request.setAttribute("error", "Too far away!");
            return false;
        }

        if (firstname.isEmpty()) {
            request.setAttribute("firstnameError", "Firstname is not set!");
            return false;
        }

        if (telephoneNumber.isEmpty()) {
            request.setAttribute("telephoneError", "Telephone number is not set!");
            return false;
        }

        if (email.isEmpty()) {
            request.setAttribute("emailError", "Email is not set!");
            return false;
        }

        return true;
    }
    private boolean isInRange(double userLon, double userLat){ // If order location is within 10 km, return true
        String query = "SELECT * FROM `pizzashoplocation`";
        ResultSet resultSet = null;
        try {
            resultSet = db.getStatement().executeQuery(query);
            while(resultSet.next()) {
                double lon = resultSet.getDouble("lon");
                double lat = resultSet.getDouble("lat");
                if(distanceManager.isInRange(lon,lat, userLon, userLat))
                    return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void insertUser(String firstname, String telephoneNumber, String  email,int orderId){
        String query = "INSERT INTO `myuser`(`firstname`, `telephoneNumber`, `email`, `orderId`) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, telephoneNumber);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private int insertOrderLocation(String locationName){
        String orderLocationQuery = "INSERT INTO `orderlocation`(`locationName`) VALUES (?)";
        try(PreparedStatement preparedStatement = db.getConnection().prepareStatement(orderLocationQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, locationName);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next())
                return generatedKeys.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    private void orderDefaultPizza( int id, String[] selectedIngredients, int generatedOrderId){
        if (selectedIngredients != null && !selectedIngredients[0].isEmpty()) {
            for (String ingredientId : selectedIngredients) {
                String insertQuery = "INSERT INTO defaultpizzatoorder (pizzaId, ingredientId, pizzaOrderId ) VALUES (?, ?, ?)";
                addOrderToDatabase(insertQuery,  id, Integer.parseInt(ingredientId),  generatedOrderId);
            }
        }
        else{
            String insertQuery = "INSERT INTO defaultpizzatoorder (pizzaId, pizzaOrderId ) VALUES (?, ?)";
            addOrderToDatabase(insertQuery, id, generatedOrderId);
        }
    }
    private void addOrderToDatabase(String query, int pizzaId, int generatedOrderId){
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, pizzaId);
            preparedStatement.setInt(2, generatedOrderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void addOrderToDatabase(String query, int pizzaId, int ingredientId, int generatedOrderId ){
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, pizzaId);
            preparedStatement.setInt(2, ingredientId);
            preparedStatement.setInt(3, generatedOrderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void orderCustomPizza(int pizzaId,String[] selectedIngredients, int generatedOrderId){
        if (selectedIngredients != null && !selectedIngredients[0].isEmpty()) {
            for (String ingredientId : selectedIngredients) {
                String insertQuery = "INSERT INTO custompizzatoorder (pizzaId, ingredientId, pizzaOrderId ) VALUES (?, ?, ?)";
                addOrderToDatabase(insertQuery, pizzaId, Integer.parseInt(ingredientId), generatedOrderId);
            }
        }
    }
}