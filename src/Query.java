import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select d.name, e.emp_name, e.emp_id from emp e join dept d on d.no = e.dept_no where d.no = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,req.getParameter("dno"));
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                out.println( resultSet.getString("name"));
                out.println(resultSet.getString("emp_name"));
                out.println(resultSet.getString("emp_id"));
                out.println("<br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
