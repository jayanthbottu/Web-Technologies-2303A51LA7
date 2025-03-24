import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        Cookie nameCookie = new Cookie("studentName", "");
        nameCookie.setMaxAge(0);
        response.addCookie(nameCookie);

        response.sendRedirect("login.html?message=Logged out successfully");
    }
}