import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println(" Initialisation du secutity filter ");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        System.out.println(" SecurityFilter : AVANT verification");

        try {
            HttpSession session = req.getSession(false);

            if (session != null && session.getAttribute("user") != null) {
                System.out.println("SecurityFilter : Acces autorise");

                System.out.println(
                    "SecurityFilter : AVANT chain.doFilter()"
                );

                chain.doFilter(request, response);

                System.out.println(
                    "SecurityFilter : APRES chain.doFilter()"
                );

            } else {
                System.out.println(
                    "SecurityFilter : Acces refuse"
                );

                res.sendRedirect("login.html");
            }
        } finally {
            System.out.println("SecurityFilter : Fin du filtre");
        }
    }

    @Override
    public void destroy() {
        System.out.println(" Destruction du security filter : ");
    }
}
