import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/doJoin")
public class JoinServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String loginId = request.getParameter("loginId");
        String loginPw = request.getParameter("loginPW");
        String loginPwConfirm = request.getParameter("loginPwConfirm");
        String name = request.getParameter("name");

        // loginPw, loginPwConfirm 변수 사용
        if (!loginPw.equals(loginPwConfirm)) {
            // 비밀번호 확인이 일치하지 않는 경우
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>회원가입 실패</h2>");
            out.println("<p>비밀번호와 비밀번호 확인이 일치하지 않습니다.</p>");
            out.println("</body></html>");
            return;
        }

        // 여기에서 받아온 데이터를 이용하여 회원가입 처리를 수행합니다.
        // 실제 데이터베이스에 저장하거나 다른 비즈니스 로직을 수행할 수 있습니다.

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>회원가입이 완료되었습니다.</h2>");
        out.println("<p>로그인 아이디: " + loginId + "</p>");
        out.println("<p>이름: " + name + "</p>");
        out.println("</body></html>");
    }
}
