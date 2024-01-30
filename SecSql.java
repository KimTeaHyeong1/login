import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SecSql {
    private StringBuilder sql;
    private List<Object> args;

    private SecSql(String plainBody) {
        this.sql = new StringBuilder(plainBody);
        this.args = new ArrayList<>();
    }

    public static SecSql from(String plainBody) {
        return new SecSql(plainBody);
    }

    public SecSql append(String... args) {
        for (String arg : args) {
            sql.append(" ").append(arg);
        }
        return this;
    }

    public SecSql append(Object arg) {
        args.add(arg);
        return this;
    }

    public String getFormat() {
        return sql.toString().trim();
    }

    public List<Object> getArgs() {
        return args;
    }

    public void bindArgs(PreparedStatement pstmt) throws SQLException {
        for (int i = 0; i < args.size(); i++) {
            pstmt.setObject(i + 1, args.get(i));
        }
    }
}
