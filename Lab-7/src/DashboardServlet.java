import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String email = (String) session.getAttribute("email");
        ServletContext context = getServletContext();
        ArrayList<Student> students = (ArrayList<Student>) context.getAttribute("students");

        Student student = null;
        for (Student s : students) {
            if (s.getEmail().equals(email)) {
                student = s;
                break;
            }
        }

        Cookie[] cookies = request.getCookies();
        String studentName = "Student";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("studentName")) {
                    studentName = cookie.getValue();
                }
            }
        }

        response.setContentType("text/html");
        response.getWriter().println("<h1>Welcome, " + studentName + "!</h1>");
        response.getWriter().println("<p>Your Course: " + (student != null ? student.getCourse() : "Unknown") + "</p>");
        response.getWriter().println("<a href='LogoutServlet'>Logout</a>");
    }
}