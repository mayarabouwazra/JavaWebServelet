import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println(" Initialisation du loggingFilter : ");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        String url = httpRequest.getRequestURL().toString();

        long debut = System.nanoTime();

        System.out.println(" LoggingFilter : AVANT chain.doFilter()");
        System.out.println("URL : " + url);

        try {
            chain.doFilter(request, response);
        } finally {
            long fin = System.nanoTime();
            double dureeMs = (fin - debut) / 1_000_000.0;

            System.out.println("LoggingFilter : APRÈS chain.doFilter()");
            System.out.printf("Temps de traitement : %.3f ms%n", dureeMs);
            
        }
    }

    @Override
    public void destroy() {
        System.out.println(" Destruction du LoggingFlter");
    }
}
