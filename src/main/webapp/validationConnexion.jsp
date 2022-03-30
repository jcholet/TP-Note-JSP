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
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.swing.text.Document" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="amazon.apaIO.operations.Search" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<%@ page import="commerce.catalogue.service.InitAmazon" %>
<%@ page import="com.zeloon.deezer.domain.Tracks" %>
<%@ page import="com.zeloon.deezer.domain.internal.AlbumId" %>
<%@ page import="commerce.catalogue.domaine.modele.*" %>
<%@ page import="org.jdom2.Element" %>
<%@ page import="com.zeloon.deezer.client.DeezerClient" %>
<%@ page import="com.zeloon.deezer.domain.Artists" %>
<%@ page import="com.zeloon.deezer.domain.internal.search.SearchArtist" %>
<%@ page import="com.zeloon.deezer.domain.Albums" %>
<%@ page import="com.zeloon.deezer.domain.internal.ArtistId" %>
<%@ page import="java.io.*" %>
<%@ page import="org.jdom2.input.SAXBuilder" %>
<%@ page import="amazon.apaIO.configuration.GenericConfiguration" %>
<%@ page import="amazon.apaIO.ApaiIO" %>
<%@ page import="org.jdom2.Namespace" %>
<%@ page import="org.jdom2.JDOMException" %>
<%@ page import="org.jdom2.output.XMLOutputter" %>
<%@ page import="org.jdom2.output.Format" %>
<%@ page import="com.sun.org.apache.xml.internal.security.Init" %>
<%@ page import="commerce.catalogue.service.Loader" %>
<%
    if (request.getParameter("login") != null && request.getParameter("password") != null) {
        if (request.getParameter("login").equals("admin") && request.getParameter("password").equals("admin")) {
            session.setAttribute("admin", true);
            session.setAttribute("user", false);
            session.setAttribute("nom", "admin");
        }
        if(request.getParameter("login").equals("user") && request.getParameter("password").equals("user")) {
            session.setAttribute("user", true);
            session.setAttribute("admin", false);
            session.setAttribute("nom", "user");
        }

        if(session.getAttribute("admin") != null && session.getAttribute("admin").equals(true)) {
            response.sendRedirect("admin/adminPage.jsp");
        } else if(session.getAttribute("user") != null && session.getAttribute("user").equals(true)) {
            response.sendRedirect("afficheRecherche.jsp");
        } else {
            response.sendRedirect("connexion.jsp");
        }
    } else {
        response.sendRedirect("connexion.jsp");
    }
%>