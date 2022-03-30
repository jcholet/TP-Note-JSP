<%--
  Created by IntelliJ IDEA.
  User: jonas
  Date: 23/03/2022
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connexion</title>
    <link href="css/themes/storefront/login.css" rel="stylesheet">
</head>
<body>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <h2 class="active"> Se connecter </h2>
        <div class="fadeIn first">
            <a href="afficheRecherche.jsp">Place du marché</a>
        </div>
        <form action="validationConnexion.jsp">
            <input type="text" id="login" class="fadeIn second" name="login" placeholder="Pseudo">
            <input type="password" id="password" class="fadeIn third" name="password" placeholder="Mot de passe">
            <input type="submit" class="fadeIn fourth" value="Se connecter">
            <br>
        </form>
    </div>
</div>
</body>
</html>
