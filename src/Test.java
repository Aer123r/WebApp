
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Test {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select username,password from users where username=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,"root");
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                System.out.println(resultSet.getString(2));
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
