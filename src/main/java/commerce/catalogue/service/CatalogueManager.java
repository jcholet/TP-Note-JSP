/**
 * Title:        commerce
 * Description:  Class for e-commerce
 * Company:      IUT Laval - Université du Maine
 * @author  A. Corbière
 */

package commerce.catalogue.service;

import java.util.List;

import commerce.catalogue.domaine.modele.*;
import org.hibernate.Session;
import org.hibernate.Query;

import commerce.catalogue.domaine.utilitaire.HibernateUtil;
import commerce.catalogue.domaine.utilitaire.UniqueKeyGenerator;

public class CatalogueManager {

	private List<Article> articles;
	private List<Livre> livres;
	private List<HuileMoteur> huilesMoteur;
	private List<Musique> musiques;
	
	public Article chercherArticleParRef(String inRefArticle) throws Exception {
		Article article ;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
		try {
			session.beginTransaction();
			article = (Article) session.get(Article.class, inRefArticle) ;
			session.getTransaction().commit();
		}
		catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw e; 
		}
		return (article) ;
	}
	public void supprimerArticleParRef(String inRefArticle) throws Exception {
		Article article ;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
		try {
			session.beginTransaction();
			article = (Article)session.get(Article.class, inRefArticle) ;
			session.delete(article) ;
			session.getTransaction().commit();
		}
		catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw e; 
		}
	}
	public void soumettreArticle(Article inArticle) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
		try {
			session.beginTransaction();
			if (inArticle.getRefArticle() == null) {
				inArticle.setRefArticle(new UniqueKeyGenerator().getUniqueId()) ;
				session.save(inArticle) ;
			}
			else {
				session.saveOrUpdate(inArticle) ;
			}
			session.getTransaction().commit();
		}
		catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw e; 
		}
	}
	public void soumettrePiste(Piste inPiste) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
		try {
			session.beginTransaction();
			if (inPiste.getRefPiste() == null) {
				inPiste.setRefPiste(new UniqueKeyGenerator().getUniqueId()) ;
				session.save(inPiste) ;
			}
			else {
				session.saveOrUpdate(inPiste) ;
			}
			session.getTransaction().commit();
		}
		catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw e; 
		}
	}
	public void setArticles(List inArticles) throws Exception {
		articles = inArticles;
	}
	public List getArticles() throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from commerce.catalogue.domaine.modele.Article") ;
			articles = query.list() ;
			session.getTransaction().commit();
		}
		catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw e; 
		}
		return articles ;
	}

	public List getLivres() throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from commerce.catalogue.domaine.modele.Livre") ;
			livres = query.list() ;
			session.getTransaction().commit();
		}
		catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw e;
		}
		return livres ;
	}

	public List getHuilesMoteur() throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from commerce.catalogue.domaine.modele.HuileMoteur") ;
			huilesMoteur = query.list() ;
			session.getTransaction().commit();
		}
		catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw e;
		}
		return huilesMoteur ;
	}

	public List getMusiques() throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from commerce.catalogue.domaine.modele.Musique") ;
			musiques = query.list() ;
			session.getTransaction().commit();
		}
		catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw e;
		}
		return musiques;
	}

	public List getMusiques(String name) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from commerce.catalogue.domaine.modele.Musique") ;
			musiques = query.list() ;
			musiques.removeIf(item ->
					!(item.getTitre().replace(" ", "").replace("-", "")
							.equalsIgnoreCase(name.replace(" ", "").replace("-", ""))));
			session.getTransaction().commit();
		}
		catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw e;
		}
		return musiques;
	}

	public List getHuilesMoteur(String name) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from commerce.catalogue.domaine.modele.HuileMoteur") ;
			huilesMoteur = query.list() ;
			huilesMoteur.removeIf(item ->
					!(item.getTitre().replace(" ", "").replace("-", "")
							.equalsIgnoreCase(name.replace(" ", "").replace("-", ""))));
			session.getTransaction().commit();
		}
		catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw e;
		}
		return huilesMoteur ;
	}

	public List getLivres(String name) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from commerce.catalogue.domaine.modele.Livre") ;
			livres = query.list();
			livres.removeIf(item ->
					!(item.getTitre().replace(" ", "").replace("-", "")
							.equalsIgnoreCase(name.replace(" ", "").replace("-", ""))));
			session.getTransaction().commit();
		}
		catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw e;
		}
		return livres ;
	}

	public List getArticles(String name) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from commerce.catalogue.domaine.modele.Article") ;
			articles = query.list() ;
			articles.removeIf(item ->
					!(item.getTitre().replace(" ", "").replace("-", "")
							.equalsIgnoreCase(name.replace(" ", "").replace("-", ""))));
			session.getTransaction().commit();
		}
		catch (RuntimeException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw e;
		}
		return articles;
	}
}