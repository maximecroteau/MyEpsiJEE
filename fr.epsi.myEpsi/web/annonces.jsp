<%@page import="fr.epsi.myEpsi.beans.Offer"%>
<%@page import="fr.epsi.myEpsi.beans.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    @SuppressWarnings("unchecked")
    List<Offer> myOffers = (List<Offer>)request.getAttribute("ANNONCES");
    User user = (User)request.getAttribute("USER");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Mes offers</title>
</head>
<body>
<h1>Bienvenue <% out.print(user.getNom()); %> !</h1>
<h3>Vous pouvez consulter les offers disponibles ci-dessous :</h3>
<table>
    <tr>
        <th>Titre</th>
        <th>Description</th>
        <th>Vendeur</th>
        <th>Date de publication</th>
    </tr>
    <%
        for (Offer offer : myOffers) {
            out.println("<tr>");
            out.println("<td>" + offer.getTitre() + "</td>");
            out.println("<td>" + offer.getDescription() + "</td>");
            out.println("<td>" + offer.getVendeur().getNom() + "</td>");
            out.println("<td>" + offer.getCreation() + "</td>");
            out.println("</tr>");
        }
    %>
</table>
</body>
</html>
