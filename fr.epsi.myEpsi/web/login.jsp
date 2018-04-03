<%@page import="fr.epsi.myEpsi.beans.Offer"%>
<%@page import="fr.epsi.myEpsi.beans.User"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    @SuppressWarnings("unchecked")
    String okSignup = (String)request.getAttribute("OKSIGNUP");
    if(okSignup == null) okSignup = "";
%>
<html>
<head>
    <link rel="stylesheet" href="CSS/materialize.css" />
    <link rel="stylesheet" href="CSS/custom.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Connectez-vous !</title>
</head>

<body>
<nav>
    <div class="nav-wrapper teal lighten-2">
        <a href="#" class="brand-logo">MyEpsi Application</a>
    </div>
</nav>


<div class="row login">

    <div class="col s6 offset-s3">
        <img alt="logo EPSI" src="IMG/epsilogo.png" class="logo">
        <p style="color:red;"><% out.print(okSignup); %></p>
        <form action="/LoginServlet" method="POST">
            <!--<form action="/fr.epsi.myEpsi/LoginServlet" method="POST"> -->
            <input type="text" name="LOGIN" placeholder="E-mail" autofocus><br>
            <input type="password" name="PWD" placeholder="Mot de passe"><br>
            <button class="btn waves-effect waves-light btn-co" type="submit" name="action">Connexion</button>
        </form>
        <p>Pas encore sur MyEpsi ?</p>
        <a class="waves-effect waves-light btn" href="signup.jsp">S'inscrire</a>
    </div>

</div>
</body>
</html>