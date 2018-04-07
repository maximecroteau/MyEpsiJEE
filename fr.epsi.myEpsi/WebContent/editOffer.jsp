<%@page import="fr.epsi.myEpsi.beans.Offer"%>
<%@page import="fr.epsi.myEpsi.beans.Status"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Offer myOffer = (Offer)request.getAttribute("OFFER");
	String userID = (String)request.getAttribute("USER");
	int edit = Integer.valueOf((String)request.getAttribute("EDIT"));
	String editable = edit == 1 ? "" : "disabled";
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
%>
<html>
<head>
	<link rel="icon" href="IMG/epsilogo.ico" />
	<link rel="stylesheet" href="CSS/materialize.css" />
	<link rel="stylesheet" href="CSS/custom.css" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><%out.print((edit == 1 ? "Modification" : "Consultation") + " d'annonce");%></title>
</head>

<body>

	<nav>
		<div class="nav-wrapper teal lighten-2">
			<a href="/fr.epsi.myEpsi/getOffersServlet?LOGIN=<%= userID %>" class="brand-logo">MyEpsi Application</a>
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><a href="/fr.epsi.myEpsi/login.jsp"><b>Déconnexion</b></a></li>
			</ul>
		</div>
	</nav>
	
	<h4>Les détails de l'annonce :</h4>
	
	<form action="/fr.epsi.myEpsi/editOfferServlet" id="editOffer" method="POST">
		<div class="row mt50">
			<% if(!(edit == 2)) { %>
				<% if(myOffer.getVendeur().getId().equals(userID)){ %>
					<div class="col s3 offset-s8">
						<% if (myOffer.getStatut() == Status.PUBLIE || myOffer.getStatut() == Status.TEMPORAIRE) { %>
							<% if (edit != 1) { %>
								<a class="waves-effect waves-light btn" href="/fr.epsi.myEpsi/editOfferServlet?ID=<%=myOffer.getId()%>&USER=<%=userID%>&EDIT=1"><i class="material-icons">edit</i></a>
							<% } %>
						 	<a class="waves-effect waves-light btn modal-trigger" href="#modalDelete"><i class="material-icons">delete</i></a>
						<% } %>
					</div>
				<% } else { %>
					<div class="col s3 offset-s8">
						<a class="waves-effect waves-light btn modal-trigger" href="#modalBuy"><i class="material-icons left">shopping_cart</i>Je l'achète !</a>
					</div>
				<% } %>
			<% } %>
			<div class="input-field col s6 offset-s2">
				<input value="<%=myOffer.getTitre()%>" type="text" id="title" name="TITLE" class="active" <%=editable%>> 
				<label class="active" for="title">Titre</label>
			</div>
			<div class="input-field col s6 offset-s2">
				<input value="<%=myOffer.getDescription()%>" type="text" id="content" name="CONTENT" class="active" <%=editable%>>
				<label class="active" for="content">Description</label>
			</div>
			<% if (edit == 1) { %>
				<div class="input-field col s6 offset-s2 mb20">
					<label class="active">Veuillez sélectionner le statut de l'annonce à l'enregistrement :</label>
					<p>
						<input type="radio" name="STATUS" id="TEMPORAIRE" value="0" <%out.print(myOffer.getStatut() == Status.TEMPORAIRE ? " checked" : "");%>>
						<label for="TEMPORAIRE">Annonce temporaire</label>
					</p>
					<p>
						<input type="radio" name="STATUS" id="PUBLIE" value="1" <%out.print(myOffer.getStatut() == Status.PUBLIE ? " checked" : "");%>>
						<label for="PUBLIE">Annonce publiée immédiatement</label>
					</p>
				</div>
			<% } else { %>
				<div class="input-field col s6 offset-s2">
					<label class="active" for="content">Statut</label>
					<input value="<%=Status.getStatusString(myOffer.getStatut())%>" type="text" id="content" name="STATUS" class="active" disabled>
				</div>
			<% } %>
			<div class="input-field col s6 offset-s2">
				<input value="<%=myOffer.getNbVues()%>" type="text" id="content" name="VIEWS" class="active" disabled> 
				<label class="active" for="content">Nombre de vues</label>
			</div>
			<div class="input-field col s6 offset-s2">
				<input value="<%=dateFormat.format(myOffer.getCreation())%>" type="text" id="content" name="CREATION" class="active" disabled> 
				<label class="active" for="content">Date de création</label>
			</div>
			<div class="input-field col s6 offset-s2">
				<input value="<%=dateFormat.format(myOffer.getModification())%>" type="text" id="content" name="EDIT" class="active" disabled> 
				<label class="active" for="content">Date de modification</label>
			</div>
			<div class="input-field col s6 offset-s2">
				<input value="<%=String.valueOf(myOffer.getPrix())%>" type="number" id="price" name="PRICE" class="active" <%=editable%>>
				<label class="active" for="price">Prix (en Euros)</label>
			</div>
			<div class="input-field col s6 offset-s2">
				<input value="<%=myOffer.getVendeur().getId()%>" type="text" id="content" name="MAIL" class="active" disabled> 
				<label class="active" for="content">Mail du vendeur</label>
			</div>
			<% if(myOffer.getStatut() == Status.VENDU) { %>
				<div class="input-field col s6 offset-s2">
					<input value="<%=dateFormat.format(myOffer.getAchat())%>" type="text" id="achat" name="ACHAT" class="active" disabled> 
					<label class="active" for="achat">Date d'achat</label>
				</div>
				<div class="input-field col s6 offset-s2">
					<input value="<%=myOffer.getAcheteur().getNom()%> (<%=myOffer.getAcheteur().getId()%>)" type="text" id="acheteur" name="BUYER" class="active" disabled>
					<label class="active" for="acheteur">Acheteur</label>
				</div>
			<% } %>
			
			<div class="input-field col s6 offset-s2">
				<input value="<%=userID%>" type="hidden" id="user" name="USER">
				<input value="<%=myOffer.getId()%>" type="hidden" id="offer"name="OFFER">
			</div>
			<div class="col s3 offset-s2">
				<a class="waves-effect waves-light btn" onclick="window.history.back();"><i class="material-icons left">arrow_back</i>Retour</a>
			</div>
			<% if (edit == 1) { %>
				<div class="col s3 offset-s3 ib">
					<a class="waves-effect waves-light btn" onclick="validateForm();"><i class="material-icons left">check</i>Valider</a>
				</div>
			<% } %>
		</div>
	</form>

  <!-- Modal Structure Delete -->
  <div id="modalDelete" class="modal">
    <div class="modal-content">
      <h4>Confirmer la suppression</h4>
      <p class="ml20">Voulez-vous vraiment supprimer l'annonce <%=myOffer.getTitre()%> ? </p>
    </div>
    <div class="modal-footer">
      <a class="modal-action waves-effect waves-light btn" href="/fr.epsi.myEpsi/deleteOfferServlet?ID=<%=myOffer.getId()%>&USER=<%=userID%>">Oui</a>
      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Non</a>
    </div>
  </div>
  
   <!-- Modal Structure Buy -->
  <div id="modalBuy" class="modal">
    <div class="modal-content">
      <h4>Confirmer l'achat</h4>
      <p class="ml20">Voulez-vous vraiment acheter l'objet de l'annonce <%=myOffer.getTitre()%> ? </p>
    </div>
    <div class="modal-footer">
      <a class="modal-action waves-effect waves-light btn" href="/fr.epsi.myEpsi/buyOfferServlet?ID=<%=myOffer.getId()%>&BUYER=<%=userID%>"><i class="material-icons left">shopping_cart</i>Oui</a>
      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Non</a>
    </div>
  </div>
  
	<script type="text/javascript">

		$(document).ready(function(){
	    	$('.modal').modal();
	  	});

		function validateForm() {
		    var form = document.getElementById('editOffer');
		    if (form.TITLE.value == "") {
		        alert("Veuillez spécifier un titre");
		        form.TITLE.focus();
		    } else if (form.CONTENT.value == "") {
		        alert("Veuillez spécifier une description");
		        form.CONTENT.focus();
		    } else if (form.PRICE.value == "") {
		        alert("Veuillez spécifier un prix");
		        form.PRICE.focus();
		    } else{
		    	form.submit();	
		    } 
		}
	</script>
</body>
</html>