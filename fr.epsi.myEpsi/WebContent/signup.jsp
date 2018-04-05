<%@page import="fr.epsi.myEpsi.beans.Offer"%>
<%@page import="fr.epsi.myEpsi.beans.User"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
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
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Inscrivez-vous !</title>
</head>

<body>
<nav>
    <div class="nav-wrapper teal lighten-2">
        <a href="#" class="brand-logo">MyEpsi Application</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
	    	<li>Déjà inscrit ? <a class="ib" href="/fr.epsi.myEpsi/login.jsp"><b>Connectez vous !</b></a></li>
	    </ul>
    </div>
</nav>

<div class="row login">

    <div class="col s6 offset-s3">
        <img alt="logo EPSI" src="IMG/epsilogo.png" class="logo mt50">
        <form action="/fr.epsi.myEpsi/SignupServlet" id="newUser" method="POST">
			<div class="row">
				<div class="input-field col s12">
					<input type="text" id="name" name="NAME" class="active"> 
					<label class="active" for="name">Votre nom</label>
				</div>
				<div class="input-field col s12">
					<input type="text" id="tel" name="TEL" class="active">
					<label class="active" for="tel">Votre numéro de téléphone</label>
				</div>
				<div class="input-field col s12">
					<% if (!loginError.equals("") || !mailError.equals("")) {%>
						<p class="error"><%=loginError%><%=mailError%></p>
					<% } %>
					<input type="text" id="login" name="LOGIN" class="active">
					<label class="active" for="login">Votre adresse mail</label>
				</div>
				<div class="input-field col s12">
					<% if (!pwdError.equals("")) {%>
						<p class="error"><%=pwdError%></p>
					<% } %>
					<input type="password" id="pwd" name="PWD">
					<label class="active" for="pwd">Votre mot de passe</label>
				</div>
				<div class="input-field col s12">
					<input type="password" id="repwd" name="REPWD">
					<label class="active" for="repwd">Confirmez votre mot de passe</label>
				</div>
				<div class="col s3 offset-s2">
					<a class="waves-effect waves-light btn" onclick="window.history.back();"><i class="material-icons left">arrow_back</i>Retour</a>
				</div>
				<div class="col s3 offset-s3 ib">
					<a class="waves-effect waves-light btn" onclick="validateForm();"><i class="material-icons left">check</i>S'inscrire</a>
				</div>
			</div>
		</form>
        
 	</div>

</div>
<script type="text/javascript">
	function validateForm() {
	    document.getElementById('newUser').submit();
	}
</script>
</body>
</html>