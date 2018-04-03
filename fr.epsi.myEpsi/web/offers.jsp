<%@page import="fr.epsi.myEpsi.beans.Offer"%>
<%@page import="fr.epsi.myEpsi.beans.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	@SuppressWarnings("unchecked")
	List<Offer> myOffers = (List<Offer>) request.getAttribute("OFFERS");
	User user = (User) request.getAttribute("USER");
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
			<li><a href="/fr.epsi.myEpsi/addOfferServlet?ID=<%=user.getId()%>">Ajouter
					une annonce</a>
			</li>
			<li><a href="/fr.epsi.myEpsi/login.jsp">D�connexion</a></li>
		</ul>
	</div>
	</nav>

	<h1>
		Bienvenue
		<%
		out.print(user.getNom());
	%>
		!
	</h1>
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
			out.println(
					"<td><input type=\"button\" class=\"btn waves-effect waves-light\" onclick=\"location.href='editOfferServlet?ID="
							+ offer.getId() + "&USER="+ user.getId() +"&EDIT=0';\" value=\"Afficher\" /></d>");
			out.println(
					"<td><input type=\"button\" class=\"btn waves-effect waves-light\" onclick=\"location.href='editOfferServlet?ID="
							+ offer.getId() + "&USER="+ user.getId() +"&EDIT=1';\" value=\"Modifier\" /></d>");
			out.println(
					"<td><input type=\"button\" class=\"btn waves-effect waves-light\" onclick=\"location.href='deleteOfferServlet?ID="
							+ offer.getId() + "&USER="+ user.getId() +"';\" value=\"Supprimer\" /></d>");
			out.println("</tr >");
			out.println("</tbody >");
		}
	%>
	</table>
</body>
</html>