package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class OrderDAL extends GeneralDAL {
    public OrderDAL() {
        super();
    }

    public ArrayList<Order> getCurrentOrder() {
        ArrayList<Order> list = new ArrayList<Order>();
        String query = "SELECT * FROM \"order\" WHERE OrderStateCode = -1 OR OrderStateCode = 0";
        ResultSet result = runQuery(query);
        try {
            while (result.next()) {
                Order o = new Order();
                o.setOrderID(result.getInt(1));
                o.setOrderNo(result.getInt(2));
                Timestamp inTime = result.getTimestamp(3);
                Timestamp outTime = null;
                if (result.getTimestamp(4) != null) {
                    outTime = result.getTimestamp(4);
                    o.setOrderOutTime(outTime);
                }else{
                    o.setOrderOutTime(null);
                }
                o.setOrderInTime(inTime);
                o.setOrderStateCode(result.getInt(5));
                list.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
