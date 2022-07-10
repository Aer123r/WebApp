import java.sql.*;
import java.util.ResourceBundle;

public class DBUtil {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("info");
    private static String driver = resourceBundle.getString("driver");
    private static String url = resourceBundle.getString("url");
    private static String user = resourceBundle.getString("user");
    private static String password = resourceBundle.getString("password");


    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
         return DriverManager.getConnection(url,user,password);
    }
    public static void close(Connection conn, PreparedStatement stm, ResultSet rs) throws SQLException {
        if(rs!=null){
            rs.close();
        }
        if(stm!=null){
            stm.close();
        }
        if(conn!=null){
            conn.close();
        }
    }
}
