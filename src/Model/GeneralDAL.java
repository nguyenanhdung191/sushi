package Model;
import java.sql.*;
import Common.Constant;

public class GeneralDAL {
    Connection con;
    public GeneralDAL(){
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(Constant.dbServer + Constant.dbName,Constant.dbUsername, Constant.dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public ResultSet runQuery(String sql){
        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            con.close();
            return result;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int runCRUD(String sql){
        try {
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(sql);
            con.close();
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.close();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }
            return 0;
        }
    }
}

