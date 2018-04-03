<%@page import="fr.epsi.myEpsi.beans.Offer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
@SuppressWarnings("unchecked")
Offer myOffer = (Offer)request.getAttribute("OFFER");
%>
<html>
<head>
 	<link rel="stylesheet" href="CSS/materialize.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ajout d'annonce</title>
</head>
<%
String userID = (String)request.getAttribute("ID");
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

<%  out.println("<center >");
	out.println("<tbody >");
    out.println("<tr >");
    out.println("<td>Supprimer l'annonce \"" + myOffer.getTitre() + "\"?</d>");
    out.println("<td><input type=\"button\" class=\"btn waves-effect waves-light\" onclick=\"location.href='removeOfferServlet?ID="
			+ myOffer.getId() + "';\" value=\"Oui\" /></d>");
    out.println("<td><input type=\"button\" class=\"btn waves-effect waves-light\" onclick=\"window.history.back();\" value=\"Non\" /></d>");
    out.println("</tr >");
    out.println("</tbody >");
     out.println("</center >");%>

</body>
</body>
</html>