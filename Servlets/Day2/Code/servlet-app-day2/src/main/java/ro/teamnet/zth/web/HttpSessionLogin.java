package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Emilia.Palaghita on 20-Jul-17.
 */

public class HttpSessionLogin extends HttpServlet {
    private final String ADMIN = "admin";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("username");
        String password = req.getParameter("password");

        if(user.equals(ADMIN) && password.equals(ADMIN)) {
            resp.getWriter().write("Welcome back " + user + "!");
            resp.getWriter().write( req.getSession().getId());
            Cookie[] cookies = req.getCookies();
            for(Cookie cookie : cookies) {
                resp.getWriter().write(cookie.getName());
            }
        } else {
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("session", req.getSession());
            resp.sendRedirect("views/loginFail.jsp");
        }
    }
}
