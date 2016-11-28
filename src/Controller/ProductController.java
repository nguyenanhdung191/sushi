package Controller;

import Model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ProductController")
public class ProductController extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if(action.equals("viewAll")){
            out.println(viewAllProduct());
        }
    }

    protected String viewAllProduct() {
        String json = "{\"products\": [";
        ProductDAL pd = new ProductDAL();
        ArrayList<Product> list = pd.getAllProduct();
        for (int i = 0; i < list.size(); i++) {
            Product p = list.get(i);
            json += "{";
            json += "\"id\": \"" + p.getProductID() + "\",";
            json += "\"name\": \"" + p.getProductName() + "\",";
            json += "\"price\": \"" + p.getProductPrice() + "\",";
            json += "\"imageUrl\": \"" + p.getProductImageUrl() + "\"";
            json += "}";
            if (i < (list.size() - 1)) {
                json += ",";
            }
        }
        json += "]}";
        return json;
    }
}
