import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Department extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select username,password from users where username=? &&password=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,req.getParameter("user"));
            statement.setString(2,req.getParameter("password"));
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                resp.sendRedirect("/WebApp/dept.html");
            }else{
                resp.sendRedirect("/WebApp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DBUtil.close(connection,statement,resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
