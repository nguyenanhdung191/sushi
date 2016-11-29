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
                p.setProductTypeID(result.getInt(6));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int addProduct(Product p) {
        String query = "INSERT INTO Product (productName, productPrice, productImageUrl, productDescription, productTypeID) ";
        query += "VALUES(N'"+ p.getProductName() +"',";
        query += "'"+ p.getProductPrice() +"',";
        query += "'"+ p.getProductImageUrl() +"',";
        query += "N'"+ p.getProductDescription() +"',";
        query += "'"+ p.getProductTypeID() +"')";
        return runCRUD(query);
    }

    public int removeProduct(String productID){
        String query = "DELETE FROM product ";
        query += "WHERE ProductID = " + Integer.parseInt(productID);
        return runCRUD(query);
    }

    public int editProduct(Product p){
        String query = "UPDATE product ";
        query += "SET ProductName = N'" + p.getProductName() + "',";
        query += "ProductDescription = N'" + p.getProductDescription() + "',";
        query += "ProductPrice = '" + p.getProductPrice() + "',";
        if(p.getProductImageUrl().equals("") == false){
            query += "ProductImageUrl = '" + p.getProductImageUrl() + "',";
        }
        query += "ProductTypeID = '" + p.getProductTypeID() + "' ";
        query += "WHERE ProductID = '" + p.getProductID() + "'";
        return runCRUD(query);
    }
}
