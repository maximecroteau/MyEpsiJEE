<%@page import="fr.epsi.myEpsi.beans.Offer"%>
<%@page import="fr.epsi.myEpsi.beans.Status"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Offer myOffer = (Offer) request.getAttribute("OFFER");
	String userID = (String) request.getAttribute("USER");
	int edit = Integer.valueOf((String) request.getAttribute("EDIT"));
	String editable = edit == 1 ? "" : "disabled";
%>
<html>
<head>
<link rel="stylesheet" href="CSS/materialize.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
	<%
		out.print((edit == 1 ? "Modification" : "Consultation") + " d'annonce");
	%>
</title>
</head>
<body>

	<nav>
	<div class="nav-wrapper teal lighten-2">
		<a href="#" class="brand-logo">MyEpsiApplication</a>
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="fr.epsi.myEpsi/login.jsp">Deconnexion</a></li>
		</ul>
	</div>
	</nav>

	<form action="/fr.epsi.myEpsi/modifyOffersServlet" id="editOffer" method="POST">
		<!--<form action="/fr.epsi.myEpsi/newOffersServlet" method="POST"> -->

		<div class="row">
			<div class="input-field col s12">
				<input value="<%=myOffer.getTitre()%>" type="text" id="title"
					name="TITLE" class="active" <%out.print(editable);%>> <label
					class="active" for="title">Titre</label>
			</div>
			<div class="input-field col s12">
				<input value="<%=myOffer.getDescription()%>" type="text"
					id="content" name="CONTENT" class="active" <%out.print(editable);%>>
				<label class="active" for="content">Description</label>
			</div>
			<div class="input-field col s12">
				<label class="active" for="content">Statut</label>

				<%
					if (edit == 1) {
				%>
			</div>
			<form action="#">
				<input type="radio" name="STATUS" id="TEMPORAIRE" value="0"
					<%out.print(myOffer.getStatut() == 0 ? " checked" : "");%>><label
					for="TEMPORAIRE">TEMPORAIRE</label><br> <input type="radio"
					name="STATUS" id="PUBLIE" value="1"
					<%out.print(myOffer.getStatut() == 1 ? " checked" : "");%>><label
					for="PUBLIE">PUBLIE</label><br><input type="radio"
					name="STATUS" id="ANNULE" value="3"
					<%out.print(myOffer.getStatut() == 3 ? " checked" : "");%>><label
					for="ANNULE">ANNULE</label><br>
			</form>
			<%
				} else {
			%>
			<input value="<%=Status.getStatusString(myOffer.getStatut())%>"
				type="text" id="content" name="STATUS" class="active" disabled>
		</div>
		<%
			}
		%>
		<div class="input-field col s12">
			<input value="<%=myOffer.getNbVues()%>" type="text" id="content"
				name="VIEWS" class="active" disabled> <label class="active"
				for="content">Nombre de vues</label>
		</div>
		<div class="input-field col s12">
			<input value="<%=myOffer.getCreation()%>" type="text" id="content"
				name="CREATION" class="active" disabled> <label
				class="active" for="content">Date de création</label>
		</div>
		<div class="input-field col s12">
			<input value="<%=myOffer.getModification()%>" type="text"
				id="content" name="EDIT" class="active" disabled> <label
				class="active" for="content">Date de modification</label>
		</div>
		<div class="input-field col s12">
			<input value="<%=String.valueOf(myOffer.getPrix())%>" type="number"
				id="price" name="PRICE" class="active" <%out.print(editable);%>>
			<label class="active" for="price">Prix</label>
		</div>
		<div class="input-field col s12">
			<input value="<%=myOffer.getVendeur().getId()%>" type="text"
				id="content" name="MAIL" class="active" disabled> <label
				class="active" for="content">Mail du vendeur</label>
		</div>
		<div class="input-field col s12">
			<input value="<%=userID%>" type="hidden" id="user" name="USER">
		</div>
		<div class="input-field col s12">
			<input value="<%=myOffer.getId()%>" type="hidden" id="offer"
				name="OFFER">
		</div>
		<div>
			<input type="button" class="btn waves-effect waves-light"
				onclick="<%out.print(edit == 1 ? "validateForm()"
					: "location.href='editOfferServlet?ID=" + myOffer.getId() + "&USER=" + userID + "&EDIT=1'");%>"
				value="Modifier" /> <input type="button"
				class="btn waves-effect waves-light"
				onclick="location.href='deleteOfferServlet?ID=<%=myOffer.getId()%>&USER=<%=userID%>';"
				value="Supprimer" /> <input type="button"
				class="btn waves-effect waves-light"
				onclick="window.history.back();" value="Retour" />
			</d>
		</div>
		</div>

	</form>
	<script type="text/javascript">
	function validateForm() {
	    var form = document.getElementById('editOffer');
	    if (form.TITLE.value == "") {
	        alert("Veuillez spécifier un titre");
	        form.TITLE.focus();
	    }else if (form.CONTENT.value == "") {
	        alert("Veuillez spécifier une description");
	        form.CONTENT.focus();
	    }else if (form.PRICE.value == "") {
	        alert("Veuillez spécifier un prix");
	        form.PRICE.focus();
	    }else{
	    	form.submit();	
	    }
	    
	}
	</script>
</body>
</body>
</html>