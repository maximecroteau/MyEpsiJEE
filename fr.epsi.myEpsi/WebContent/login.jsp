<%@page import="fr.epsi.myEpsi.beans.Offer"%>
<%@page import="fr.epsi.myEpsi.beans.User"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String okSignup = (String)request.getAttribute("OKSIGNUP");
    if(okSignup == null) okSignup = "";
%>
<html>
<head>
    <link rel="stylesheet" href="CSS/materialize.css" />
    <link rel="stylesheet" href="CSS/custom.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Connectez-vous !</title>
</head>

<body>
<nav>
    <div class="nav-wrapper teal lighten-2">
        <a href="#" class="brand-logo">MyEpsi Application</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
	    	<li>Pas encore sur MyEpsi ? <a class="ib" href="/fr.epsi.myEpsi/signup.jsp"><b>Inscrivez vous !</b></a></li>
	    </ul>
    </div>
</nav>


<div class="row mt50">

    <div class="col s6 offset-s3">
        <img alt="logo EPSI" src="IMG/epsilogo.png" class="logo">
        
        <p class="error"><%=okSignup%></p>
        
        <form action="/fr.epsi.myEpsi/LoginServlet" method="POST">
            <input type="text" name="LOGIN" placeholder="E-mail" autofocus><br>
            <input type="password" name="PWD" placeholder="Mot de passe"><br>
            <button class="btn waves-effect waves-light btn-co" type="submit" name="action">Connexion</button>
        </form>
    </div>

</div>

</body>
</html>