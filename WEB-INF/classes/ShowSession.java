import java.io.IOException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class ShowSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();

        Integer visits = (Integer) session.getAttribute("visits");

        if (visits == null) {
            visits = 1;
        } else {
            visits++;
        }

        session.setAttribute("visits", visits);

        response.setContentType("text/html;charset=UTF-8");

        String message = visits == 1
                ? "Welcome to my site!"
                : "Welcome back!";

        response.getWriter().println(
            "<html><body><h2>" + message + "</h2>"
            + "<p>Number of visits in this session: "
            + visits + "</p></body></html>"
        );
    }
}
