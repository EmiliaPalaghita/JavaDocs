/**
 * HelloWorldServlet.java
 * <p>
 * Copyright (c) 2014 Teamnet. All Rights Reserved.
 * <p>
 * This source file may not be copied, modified or redistributed,
 * in whole or in part, in any form or for any reason, without the express
 * written consent of Teamnet.
 **/

package ro.teamnet.zth.web;

import sun.misc.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Hello World Servlet expose get method to say hello to input user
 */
public class HelloWorldServlet extends HttpServlet {

    /**
     * This method is used to map a GET request from the client side
     * @param request the HttpServletRequest instance
     * @param response the HttpServletResponse instance
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        String user = "";

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/hellofwd");
        request.setAttribute("testAttribute", "Enjoy Z2H");
        requestDispatcher.include(request, response);

        //  Obtain the username from the request instance
        user = request.getParameter("user");

        //  Write the response content to the PrintWriter instance of the response instance
        // TIP: use write() method
        // TIP: you can also use html tags to markup your text

        response.getWriter().write("<h2> Hello " + user + "</h2>");
    }

}
