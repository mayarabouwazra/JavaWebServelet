import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ReadCookie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String[] names = {
            "firstName", "lastName", "birthDay", "sex"
        };

        String[] values = new String[names.length];
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(cookie.getName())) {
                        values[i] = cookie.getValue();
                    }
                }
            }
        }

        out.println("<html><body><h2>Saved cookies</h2>");
        out.println("<table border='1' cellpadding='8'>");
        out.println("<tr><th>Cookie</th><th>Value</th></tr>");

        for (int i = 0; i < names.length; i++) {
            out.println("<tr><td>" + names[i] + "</td><td>"
                    + (values[i] == null ? "Not found" : values[i])
                    + "</td></tr>");
        }

        out.println("</table>");
        out.println("<p><a href='index.html'>Back</a></p>");
        out.println("</body></html>");
    }
}
