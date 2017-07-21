package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Emilia.Palaghita on 20-Jul-17.
 */
public class HelloWorldServletForward extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        String attribute = (String) req.getAttribute("testAttribute");
        resp.getWriter().write("Hello <b>" + req.getParameter("user") + " </b> from the Forward Servlet! " + attribute);

    }
}
