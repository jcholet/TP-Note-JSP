/**
 * Title:        commerce
 * Description:  Class for e-commerce
 * Company:      IUT Laval - Université du Maine
 * @author  A. Corbière
 */
package commerce.catalogue.service;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import commerce.catalogue.domaine.modele.HuileMoteur;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.zeloon.deezer.client.DeezerClient;
import com.zeloon.deezer.domain.Albums;
import com.zeloon.deezer.domain.Artists;
import com.zeloon.deezer.domain.Tracks;
import com.zeloon.deezer.domain.internal.AlbumId;
import com.zeloon.deezer.domain.internal.ArtistId;
import com.zeloon.deezer.domain.internal.search.SearchArtist;

import amazon.apaIO.ApaiIO;
import amazon.apaIO.configuration.GenericConfiguration;
import amazon.apaIO.operations.Search;
import commerce.catalogue.domaine.modele.Livre;
import commerce.catalogue.domaine.modele.Musique;
import commerce.catalogue.domaine.modele.Piste;
import sun.tools.jconsole.JConsole;

public class InitAmazon {

	private CatalogueManager catalogueManager ;
	private List<Search> searchList;

	public InitAmazon(CatalogueManager manager) {
		this.catalogueManager = manager;
		this.searchList = new ArrayList<>();
		Gson gson = new Gson();
		try {
			this.searchList = gson.fromJson(new FileReader("searchData"), new TypeToken<List<Search>>(){}.getType());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void init() {
		// Lien pour obtenir la clé d'accès et la clé secrète auprès d'Amazon.
		// https://portal.aws.amazon.com/gp/aws/securityCredentials
		/*
		 * Utiliser l'un des points d'accès en fonction du type d'article/prix/...
		 *
		 *      US: ecs.amazonaws.com
		 *      CA: ecs.amazonaws.ca
		 *      UK: ecs.amazonaws.co.uk
		 *      DE: ecs.amazonaws.de
		 *      FR: ecs.amazonaws.fr
		 *      JP: ecs.amazonaws.jp
		 */
		//String ENDPOINT = "ecs.amazonaws.fr" ;
		String ENDPOINT = "odp.tuxfamily.org";
		String AWS_ACCESS_KEY_ID = "YOUR_ACCESS_KEY_ID_HERE";
		String AWS_SECRET_KEY = "YOUR_SECRET_KEY_HERE";

		GenericConfiguration conf = new GenericConfiguration();
		conf.setAccessKey(AWS_ACCESS_KEY_ID) ;
		conf.setSecretKey(AWS_SECRET_KEY);
		conf.setEndPoint(ENDPOINT);

		ApaiIO apaiIO = new ApaiIO();
		apaiIO.setConfiguration(conf);

		Livre livre;
		Musique musique ;
		HuileMoteur huileMoteur;
		Piste piste ;
		SAXBuilder builder = new SAXBuilder();
		builder.setIgnoringElementContentWhitespace(true);
		Document document ;
		Element racine = null ;
		Namespace espaceNom = null ;

		int indexName = 0;
		try {
			for(Search search : searchList){
				document = builder.build(new StringReader(apaiIO.runOperation(search)));
				racine = document.getRootElement() ;
				try {
					FileWriter writer = new FileWriter("amazonResponse"+ indexName +".xml");
					XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat()) ;
					outputter.output(racine, writer) ;
				}
				catch (IOException e) {
					e.printStackTrace() ;
				}
				indexName++;
				espaceNom = Namespace.getNamespace(racine.getNamespaceURI());

				if (espaceNom != null && !racine.getName().equals("ItemSearchErrorResponse")) {
					Element items = racine.getChild("Items",espaceNom) ;
					Iterator<Element> itemIterator = items.getChildren("Item",espaceNom).iterator() ;
					Element item ;
					Element itemAttributes ;
					Element image ;
					int i = 0 ;

					DeezerClient deezerClient = new DeezerClient();
					Artists artists = deezerClient.search(new SearchArtist(search.getKeywords())) ;
					Albums albums = deezerClient.getAlbums(new ArtistId(artists.getData().get(0).getId()));

					while (itemIterator.hasNext() && i != 5) {
						item = itemIterator.next() ;
						itemAttributes = item.getChild("ItemAttributes",espaceNom);
						image = item.getChild("LargeImage",espaceNom);
						try
						{
							if (itemAttributes.getChild("ProductGroup",espaceNom).getText().equals("Book")) {
								livre = new Livre();
								livre.setRefArticle(item.getChild("ASIN",espaceNom).getText());
								livre.setTitre(itemAttributes.getChild("Title",espaceNom).getText());
								livre.setAuteur(itemAttributes.getChild("Author",espaceNom).getText());
								livre.setISBN(itemAttributes.getChild("ISBN",espaceNom).getText());
								livre.setImage(image.getChild("URL",espaceNom).getText());
								livre.setPrix(Integer.parseInt(item.getChild("OfferSummary",espaceNom).getChild("LowestNewPrice",espaceNom).getChild("Amount",espaceNom).getText())/100.0);
								livre.setDisponibilite(1);
								catalogueManager.soumettreArticle(livre) ;
								i ++ ;
							}

							if(itemAttributes.getChild("ProductGroup", espaceNom).getText().equals("Automotive")){
								huileMoteur = new HuileMoteur();
								huileMoteur.setRefArticle(item.getChild("ASIN", espaceNom).getText());
								huileMoteur.setTitre(itemAttributes.getChild("Title", espaceNom).getText());
								huileMoteur.setImage(image.getChild("URL", espaceNom).getText());
								huileMoteur.setPrix(Integer.parseInt(item.getChild("OfferSummary", espaceNom).getChild("LowestNewPrice", espaceNom).getChild("Amount", espaceNom).getText())/100.0);
								catalogueManager.soumettreArticle(huileMoteur);
								i++;
							}

							if (itemAttributes.getChild("ProductGroup",espaceNom).getText().equals("Music")) {
								musique = new Musique();
								musique.setRefArticle(item.getChild("ASIN",espaceNom).getText());
								musique.setTitre(itemAttributes.getChild("Title",espaceNom).getText());
								musique.setImage(image.getChild("URL",espaceNom).getText());
								musique.setPrix(Integer.parseInt(item.getChild("OfferSummary",espaceNom).getChild("LowestNewPrice",espaceNom).getChild("Amount",espaceNom).getText())/100.0);
								musique.setDisponibilite(1);

								int j = 0 ;
								Boolean sortir = (j==albums.getData().size()) ;
								Boolean albumTrouve = false ;
								while(!sortir) {
									String titreDeezer = albums.getData().get(j).getTitle().toLowerCase().replaceAll(" ", "") ;
									String titreAmazon = musique.getTitre().toLowerCase().replaceAll(" ", "") ;
									titreDeezer.replaceAll("-", "") ;
									titreAmazon.replaceAll("-", "") ;
									albumTrouve = titreDeezer.equals(titreAmazon) ;
									if (titreAmazon.length() > titreDeezer.length())
										albumTrouve = albumTrouve || (titreAmazon.indexOf(titreDeezer)>=0) ;
									if (titreDeezer.length() > titreAmazon.length())
										albumTrouve = albumTrouve || (titreDeezer.indexOf(titreAmazon)>=0) ;

									j++ ;
									sortir = albumTrouve || (j==albums.getData().size()) ;
								}
								if (albumTrouve) {
									Tracks tracks = deezerClient.getTracks(new AlbumId(albums.getData().get(j-1).getId()));
									j = 0 ;
									List<Piste> listePistes = new ArrayList<Piste>() ;
									while(j<tracks.getData().size()) {
										piste = new Piste() ;
										piste.setTitre(tracks.getData().get(j).getTitle());
										piste.setUrl(tracks.getData().get(j).getPreview());
										catalogueManager.soumettrePiste(piste);
										listePistes.add(piste) ;
										j++;
									}
									if (tracks.getData().size() != 0)
										musique.setPistes(listePistes);
								}
								catalogueManager.soumettreArticle(musique) ;
								i ++ ;
							}
						}
						catch (NullPointerException e) {
							e.printStackTrace() ;
						}
						catch (Exception e) {
							e.printStackTrace() ;
						}
					}
				}
				else {
					try {
						livre = new Livre();
						livre.setRefArticle("1141555677821");
						livre.setTitre("Le seigneur des anneaux");
						livre.setAuteur("J.R.R. TOLKIEN");
						livre.setISBN("2075134049");
						livre.setImage("51O0yBHs+OL._SL500_.jpg");
						livre.setNbPages(736);
						livre.setPrix("8.90");
						livre.setDisponibilite("1");
						catalogueManager.soumettreArticle(livre);
						livre = new Livre();
						livre.setRefArticle("1141555897821");
						livre.setTitre("Un paradis trompeur");
						livre.setAuteur("Henning Mankell");
						livre.setISBN("275784797X");
						livre.setImage("41kQXtYSFPL._SL500_.jpg");
						livre.setNbPages(400);
						livre.setPrix("7.90");
						livre.setDisponibilite("1");
						catalogueManager.soumettreArticle(livre);
						livre = new Livre();
						livre.setRefArticle("1141556299459");
						livre.setTitre("Dôme tome 1");
						livre.setAuteur("Stephen King");
						livre.setISBN("2212110685");
						livre.setImage("41ICy6+-LdL._SL500_.jpg");
						livre.setNbPages(840);
						livre.setPrix("8.90");
						livre.setDisponibilite("1");
						catalogueManager.soumettreArticle(livre);
					}
					catch (Exception e) {
						e.printStackTrace() ;
					}
				}
			}
		}
		catch (JDOMException e) {
			e.printStackTrace() ;
		}
		catch (IOException e) {
			e.printStackTrace() ;
		}
	}
}
