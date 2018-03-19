package fr.epsi.myEpsi.servlets;

import fr.epsi.myEpsi.beans.Offer;
import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.dao.IOfferDao;
import fr.epsi.myEpsi.dao.IUserDao;
import fr.epsi.myEpsi.dao.hsqlImpl.OfferDao;
import fr.epsi.myEpsi.dao.hsqlImpl.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Calendar;

@WebServlet("/newOffersServlet")
public class newOffersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("TITLE");
        String content = request.getParameter("CONTENT");
        Double prix = Double.parseDouble(request.getParameter("PRICE"));
        //java.sql.Date(today.getTime())

        Offer offer = new Offer();
        offer.setTitre(title);
        offer.setDescription(content);
        offer.setPrix(prix);
        java.util.Date dateJava = new java.util.Date();
        java.sql.Date DateSQL = new java.sql.Date(dateJava.getTime());
        offer.setCreation(DateSQL);
        offer.setStatut(1);
        User user = new User();
        user.setId("Max");
        offer.setVendeur(user);
        OfferDao.saveOffer(offer);
        request.setAttribute("OFFER", offer);
        request.getRequestDispatcher("LoginServlet").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
