<%@ page pageEncoding="UTF-8"%>
<%@ page import="commerce.catalogue.service.CatalogueManager"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="commerce.catalogue.domaine.modele.HuileMoteur" %>
<%
	CatalogueManager catalogueManager = (CatalogueManager)application.getAttribute("catalogueManager") ;
    HuileMoteur huileMoteur ;
    huileMoteur = new HuileMoteur();
    int refArticle = 0;
    if(request.getParameter("commande")!=null) {
      if (request.getParameter("commande").equals("AModifier")) {
        huileMoteur = (HuileMoteur) catalogueManager.chercherArticleParRef(request.getParameter("refArticle")) ;
        session.setAttribute("huilemoteur", huileMoteur) ;
        response.sendRedirect(response.encodeURL("modifie.jsp")) ;
      }
      if (request.getParameter("commande").equals("ajouter")
       || request.getParameter("commande").equals("modifier")) {
        huileMoteur.setRefArticle(request.getParameter("refArticle")) ;
        huileMoteur.setTitre(request.getParameter("titre")) ;
        huileMoteur.setImage(request.getParameter("image")) ;
        huileMoteur.setLitrage(Integer.parseInt(request.getParameter("litrage"))); ;
        huileMoteur.setPrix(request.getParameter("prix")) ;
        huileMoteur.setDisponibilite(request.getParameter("disponibilite")) ;
        huileMoteur.setMarque("marque");
        huileMoteur.setViscosite("viscosite");

        catalogueManager.soumettreArticle(huileMoteur) ;
	    response.sendRedirect(response.encodeURL("affiche.jsp")) ;
      }
      if (request.getParameter("commande").equals("supprimer")) {
        catalogueManager.supprimerArticleParRef(request.getParameter("refArticle")) ;
	    response.sendRedirect(response.encodeURL("affiche.jsp")) ;
      }
    }
    else
      response.sendRedirect(response.encodeURL("affiche.jsp")) ;
%>