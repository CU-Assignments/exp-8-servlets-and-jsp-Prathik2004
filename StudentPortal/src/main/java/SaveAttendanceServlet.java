import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class SaveAttendanceServlet extends HttpServlet {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/student_portal";
        String user = "root";  // change this
        String pass = "Mrithik2008@";      // change this
        return DriverManager.getConnection(url, user, pass);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String studentName = request.getParameter("studentName");
        String studentId = request.getParameter("studentId");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO attendance (student_name, student_id, date, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, studentName);
            ps.setString(2, studentId);
            ps.setString(3, date);
            ps.setString(4, status);
            ps.executeUpdate();

            out.println("<h3>Attendance Saved Successfully!</h3>");
            out.println("<a href='index.jsp'>Go Back</a>");

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
