<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 	<link rel="stylesheet" href="CSS/materialize.css" />
 	<link rel="stylesheet" href="CSS/custom.css" />
 	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ajout d'annonce</title>
</head>
<%
	String userID = (String)request.getAttribute("ID");
%>
<body>

	<nav>
	    <div class="nav-wrapper teal lighten-2">
	        <a href="#" class="brand-logo">MyEpsi Application</a>
	        <ul id="nav-mobile" class="right hide-on-med-and-down">
	            <li><a href="/fr.epsi.myEpsi/login.jsp">Deconnexion</a></li>
	        </ul>
	    </div>
	</nav>

	<h4>Créez votre annonce :</h4>
	
	<form action="/fr.epsi.myEpsi/newOffersServlet" id="createOffer" method="POST">
		<div class="row mt50">

			<div class="input-field col s6 offset-s2">
				<input type="text" id="title" name="TITLE" class="active"> 
				<label class="active" for="title">Titre</label>
			</div>
			<div class="input-field col s6 offset-s2">
				<input type="text" id="content" name="CONTENT" class="active">
				<label class="active" for="content">Description</label>
			</div>
			<div class="input-field col s6 offset-s2 mb20">
				<label class="active">Veuillez sélectionner le statut de l'annonce à l'enregistrement :</label>
				<p>
					<input type="radio" name="STATUS" id="TEMPORAIRE" value="0">
					<label for="TEMPORAIRE">Annonce temporaire</label>
				</p>
				<p>
					<input type="radio" name="STATUS" id="PUBLIE" value="1">
					<label for="PUBLIE">Annonce publiée immédiatement</label>
				</p>
			</div>
			<div class="input-field col s6 offset-s2">
				<input type="number" id="price" name="PRICE" class="active">
				<label class="active" for="price">Prix (en Euros)</label>
			</div>
			<div class="input-field col s6 offset-s2">
				<input value="<%=userID%>" type="hidden" id="user" name="USER">
			</div>
			<div class="col s3 offset-s2">
				<a class="waves-effect waves-light btn" onclick="window.history.back();"><i class="material-icons left">arrow_back</i>Retour</a>
			</div>
			<div class="col s3 offset-s3 ib">
				<a class="waves-effect waves-light btn" onclick="validateForm();"><i class="material-icons left">check</i>Valider</a>
			</div>
		</div>
	</form>
	
	<script type="text/javascript">
		function validateForm() {
		    var form = document.getElementById('createOffer');
		    if (form.TITLE.value == "") {
		        alert("Veuillez spécifier un titre");
		        form.TITLE.focus();
		    } else if (form.CONTENT.value == "") {
		        alert("Veuillez spécifier une description");
		        form.CONTENT.focus();
		    } else if (form.PRICE.value == "") {
		        alert("Veuillez spécifier un prix");
		        form.PRICE.focus();
		    } else {
		    	form.submit();	
		    } 
		}
	</script>
</body>
</body>
</html>