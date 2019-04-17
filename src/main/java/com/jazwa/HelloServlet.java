package com.jazwa;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sayHello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String s = req.getParameter("name");
        String name = "";
        if (s!=null){
            name = s;
        }
        resp.getWriter().println("<h1>Hello world "+ name +"<h1>");
    }
}
