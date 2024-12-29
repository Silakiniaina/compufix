package model.composant.cm;

import java.sql.Connection;
import java.sql.SQLException;

import model.composant.disque.TypeDisque;
import model.composant.processeur.TypeProcesseur;
import model.composant.ram.TypeRam;

public class CarteMere {
    
    private int idCarteMere;
    private int nombreSlotRam;
    private int nombreSlotDisque;
    private TypeRam typeRam;
    private TypeProcesseur typeProcesseur;
    private TypeDisque typeDisque;


    public int getIdCarteMere() {
        return idCarteMere;
    }
    public int getNombreSlotRam() {
        return nombreSlotRam;
    }
    public int getNombreSlotDisque() {
        return nombreSlotDisque;
    }
    public TypeRam getTypeRam() {
        return typeRam;
    }
    public TypeProcesseur getTypeProcesseur() {
        return typeProcesseur;
    }
    public TypeDisque getTypeDisque() {
        return typeDisque;
    }

    public void setIdCarteMere(int idCarteMere) {
        this.idCarteMere = idCarteMere;
    }
    public void setNombreSlotRam(int nombreSlotRam) {
        this.nombreSlotRam = nombreSlotRam;
    }
    public void setNombreSlotDisque(int nombreSlotDisque) {
        this.nombreSlotDisque = nombreSlotDisque;
    }
    public void setTypeRam(Connection c, int typeRam) throws SQLException{
        this.typeRam = new TypeRam().getById(c, typeRam);
    }
    public void setTypeProcesseur(Connection c, int typeProcesseur)throws SQLException {
        this.typeProcesseur = new TypeProcesseur().getById(c, typeProcesseur);
    }
    public void setTypeDisque(Connection c, int typeDisque) throws SQLException{
        this.typeDisque = new TypeDisque().getById(c, typeDisque);
    }
}
