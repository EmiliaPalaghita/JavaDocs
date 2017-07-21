package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by Emilia.Palaghita on 19-Jul-17.
 */
public class InfoHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        String head = "<html>" +
                        "<head>" +
                            "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
                "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n" +
                "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" +
                "</head>";

        resp.getWriter().write(head);

        String table = "<table class=\"table table-bordered\">" +
                "<thead>";

        /*displaying headers and their values in a table*/

        Enumeration<String> headers = req.getHeaderNames();
        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            table += "<th>" + header + "</th>";
        }
        table += "</thead>" +
                "<tr>";


        headers = req.getHeaderNames();

        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            table += "<td>" + req.getHeader(header) + "</td>";
        }
        table += "</tr>" +
                "</table>";
        resp.getWriter().write(table);

        /*displaying method type (GET) and the query string*/

        resp.getWriter().write("<p>" + req.getMethod() + "</p>");
        resp.getWriter().write("<p>" + req.getQueryString() + "</p>");

        /*displaying table containing cookies*/

        table = "<table class=\"table table-bordered\">" +
                "<tr>";
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            table += "<th>" + cookie.getName() + "</th>";
        }
        table += "</tr>" +
                "<tr>";
        for (Cookie cookie : cookies) {
            table += "<td>" + cookie.getValue() + "</td>";
        }
        table += "</tr>" +
                "</table>";
        resp.getWriter().write(table);

        /*displaying table with parameters and its values*/

        table = "<table class=\"table table-inverse\">" +
                "<tr>";
        Enumeration<String> parameters = req.getParameterNames();
        while (parameters.hasMoreElements()) {
            String parameter = parameters.nextElement();
            table += "<th>" + parameter + "</th>";
        }
        table += "</tr>" +
                "<tr>";
        parameters = req.getParameterNames();
        while (parameters.hasMoreElements()) {
            String parameter = parameters.nextElement();
            table += "<td>" + req.getParameter(parameter) + "</td>";
        }
        table += "</tr>" +
                "</table>";
        resp.getWriter().write(table);

    }
}
