package model.ordinateur;

import java.sql.Connection;
import java.sql.SQLException;

import model.composant.Composant;

public class ComposantOrdinateur {
    
    private Composant composant; 
    private int quantite;
    
    
    public Composant getComposant() {
        return composant;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setComposant(Connection c, int composant)throws SQLException {
        this.composant = new Composant().getById(c, composant);
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
