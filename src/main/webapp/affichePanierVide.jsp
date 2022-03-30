<%@ page pageEncoding="UTF-8"%>
<%@ include file="enTetePage.html"%>
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
							<li class="page_item page-item-1">
								<a href="<%=response.encodeURL("./afficheRecherche.jsp")%>">Rechercher
									un article</a>
							</li>
							<li class="page_item page-item-2 current_page_item focus">
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
							<li class="page_item page-item-5 current_page_item focus">
								<a href="<%=response.encodeURL("./controlePanier.jsp")%>">Panier</a>
							</li>
						</ul>
					</div>
				</nav>
			</header>
			<div id="content" class="site-content">
				<div class="col-full">
					<header class="entry-header">
						<h1 class="entry-title">Panier</h1>
					</header><!-- .entry-header -->
					<div class="entry-content">
						<div class="woocommerce">
							<p class="cart-empty">Votre panier est vide.</p>
						</div>
					</div>
				</div><!-- .col-full -->
			</div><!-- #content -->
<%@ include file='piedDePage.html'%>

