<%@page import="fr.epsi.myEpsi.beans.Offer"%>
<%@page import="fr.epsi.myEpsi.beans.User"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    @SuppressWarnings("unchecked")
    String loginError = (String)request.getAttribute("LOGINERROR");
    String mailError = (String)request.getAttribute("MAILERROR");
    String pwdError = (String)request.getAttribute("PWDERROR");
    if(mailError == null) mailError = "";
    if(pwdError == null) pwdError = "";
    if(loginError == null) loginError = "";
%>

<html>
<head>
    <link rel="stylesheet" href="CSS/materialize.css" />
    <link rel="stylesheet" href="CSS/custom.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Inscrivez-vous !</title>
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
        <form action="/fr.epsi.myEpsi//SignupServlet" method="POST">
            <!--<form action="/fr.epsi.myEpsi/LoginServlet" method="POST"> -->
            <input type="text" name="NAME" placeholder="Votre nom" autofocus><br>
            <input type="text" name="TEL" placeholder="Votre téléphone"><br>
            <input type="text" name="LOGIN" placeholder="Votre e-mail"><br>
            <p style="color:red;"><% out.print(loginError); out.print(mailError); %></p>
            <input type="password" name="PWD" placeholder="Mot de passe"><br>
            <p style="color:red;"><% out.print(pwdError); %></p>
            <input type="password" name="REPWD" placeholder="Confirmez votre mot de passe"><br>
            <button class="btn waves-effect waves-light btn-co" type="submit" name="action">S'inscrire</button>
        </form>
        <p>Déjà inscrit ?</p>
        <a class="waves-effect waves-light btn" href="/fr.epsi.myEpsi/login.jsp">Connexion</a>

    </div>

</div>
</body>
</html>