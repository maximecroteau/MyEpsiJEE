<%@page import="fr.epsi.myEpsi.beans.Offer"%>
<%@page import="fr.epsi.myEpsi.beans.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
				<li><a href="/fr.epsi.myEpsi/addOfferServlet?ID=<%=user.getId()%>">Ajouter une annonce</a></li>
				<li><a href="/fr.epsi.myEpsi/login.jsp">Déconnexion</a></li>
			</ul>
		</div>
	</nav>

	<h2>Bienvenue <%=user.getNom()%> !</h2>
	<h4>Vous pouvez consulter les annonces disponibles ci-dessous :</h4>
	<table class="striped">
		<thead >
			<tr>
				<th>Titre</th>
				<th>Description</th>
				<th>Vendeur</th>
				<th>Date de creation</th>
				<th>Actions</th>
			</tr>
		</thead >
		<tbody>
		<% for (Offer offer : myOffers) { %>
			<tr>
				<td><%=offer.getTitre()%></td>
				<td><%=offer.getDescription()%></td>
				<td><%=offer.getVendeur().getNom()%></td>
				<td><%=offer.getCreation()%></td>
				<td>
					<a class='waves-effect waves-light btn' href="/editOfferServlet?ID=<%=offer.getId()%>&USER=<%=user.getId()%>&EDIT=0">Afficher</a>
					<% if(offer.getVendeur().getId() == user.getId()){ %>
						<a class="waves-effect waves-light btn" href="/editOfferServlet?ID=<%=offer.getId()%>&USER=<%=user.getId()%>&EDIT=1"><i class="material-icons">edit</i></a>
						<a class="waves-effect waves-light btn" href="/deleteOfferServlet?ID=<%=offer.getId()%>&USER=<%=user.getId()%>"><i class="material-icons">delete</i></a>
					<% } %>
				</td>
			</tr>
		<% } %>
		</tbody>
	</table>
</body>
</html>