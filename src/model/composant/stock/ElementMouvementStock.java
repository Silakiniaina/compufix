package model.composant.stock;

import java.sql.Date;

public class ElementMouvementStock {
    
    private int idComposant; 
    private double quantite;
    private Date dateMouvement;

    // GETTERS AND SETTERS
    public int getIdComposant() {
        return idComposant;
    }
    public double getQuantite() {
        return quantite;
    }
    public Date getDateMouvement() {
        return dateMouvement;
    }
    public void setIdComposant(int idComposant) {
        this.idComposant = idComposant;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    public void setDateMouvement(Date dateMouvement) {
        this.dateMouvement = dateMouvement;
    }
}
