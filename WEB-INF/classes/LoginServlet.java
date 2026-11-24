import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebServlet;



public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        
        String username = request.getParameter("username");
        HttpSession session = request.getSession();
        session.setAttribute("user", username);

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='fr'>");
        out.println("<head><meta charset='UTF-8'>");
        out.println("<title>Connexion réussie</title></head>");
        out.println("<body>");

        out.println("<h1>Connexion réussie !</h1>");
        out.println("<p>Bienvenue, " + escapeHtml(username) + " !</p>");
        out.println("<p>Votre nom d'utilisateur a été enregistré dans la session.</p>");
        out.println("<a href='items.html'>Accéder à la liste des articles</a>");

        out.println("</body></html>");
    }

    private String escapeHtml(String value) {
        if (value == null) {
            return "";
        }

        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }

    


}


