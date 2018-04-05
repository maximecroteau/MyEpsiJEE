<%@page import="fr.epsi.myEpsi.beans.Offer"%>
<%@page import="fr.epsi.myEpsi.beans.User"%>
<%@page import="fr.epsi.myEpsi.beans.Status"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	@SuppressWarnings("unchecked")
	List<Offer> myOffers = (List<Offer>) request.getAttribute("OFFERS");
	User user = (User) request.getAttribute("USER");
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
%>
<html>
<head>
	<link rel="stylesheet" href="CSS/materialize.css" />
	<link rel="stylesheet" href="CSS/custom.css" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Mes annonces</title>
</head>
<body>

	<nav>
		<div class="nav-wrapper teal lighten-2">
			<a href="#" class="brand-logo">MyEpsi Application</a>
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><a href="/fr.epsi.myEpsi/newOffersServlet?ID=<%=user.getId()%>">Ajouter une annonce</a></li>
				<li><a href="/fr.epsi.myEpsi/login.jsp">Déconnexion</a></li>
			</ul>
		</div>
	</nav>

	<h2>Bienvenue <%=user.getNom()%> !</h2>
	<h4>Vous pouvez consulter les annonces disponibles ci-dessous :</h4>
	<div class="row">
		<table class="striped col s10 offset-s1">
		<thead >
			<tr>
				<th></th>
				<th>Titre</th>
				<th>Description</th>
				<th>Vendeur</th>
				<th>Publiée le</th>
				<th>Actions</th>
				<th>Acheter</th>
			</tr>
		</thead >
		<tbody>
		<% for (Offer offer : myOffers) { %>
			<tr>
				<td>
					<% if(offer.getVendeur().getId().equals(user.getId())){ %>
						<i class="material-icons">account_circle</i>
						<% if(offer.getStatut() == Status.ANNULE){ %>
							<i class="material-icons rouge">delete</i>
						<% } %>
						<% if(offer.getStatut() == Status.VENDU){ %>
							<i class="material-icons vert">done</i>
						<% } %>
					<% } else { %>
						<i class="material-icons">people</i>
					<% } %>

				</td>
				<td><%=offer.getTitre()%></td>
				<td><%=offer.getDescription()%></td>
				<td><%=offer.getVendeur().getNom()%></td>
				<td><%=dateFormat.format(offer.getCreation())%></td>
				<td>
					<a class='waves-effect waves-light btn' href="/fr.epsi.myEpsi/editOfferServlet?ID=<%=offer.getId()%>&USER=<%=user.getId()%>&EDIT=0"><i class="material-icons">visibility</i></a>
					<% if((offer.getVendeur().getId().equals(user.getId())) && (offer.getStatut() == Status.PUBLIE || offer.getStatut() == Status.TEMPORAIRE) ){ %>
						<a class="waves-effect waves-light btn" href="/fr.epsi.myEpsi/editOfferServlet?ID=<%=offer.getId()%>&USER=<%=user.getId()%>&EDIT=1"><i class="material-icons">edit</i></a>
						<a class="waves-effect waves-light btn modal-trigger" href="#modalDelete<%=offer.getId()%>"><i class="material-icons">delete</i></a>
					<% } %>
				</td>
				<td>
					<% if(!offer.getVendeur().getId().equals(user.getId())){ %>
						<a class="waves-effect waves-light btn modal-trigger" href="#modalBuy<%=offer.getId()%>"><i class="material-icons left">shopping_cart</i>Je l'achète !</a>
					<% } else if(offer.getStatut() == Status.VENDU) { %>
						Vendue à <b><%=offer.getAcheteur().getNom()%></b>
					<% } %>
				</td>
			</tr>
		<% } %>
		</tbody>
	</table>
	</div>
	
	<% for (Offer offer : myOffers) { %>
		 <!-- Modal Structure Delete -->
		  <div id="modalDelete<%=offer.getId()%>" class="modal">
		    <div class="modal-content">
		      <h4>Confirmer la suppression</h4>
		      <p class="ml20">Voulez-vous vraiment supprimer l'annonce <%=offer.getTitre()%> ? </p>
		    </div>
		    <div class="modal-footer">
		      <a class="modal-action waves-effect waves-light btn" href="/fr.epsi.myEpsi/deleteOfferServlet?ID=<%=offer.getId()%>">Oui</a>
		      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Non</a>
		    </div>
		  </div>
		  
		    <!-- Modal Structure Buy -->
		  <div id="modalBuy<%=offer.getId()%>" class="modal">
		    <div class="modal-content">
		      <h4>Confirmer l'achat</h4>
		      <p class="ml20">Voulez-vous vraiment acheter l'objet de l'annonce <%=offer.getTitre()%> ? </p>
		    </div>
		    <div class="modal-footer">
		      <a class="modal-action waves-effect waves-light btn" href="/fr.epsi.myEpsi/buyOfferServlet?ID=<%=offer.getId()%>&BUYER=<%=user.getId()%>"><i class="material-icons left">shopping_cart</i>Oui</a>
		      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Non</a>
		    </div>
		  </div>
	  <% } %>
	  
	<script>
		$(document).ready(function(){
	    	$('.modal').modal();
	  	});
	</script>
	
	
</body>
</html>