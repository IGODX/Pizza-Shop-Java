package Classes.shopPosition;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import Classes.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import org.json.JSONObject;

@WebServlet(name = "ShopPositionServlet", value = "/ShopPositionServlet")
public class ShopPositionServlet extends HttpServlet {
    Database db = new Database();

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
        JSONObject json = new JSONObject();
        String query = "SELECT * FROM `pizzashoplocation`";
        ResultSet resultSet = null;
        try {
            resultSet = db.getStatement().executeQuery(query);
            while(resultSet.next()) {
                double lon = resultSet.getDouble("lon");
                double lat = resultSet.getDouble("lat");
                json.put("lng", lon);
                json.put("lat", lat);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
    }


}