package commerce.catalogue.domaine.modele;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name="commerce.catalogue.domaine.modele.HuileMoteur")
@DiscriminatorValue("Huile moteur")
public class HuileMoteur extends Article{
    private String marque;
    private int litrage;
    private double prixAuLitre;
    private String viscosite;

    @Basic
    public int getLitrage(){
        return litrage;
    }
    public void setLitrage(int i){
        this.litrage = i;
    }

    @Basic
    public String getMarque() {
        return marque;
    }
    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Basic
    public double getPrixAuLitre() {
        return prixAuLitre;
    }
    public void setPrixAuLitre(double prixAuLitre) {
        this.prixAuLitre = prixAuLitre;
    }

    @Basic
    public String getViscosite() {
        return viscosite;
    }
    public void setViscosite(String viscosite) {
        this.viscosite = viscosite;
    }

    public boolean equals(Object o) {
        boolean retour = false ;
        if (!(o instanceof Article))
            retour = false ;
        else {
            if (!(o instanceof HuileMoteur))
                retour = super.equals(o) ;
            else {
                HuileMoteur huileMoteur = (HuileMoteur) o ;
                if (super.equals((Article)huileMoteur)
                        &&this.getTitre().equals(huileMoteur.getTitre())
                        && this.getMarque().equals(huileMoteur.getMarque())
                        && this.getPrixAuLitre()==(huileMoteur.getPrixAuLitre())
                        && this.getLitrage()==(huileMoteur.getLitrage())
                        && this.getViscosite().equals(huileMoteur.getViscosite()))
                    retour = true ;
                else
                    retour = false ;
            }
        }
        return retour ;
    }
}
