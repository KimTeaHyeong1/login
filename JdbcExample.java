import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcExample {  

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:5500/login";
        String user = "kmh";
        String password = "0000";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password)) {
            if (connection != null) {
                System.out.println("데이터베이스 연결 성공");

                // users 테이블 조회
                String query = "SELECT * FROM users";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                     ResultSet resultSet = preparedStatement.executeQuery()) {

                    // 결과 출력
                    while (resultSet.next()) {
                        int userId = resultSet.getInt("user_id");
                        String name = resultSet.getString("name");

                        System.out.println("User ID: " + userId + ", name " + name);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("데이터베이스 연결 실패.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
