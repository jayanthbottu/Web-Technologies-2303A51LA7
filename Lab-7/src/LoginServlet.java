import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        ServletContext context = getServletContext();
        ArrayList<Student> students = (ArrayList<Student>) context.getAttribute("students");

        if (students != null) {
            for (Student student : students) {
                if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("email", email);

                    Cookie nameCookie = new Cookie("studentName", student.getName());
                    response.addCookie(nameCookie);

                    response.sendRedirect("DashboardServlet");
                    return;
                }
            }
        }

        response.sendRedirect("login.html?error=Invalid credentials");
    }
}