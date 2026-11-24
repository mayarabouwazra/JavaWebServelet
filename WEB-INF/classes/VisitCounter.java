import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class VisitCounter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        int visits = 0;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("visitCount".equals(cookie.getName())) {
                    try {
                        visits = Integer.parseInt(cookie.getValue());
                    } catch (NumberFormatException e) {
                        visits = 0;
                    }
                }
            }
        }

        visits++;

        Cookie counter = new Cookie("visitCount",
                String.valueOf(visits));
        counter.setMaxAge(30 * 24 * 60 * 60);
        counter.setPath(request.getContextPath().isEmpty()
                ? "/" : request.getContextPath());
        response.addCookie(counter);

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println(
            "<html><body><h2>You visited this page "
            + visits + " times.</h2></body></html>"
        );
    }
}
