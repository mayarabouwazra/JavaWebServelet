import java.io.IOException;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BirthdaySession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        var out = response.getWriter();

        String name = (String) session.getAttribute("fullName");
        String birthday = (String) session.getAttribute("birthday");

        out.println("<html><body>");

        if (name == null || birthday == null) {
            out.println("<h2>Welcome! Please enter your details.</h2>");
            out.println("<form method='POST' action='BirthdaySession'>");
            out.println("Full name: <input name='fullName' required><br>");
            out.println("Birthday: <input type='date' name='birthday' required><br>");
            out.println("<input type='submit' value='Save'>");
            out.println("</form>");
        } else {
            LocalDate today = LocalDate.now();
            LocalDate nextBirthday = LocalDate.parse(birthday);

            nextBirthday = nextBirthday.withYear(today.getYear());

            if (nextBirthday.isBefore(today)) {
                nextBirthday = nextBirthday.plusYears(1);
            }

            long days = ChronoUnit.DAYS.between(today, nextBirthday);

            out.println("<h2>Hi, " + escape(name) + ".</h2>");
            out.println("<p>There are " + days
                    + " days to your birthday.</p>");
        }

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();

        String name = request.getParameter("fullName");
        String birthday = request.getParameter("birthday");

        try {
            LocalDate.parse(birthday);
            if (name == null || name.trim().isEmpty()) {
                response.sendError(400, "Name is required");
                return;
            }
        } catch (Exception e) {
            response.sendError(400, "Invalid birthday");
            return;
        }

        session.setAttribute("fullName", name.trim());
        session.setAttribute("birthday", birthday);

        response.sendRedirect("BirthdaySession");
    }

    private String escape(String value) {
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
