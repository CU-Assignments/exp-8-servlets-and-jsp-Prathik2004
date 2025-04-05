import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class EmployeeServlet extends HttpServlet {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/employee_db";
        String user = "root"; // change if needed
        String pass = "Mrithik2008@";     // change if needed
        return DriverManager.getConnection(url, user, pass);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String empId = request.getParameter("id");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection conn = getConnection()) {
            out.println("<html><body>");
            out.println("<h2>Employee Search</h2>");
            out.println("<form action='' method='get'>");
            out.println("Enter Employee ID: <input type='text' name='id'/>");
            out.println("<input type='submit' value='Search'/>");
            out.println("</form><hr>");

            if (empId != null && !empId.isEmpty()) {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM employees WHERE id = ?");
                ps.setInt(1, Integer.parseInt(empId));
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    out.println("<h3>Employee Details:</h3>");
                    out.println("ID: " + rs.getInt("id") + "<br>");
                    out.println("Name: " + rs.getString("name") + "<br>");
                    out.println("Department: " + rs.getString("department") + "<br>");
                    out.println("Salary: $" + rs.getDouble("salary") + "<br>");
                } else {
                    out.println("<p>No employee found with ID " + empId + "</p>");
                }

                out.println("<hr>");
            }

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

            out.println("<h2>All Employees</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Department</th><th>Salary</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("department") + "</td>");
                out.println("<td>$" + rs.getDouble("salary") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}
