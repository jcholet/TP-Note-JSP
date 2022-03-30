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
    if (request.getParameter("productSelect") != null && request.getParameter("productName") != null) {
        List<Search> searches = new ArrayList();
        Gson gson = new Gson();
        try {
            searches = gson.fromJson(new FileReader("searchData"), new TypeToken<List<Search>>() {
            }.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Search search = new Search();
        search.setCategory(request.getParameter("productSelect"));
        search.setResponseGroup("Offers,ItemAttributes,Images");
        search.setKeywords(request.getParameter("productName"));
        searches.add(search);
        try {
            gson = new Gson();
            String stringify = gson.toJson(searches);
            FileWriter fileWriter = new FileWriter("searchData");
            fileWriter.write(stringify);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Loader loader = new Loader();
        loader.contextInitialized(new ServletContextEvent(application));
        response.sendRedirect("ajoutProduit.jsp");
    }
%>