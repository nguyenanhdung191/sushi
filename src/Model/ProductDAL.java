package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAL extends GeneralDAL {
    public ProductDAL() {
        super();
    }

    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> list = new ArrayList<Product>();
        String query = "SELECT * FROM product";
        ResultSet result = runQuery(query);
        try {
            while (result.next()) {
                Product p = new Product();
                p.setProductID(result.getInt(1));
                p.setProductName(result.getString(2));
                p.setProductPrice(result.getInt(3));
                p.setProductImageUrl(result.getString(4));
                p.setProductDescription(result.getString(5));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
