package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class OrderDetailDAL extends GeneralDAL {
    public OrderDetailDAL() {
        super();
    }

    public ArrayList<OrderDetail> getOrderDetailByOrderID(int orderID) {
        String query = "SELECT * FROM orderdetail WHERE OrderID = " + orderID;
        ResultSet result = runQuery(query);
        ProductDAL pd = new ProductDAL();
        OrderDAL odal = new OrderDAL();
        ArrayList<OrderDetail> list = new ArrayList<OrderDetail>();
        try {
            while (result.next()) {
                OrderDetail od = new OrderDetail();
                od.setOrderDetailID(result.getInt(1));
                od.setOrderID(result.getInt(2));
                od.setProductID(result.getInt(3));
                od.setQuantity(result.getInt(4));
                od.setNote(result.getString(5));
                od.setOrder(odal.getOrderByID(result.getInt(2)));
                od.setProduct(pd.getProductByID(result.getInt(3)));
                list.add(od);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int addOrderDetail(OrderDetail od) {
        String query = "INSERT INTO orderdetail (OrderID, ProductID, Quantity) ";
        query += "VALUES (" + od.getOrderID() + ",";
        query += od.getProductID() + ",";
        query += od.getQuantity() + ")";
        return runCRUD(query);
    }
}
