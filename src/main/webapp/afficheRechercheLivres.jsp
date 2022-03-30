<%@ page pageEncoding="UTF-8"%>
<%@ include file="enTetePage.html"%>
<%@ page import="commerce.catalogue.service.CatalogueManager"%>
<%@ page import="commerce.catalogue.domaine.modele.Article"%>
<%@ page import="commerce.catalogue.domaine.modele.Livre"%>
<%@ page import="commerce.catalogue.domaine.modele.Musique"%>
<%@ page import="commerce.catalogue.domaine.modele.Piste"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="javax.swing.text.Document" %>
<%
    if (session.getAttribute("panier")==null) {
        response.sendRedirect("./index.jsp");
    } else {
        CatalogueManager catalogueManager = (CatalogueManager) application
                .getAttribute("catalogueManager");
        List<Article> articles = null;
        try {
            articles = catalogueManager.getLivres(request.getParameter("searchBar"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Iterator<Article> listeDesArticles ;
        Livre livre = null;
        Musique musique = null;
        Article article;
%>
<header id="header" class="col-full">
    <div id="logo">
        <h1 class="site-title">
            <a href="afficheRecherche.jsp">Place du marché</a>
        </h1>
        <a href="<%=response.encodeURL("./connexion.jsp")%>">
            Se connecter
        </a>
    </div>
    <nav id="site-navigation" class="main-navigation" role="navigation">
        <button class="menu-toggle" aria-controls="site-navigation" aria-expanded="false">
            <span>Menu</span>
        </button>
        <div class="menu">
            <ul id="nav-menu">
                <li class="page_item page-item-1 current_page_item focus">
                    <a href="<%=response.encodeURL("./afficheRecherche.jsp")%>">Rechercher
                        un article</a>
                </li>
                <li class="page_item page-item-2">
                    <a href="<%=response.encodeURL("./afficheLivre.jsp")%>">
                        Livres
                    </a>
                </li>
                <li class="page_item page-item-3">
                    <a href="<%=response.encodeURL("./afficheHuileMoteur.jsp")%>">
                        Huiles moteur
                    </a>
                </li>
                <li class="page_item page-item-4">
                    <a href="<%=response.encodeURL("./afficheMusique.jsp")%>">
                        Musique
                    </a>
                </li>
                <li class="page_item page-item-5>
								<a href="<%=response.encodeURL("./controlePanier.jsp")%>">Panier</a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<div id="content" class="site-content" tabindex="-1">
    <div class="col-full">
        <div id="primary" class="content-area">
            <main id="main" class="site-main" role="main">
                <header class="woocommerce-products-header">
                    <h1 class="woocommerce-products-header__title page-title">Résultats de la recherche</h1>
                </header>
                <ul class="products columns-3">
                    <%
                        listeDesArticles = articles.iterator() ;
                        int index = 0 ;
                        while (listeDesArticles.hasNext()) {
                            article = (Article) listeDesArticles.next();
                            if ((index % 3) == 0) {
                    %>
                    <li class="product type-product first">
                            <%
											} else {
%>
                    <li class="product type-product">
                        <%
                            }
                            index ++;
                        %>
                        <a
                                href="<%=response.encodeURL("./controlePanier.jsp?refArticle="
											+ article.getRefArticle()
									+ "&amp;commande=ajouterLigne")%>"> <img
                                src="<% if (article.getImage().startsWith("http"))
													out.print(article.getImage()) ;
												else
													out.print("./images/"+article.getImage()) ; %>"
                                class="attachment-shop_catalog wp-post-image" alt="poster_2_up"
                                height="300"/>
                            <h3><%=article.getTitre()%></h3> <span class="price"><ins>
												<span class="amount"><%=article.getPrix()%> €</span>
											</ins></span>

                        </a> <a
                            href="<%=response.encodeURL("./controlePanier.jsp?refArticle="
											+ article.getRefArticle()
									+ "&amp;commande=ajouterLigne")%>"
                            class="button add_to_cart_button product_type_simple">Mettre
                        dans le panier</a>
                        <%
                            if (article instanceof Musique) {
                                musique = (Musique) article;
                                if (musique.getPistes().size() > 0) {
                        %>
                        <div id="jquery_jplayer_<%=article.getRefArticle()%>" class="jp-jplayer"></div>
                        <div id="jp_container_<%=article.getRefArticle()%>" class="jp-audio" role="application">
                            <div class="jp-type-playlist">
                                <div class="jp-gui jp-interface">
                                    <div class="jp-controls-holder">
                                        <div class="jp-controls">
                                            <button class="jp-previous" role="button" tabindex="0">previous</button>
                                            <button class="jp-play" role="button" tabindex="0">play</button>
                                            <button class="jp-stop" role="button" tabindex="0">stop</button>
                                            <button class="jp-next" role="button" tabindex="0">next</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="jp-playlist">
                                    <ul>
                                        <li>&nbsp;</li>
                                    </ul>
                                </div>
                                <div class="jp-no-solution">
                                    <span>Update Required</span> To play the media you will need to
                                    either update your browser to a recent version or update your <a
                                        href="http://get.adobe.com/flashplayer/" target="_blank">Flash
                                    plugin</a>.
                                </div>
                            </div>
                        </div>
                        <%
                                } // if
                            } // if
                        %>
                    </li>
                    <%
                        } // while
                    %>
                </ul>
            </main>
        </div>
    </div><!-- .col-full -->
</div><!-- #content -->
<script type="text/javascript">
    window.addEventListener("load", myFunction, false);
    function myFunction(event) {
        <%
                            listeDesArticles = articles.iterator() ;
                            while (listeDesArticles.hasNext()) {
                                article = (Article) listeDesArticles.next();
                                if (article instanceof Musique) {
        %>
        var myPlaylist_<%=article.getRefArticle()%> = new jPlayerPlaylist({
            cssSelectorAncestor : "#jp_container_<%=article.getRefArticle()%>",
            jPlayer: "#jquery_jplayer_<%=article.getRefArticle()%>"}, [<%
				musique = (Musique) article;
							if (musique.getPistes().size() > 0) {
								Iterator<Piste> itPistes = musique.getPistes()
										.iterator();
								Piste unePiste;
								while (itPistes.hasNext()) {
									unePiste = itPistes.next();
%>
            { title:"<%=unePiste.getTitre().replace("\"", "\\\"" )%>", mp3:"<%=unePiste.getUrl()%>" },<%

								}
							}
%>
        ], { swfPath : "/js/jplayer-2.9.2/jplayer", supplied : "mp3", wmode:
                "window", useStateClassSkin: true, autoBlur: false, smoothPlayBar: true,
            keyEnabled: true });
        <%

                                }
                            }
        %>
        var jp_playlist_tab = document.getElementsByClassName("jp-playlist") ;
        for (i = 0; i < jp_playlist_tab.length; i++) {
            jp_playlist_tab[i].style.display = "none";
        }
    }
</script>
<%
    }
%>
<%@ include file="piedDePage.html"%>