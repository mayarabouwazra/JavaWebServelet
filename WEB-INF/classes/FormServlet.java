import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.servlet.http.Cookie;


public class FormServlet extends HttpServlet {


        @Override
        protected void doPost(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException {


        response.setContentType("text/html;charset=UTF-8");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDate = request.getParameter("birthDate");

        String sex = request.getParameter("sex");


        Cookie c1 = new Cookie("firstName", firstName);
        Cookie c2 = new Cookie("lastName", lastName);
        Cookie c3 = new Cookie("birthDay", birthDay);
        Cookie c4 = new Cookie("sex", sex);

        Cookie[] cookies = {c1, c2, c3, c4};

        for (Cookie c : cookies) {
            c.setMaxAge(7 * 24 * 60 * 60);
            c.setPath(request.getContextPath().isEmpty()
                    ? "/" : request.getContextPath());
            response.addCookie(c);
        }



        PrintWriter out = response.getWriter();

        System.out.println("Java is working!");
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Entrer Les informations personelles</title></head>");
        out.println("<body>");

        out.println("<h2> Information soumise </h2>");

        out.println("<table border='1' cellpadding='10'>");
        out.println("<tr><th>Prénom:</th><td>"
                + escapeHtml(firstName) + "</td></tr>");
        out.println("<tr><th>Nom </th><td>"
                + escapeHtml(lastName) + "</td></tr>");
        out.println("<tr><th>Birth Date</th><td>"
                + escapeHtml(birthDate) + "</td></tr>");
        out.println("<tr><th>Sex</th><td>"
                + escapeHtml(sex) + "</td></tr>");
        out.println("</table>");

        out.println("<br><a href='index.html'>Back to form</a>");
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
