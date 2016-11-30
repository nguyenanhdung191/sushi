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

@WebServlet(name = "OrderDetailController")
public class OrderDetailController extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if (action.equals("getOrderDetail")) {
            out.println(getOrderDetail(Integer.parseInt(request.getParameter("orderID"))));
        }
    }

    protected String getOrderDetail(int orderID) {
        OrderDetailDAL odd = new OrderDetailDAL();
        String json = "{\"orderdetails\": [";
        ArrayList<OrderDetail> list = odd.getOrderDetailByOrderID(orderID);
        for (int i = 0; i < list.size(); i++) {
            OrderDetail od = list.get(i);
            json += "{";
            json += "\"id\": \"" + od.getOrderDetailID() + "\",";
            json += "\"orderID\": \"" + od.getOrderID() + "\",";
            json += "\"productID\": \"" + od.getProductID() + "\",";
            json += "\"productName\": \"" + od.getProduct().getProductName() + "\",";
            json += "\"productPrice\": \"" + od.getProduct().getProductPrice() + "\",";
            json += "\"quantity\": \"" + od.getQuantity() + "\",";
            json += "\"note\": \"" + od.getNote() + "\"";
            json += "}";
            if (i < (list.size() - 1)) {
                json += ",";
            }
        }
        json += "]}";
        return json;
    }
}

