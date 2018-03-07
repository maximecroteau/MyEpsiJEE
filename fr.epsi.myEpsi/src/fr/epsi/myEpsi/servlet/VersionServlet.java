package fr.epsi.myEpsi.servlet;

import fr.epsi.myEpsi.Constants;
import sun.misc.Version;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/VersionServlet")
public class VersionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().append("Served at : ").append(request.getContextPath());
        response.getWriter().println("Version" + Constants.VERSION);
        response.flushBuffer();
    }
}
