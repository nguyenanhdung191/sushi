package Controller;

import Model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


@WebServlet(name = "OrderController")
public class OrderController extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");

        if (action.equals("getCurrentOrder")) {
            out.println(getCurrentOrder());
        }
        if(action.equals("addOrderItem")){
            int orderNo = Integer.parseInt(request.getParameter("orderNo"));
            addOrderItem(orderNo);
        }


    }

    protected String getCurrentOrder() {
        String json = "{\"orders\": [";
        OrderDAL od = new OrderDAL();
        ArrayList<Order> list = od.getCurrentOrder();
        for (int i = 0; i < list.size(); i++) {
            Order o = list.get(i);
            json += "{";
            json += "\"id\": \"" + o.getOrderID() + "\",";
            json += "\"no\": \"" + o.getOrderNo() + "\",";
            SimpleDateFormat format = new SimpleDateFormat("HH:mm - dd/MM/yyyy");
            json += "\"inTime\": \"" + format.format(o.getOrderInTime()) + "\",";
            if (o.getOrderOutTime() != null) {
                json += "\"outTime\": \"" + format.format(o.getOrderOutTime()) + "\",";
            } else {
                json += "\"outTime\": \"\",";
            }
            json += "\"stateCode\": \"" + o.getOrderStateCode() + "\"";
            json += "}";
            if (i < (list.size() - 1)) {
                json += ",";
            }
        }
        json += "]}";
        return json;
    }
    protected void addOrderItem(int orderNo){
        OrderDAL od = new OrderDAL();
        od.addOrderItem(orderNo);
    }
}
