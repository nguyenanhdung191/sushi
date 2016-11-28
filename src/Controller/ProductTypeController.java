package Controller;

import Model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet(name = "ProductTypeController")
public class ProductTypeController extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if(action.equals("getAll")){
            out.println(getAllProductType());
        }
    }

    protected String getAllProductType() {
        String json = "{\"producttypes\": [";
        ProductTypeDAL pd = new ProductTypeDAL();
        ArrayList<ProductType> list = pd.getAllProductType();
        for (int i = 0; i < list.size(); i++) {
            ProductType pt = list.get(i);
            json += "{";
            json += "\"id\": \"" + pt.getProductTypeID() + "\",";
            json += "\"name\": \"" + pt.getProductTypeName() + "\",";
            json += "\"description\": \"" + pt.getProductTypeDescription() + "\"";
            json += "}";
            if (i < (list.size() - 1)) {
                json += ",";
            }
        }
        json += "]}";
        return json;
    }
}
