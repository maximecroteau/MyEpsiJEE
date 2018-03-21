<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 	<link rel="stylesheet" href="CSS/materialize.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ajout d'annonce</title>
</head>
<%
String userID = (String)request.getAttribute("ID");
out.print(userID);
%>
<body>

	<nav>
	    <div class="nav-wrapper teal lighten-2">
	        <a href="#" class="brand-logo">MyEpsiApplication</a>
	        <ul id="nav-mobile" class="right hide-on-med-and-down">
	            <li><a href="login.html">Deconnexion</a></li>
	        </ul>
	    </div>
	</nav>

 	<form action="/newOffersServlet" method="POST">
    <!--<form action="/fr.epsi.myEpsi/newOffersServlet" method="POST"> -->

    <div class="row">
        <div class="input-field col s12">
            <input type="text" id="title" name="TITLE" class="active">
            <label class="active" for="title">Titre</label>
        </div>
        <div class="input-field col s12">
            <input type="text" id="content" name="CONTENT" class="active">
            <label class="active" for="content">Description</label>
        </div>
        <div class="input-field col s12">
            <input type="text" id="price" name="PRICE" class="active">
            <label class="active" for="price">Prix</label>
        </div>
        <div class="input-field col s12">
            <input value="<%= userID %>" type="hidden" id="user" name="USER">
        </div>
        <div>
            <button class="btn waves-effect waves-light" type="submit" name="action">Envoyer</button>
        </div>
    </div>

</form>
</body>
</body>
</html>