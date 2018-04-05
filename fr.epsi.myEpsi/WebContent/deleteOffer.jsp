<%@page import="fr.epsi.myEpsi.beans.Offer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Offer myOffer = (Offer)request.getAttribute("OFFER");
	String userID = (String)request.getAttribute("ID");
%>
<html>
<head>
 	<link rel="stylesheet" href="CSS/materialize.css" />
 	<link rel="stylesheet" href="CSS/custom.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ajout d'annonce</title>
</head>

<body>

	<nav>
	    <div class="nav-wrapper teal lighten-2">
	        <a href="#" class="brand-logo">MyEpsi Application</a>
	        <ul id="nav-mobile" class="right hide-on-med-and-down">
	            <li><a href="/fr.epsi.myEpsi/login.jsp">Deconnexion</a></li>
	        </ul>
	    </div>
	</nav>

	<center>
		<tbody>
		    <tr>
		    	<td>Supprimer l'annonce <%=myOffer.getTitre()%> ?</td>
			    <td>
			    	<input type="button" class="btn waves-effect waves-light" onclick="location.href='removeOfferServlet?ID=<%=myOffer.getId()%>'" value="Oui" />
			    </td>
			    <td>
			    	<input type="button" class="btn waves-effect waves-light" onclick="window.history.back();" value="Non" />
			    </td>
		    </tr>
	    </tbody>
    </center>

</body>
</body>
</html>