<%@ page pageEncoding="UTF-8"%>
<%@ include file="../enTetePage.html"%>
<%@ page import="commerce.catalogue.service.CatalogueManager" %>
<%@ page import="commerce.catalogue.domaine.modele.Livre" %>
<%@ page import="commerce.catalogue.domaine.modele.Article" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="commerce.catalogue.domaine.modele.HuileMoteur" %>
<%
 if (application.getAttribute("catalogueManager")==null) {
    response.sendRedirect("./index.jsp") ;
  }
  else {
	CatalogueManager catalogueManager = (CatalogueManager)application.getAttribute("catalogueManager") ;
%>
  <div id="content" class="col-full">
    <section class="entry">
      <table>
<%
    HuileMoteur huileMoteur = null ;
    Article article = null ; 
    Boolean enteteAffiche = false ;
    Iterator<Article> listeDesHuilesMoteurs = catalogueManager.getArticles().iterator() ;
    while(listeDesHuilesMoteurs.hasNext()) {
	  article = listeDesHuilesMoteurs.next() ;
	  if (article instanceof HuileMoteur) {
        if (!enteteAffiche) {
%>
        <thead>
          <tr>
              <th>R&eacute;férence&nbsp;</th>
              <th>Titre</th>
              <th>Image</th>
              <th>prix</th>
              <th>Marque</th>
              <th>Litrage</th>
              <th>Viscosité</th>
              <th>Prix au Litre</th>
          </tr>
        </thead>
<%
          enteteAffiche = true ;
        }
        huileMoteur = (HuileMoteur) article ;
%>
		<tbody>
          <tr>
            <td><%=huileMoteur.getRefArticle() %></td>
            <td><%=huileMoteur.getTitre() %></td>
<%
        if (huileMoteur.getImage() != null) {
%>
		    <td><%=huileMoteur.getImage() %></td>
<%
        }
        else { 
%>
		    <td>Inexistante</td>
<%
        }
%>
              <td><%=huileMoteur.getPrix() %></td>
              <td><%=huileMoteur.getLitrage() %></td>
              <td><%=huileMoteur.getViscosite() %></td>
            <td><a href="controleHuileMoteur.jsp?refArticle=<%=huileMoteur.getRefArticle() %>&amp;commande=AModifier">Modifier</a></td>
            <td><a href="javascript:deleteObject('refArticle','<%=huileMoteur.getRefArticle() %>')">Supprimer</a></td>
            <script type="text/javascript">
// <![CDATA[
	function deleteObject(id,idval)
	{
		if(confirm("Etes-vous sur de vouloir supprimer cet article ?"))
		{
			location.href = "controleHuileMoteur.jsp?commande=supprimer&" + id + "=" + idval;
		}
	}
// ]]>
            </script>
          </tr>
        </tbody>
<%
      }
    } 
%>
      </table>				
      <table>
        <tr>
          <td><a href="ajoute.jsp">Ajouter une nouvelle huile moteur</a></td>
        </tr>
      </table>
    </section>
  </div>	 
<%
  }
%>
<%@ include file="../piedDePage.html"%>
