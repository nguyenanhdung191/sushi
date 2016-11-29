package Controller;

import Model.*;

import java.io.File;
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
        if (action.equals("viewAll")) {
            out.println(viewAllProduct());
        }
        if (action.equals("addMenuItem")) {
            Product p = new Product();
            p.setProductName(request.getParameter("name"));
            p.setProductDescription(request.getParameter("description"));
            p.setProductPrice(Integer.parseInt(request.getParameter("price")));
            p.setProductTypeID(Integer.parseInt(request.getParameter("typeID")));
            p.setProductImageUrl(request.getParameter("imageUrl"));
            ProductDAL pd = new ProductDAL();
            pd.addProduct(p);
        }
        if (action.equals("removeProduct")) {
            ProductDAL pd = new ProductDAL();
            pd.removeProduct(request.getParameter("id"));
            if(request.getParameter("imageUrl") == ""){
                return;
            }
            try {
                File file = new File(getServletContext().getRealPath("/") + "/img/product/" + request.getParameter("imageUrl"));
                if (file.delete()) {
                    System.out.println(file.getName() + " is deleted!");
                } else {
                    System.out.println("Delete operation is failed.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(action.equals("editProduct")){
            Product p = new Product();
            p.setProductName(request.getParameter("name"));
            p.setProductDescription(request.getParameter("description"));
            p.setProductPrice(Integer.parseInt(request.getParameter("price")));
            p.setProductTypeID(Integer.parseInt(request.getParameter("typeID")));
            p.setProductID(Integer.parseInt(request.getParameter("id")));
            p.setProductImageUrl(request.getParameter("imageUrl"));
            ProductDAL pd = new ProductDAL();
            pd.editProduct(p);
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
            json += "\"imageUrl\": \"" + p.getProductImageUrl() + "\",";
            json += "\"description\": \"" + p.getProductDescription() + "\",";
            json += "\"typeID\": \"" + p.getProductTypeID() + "\"";
            json += "}";
            if (i < (list.size() - 1)) {
                json += ",";
            }
        }
        json += "]}";
        return json;
    }
}
