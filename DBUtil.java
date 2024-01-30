import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    public static int insert(Connection con, SecSql sql) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(sql.getFormat());
             ResultSet rs = pstmt.getGeneratedKeys()) {

            sql.bindArgs(pstmt);

            pstmt.executeUpdate();

            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        return -1; // 오류 발생 시 -1 리턴
    }
}
