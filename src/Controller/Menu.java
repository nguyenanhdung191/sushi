package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.*;
@WebServlet(name = "Menu")
public class Menu extends HttpServlet {
    public Menu() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        GeneralDAL pd = new GeneralDAL();
        ResultSet rs = pd.runQuery("SELECT * FROM product");
        try {
            while(rs.next()){
                out.println(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
