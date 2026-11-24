import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();

        System.out.println(
            "ApplicationListener : L'application démarre."
        );

        context.setAttribute("appName", "MP2 Web Application");

        System.out.println(
            "Nom de l'application : "
            + context.getAttribute("appName")
        );
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        System.out.println(
            "ApplicationListener : L'application s'arrête."
        );
    }
}





