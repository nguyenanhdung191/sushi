package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductTypeDAL extends GeneralDAL {
    public ProductTypeDAL() {
        super();
    }

    public ArrayList<ProductType> getAllProductType() {
        ArrayList<ProductType> list = new ArrayList<ProductType>();
        String query = "SELECT * FROM producttype";
        ResultSet result = runQuery(query);
        try {
            while (result.next()) {
                ProductType pt = new ProductType();
                pt.setProductTypeID(result.getInt(1));
                pt.setProductTypeName(result.getString(2));
                pt.setProductTypeDescription(result.getString(3));
                list.add(pt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
