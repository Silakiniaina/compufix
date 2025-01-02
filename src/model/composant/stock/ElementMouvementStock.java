package model.composant.stock;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import model.composant.Composant;

public class ElementMouvementStock {
    
    private Composant composant; 
    private double quantite;
    private Date dateMouvement;

    // GETTERS AND SETTERS
    public Composant getComposant() {
        return composant;
    }
    public double getQuantite() {
        return quantite;
    }
    public Date getDateMouvement() {
        return dateMouvement;
    }
    public void setComposant(Connection c, int idComposant)throws SQLException {
        this.composant = new Composant().getById(c, idComposant);
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    public void setDateMouvement(Date dateMouvement) {
        this.dateMouvement = dateMouvement;
    }
}
