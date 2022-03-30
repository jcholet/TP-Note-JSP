<%--
  Created by IntelliJ IDEA.
  User: jonas
  Date: 23/03/2022
  Time: 08:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="enTetePage.html" %>
<%@ page import="commerce.catalogue.service.CatalogueManager" %>
<%@ page import="commerce.catalogue.domaine.modele.Article" %>
<%@ page import="commerce.catalogue.domaine.modele.Livre" %>
<%@ page import="commerce.catalogue.domaine.modele.Musique" %>
<%@ page import="commerce.catalogue.domaine.modele.Piste" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.swing.text.Document" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.FileNotFoundException" %>
<%@ page import="amazon.apaIO.operations.Search" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<header id="header" class="col-full">
    <div id="logo">
        <h1 class="site-title">
            <a href="afficheRecherche.jsp">Place du marché</a>
        </h1>
    </div>
    <nav id="site-navigation" class="main-navigation" role="navigation">
        <button class="menu-toggle" aria-controls="site-navigation" aria-expanded="false">
            <span>Menu</span>
        </button>
    </nav>
</header>
<div id="content" class="site-content" tabindex="-1">
    <div class="col-full">
        <div id="primary" class="content-area">
            <main id="main" class="site-main" role="main">
                <header class="woocommerce-products-header">
                    <h1 class="woocommerce-products-header__title page-title">Ajouter un produit provenant d'Amazon</h1>
                </header>
                <div>
                    <form action="validationAjoutProduit.jsp">
                        <input type="search" name="productName" placeholder="Produit à ajouter.." class="woocommerce-form__input-checkbox">
                        <br>
                        <br>
                        <label for="selecting">Choisir une catégorie : </label>
                        <select name="productSelect" id="selecting">
                            <option value="Books">Livres</option>
                            <option value="Automotive">Automobile</option>
                            <option value="Music">Musique</option>
                        </select>
                        <br>
                        <br>
                        <input type="submit" name="Valider">
                    </form>
                </div>
            </main>
        </div>
    </div><!-- .col-full -->
</div>
<!-- #content -->
<%@ include file="piedDePage.html" %>
