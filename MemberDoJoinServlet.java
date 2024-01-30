import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/doJoin")
public class MemberDoJoinServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String driverName = Config.getDbDriverClassName();
        System.out.println();
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.err.printf("[ClassNotFoundException 예외, %s]\n", e.getMessage());
            response.getWriter().append("DB 드라이버 클래스 로딩 실패");
            return;
        }

        Connection con = null;
        try {
            con = DriverManager.getConnection(Config.getDBUrl(), Config.getDBId(), Config.getDBPassword());
            String loginId = request.getParameter("loginId");
            String loginPw = request.getParameter("loginPw");
            String name = request.getParameter("name");
            System.out.println("아이디:"+loginId);
            System.out.println("비밀번호:"+loginPw);
            System.out.println("비밀번호:"+name);
            SecSql sql = SecSql.from("INSERT INTO article");
            sql.append("SET regDate = NOW()");
            sql.append(", loginId = ?, loginId");
            sql.append(", loginPw = ?, loginPw");
            sql.append(", name = ?, name");

            int id = DBUtil.insert(con, sql);
            response.getWriter().append(
                String.format("<script> alert('%d번 회원이 생성되었습니다.'); location.replace('../home/main'); </script>",id));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
            }
}
