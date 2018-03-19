<%@page import="fr.epsi.myEpsi.beans.Offer"%>
<%@page import="fr.epsi.myEpsi.beans.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
@SuppressWarnings("unchecked")
List<Offer> myOffers = (List<Offer>)request.getAttribute("OFFERS");
User user = (User)request.getAttribute("USER");
%>
<html>
	<head>
        <link rel="stylesheet" href="CSS/materialize.css" />
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Mes annonces</title>
	</head>
<body>

<nav>
    <div class="nav-wrapper teal lighten-2">
        <a href="#" class="brand-logo">MyEpsiApplication</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="addOffer.html">Ajouter une annonce</a></li>
            <li><a href="login.html">Deconnexion</a></li>
        </ul>
    </div>
</nav>

<h1>Bienvenue <% out.print(user.getNom()); %> !</h1>
	<h3>Vous pouvez consulter les annonces disponibles ci-dessous :</h3>

<%
    out.println("<table class=\"striped\">");
    out.println("<thead >");
    out.println("<tr >");
    out.println("<th > Titre </th >");
    out.println("<th > Description </th >");
    out.println("<th > Vendeur </th >");
    out.println("<th > Date de creation </th>");
    out.println("</tr >");
    out.println("</thead >");

for (Offer offer : myOffers) {

    out.println("<tbody >");
    out.println("<tr >");
    out.println("<td>" + offer.getTitre() + "</d>");
    out.println("<td>" + offer.getDescription() + "</d>");
    out.println("<td>" + offer.getVendeur().getNom() + "</d>");
    out.println("<td>" + offer.getCreation() + "</d>");
    out.println("<td><a class=\"waves-effect waves-light btn\">Afficher</a></d>");
    out.println("<td><a class=\"waves-effect waves-light btn\">Modifier</a></d>");
    out.println("<td><a class=\"waves-effect waves-light btn\">Supprimer</a></d>");
    out.println("</tr >");
    out.println("</tbody >");
    //<a class="waves-effect waves-light btn-large">Button</a>
}
%>
    </tr>
</table>
</body>
</html>