package model.reparation;

import java.sql.Connection;
import java.sql.SQLException;

import model.composant.TypeComposant;
import model.ordinateur.ComposantOrdinateur;
import model.technicien.Technicien;

public class ComposantReparation {
    
    private Reparation reparation;
    private TypeReparation typeReparation;
    private Technicien technicien;
    private ComposantOrdinateur composantOrdinateur;

/// GETTERS AND SETTERS
    public Reparation getReparation() {
        return reparation;
    }
    public TypeReparation getTypeReparation() {
        return typeReparation;
    }
    public Technicien getTechnicien() {
        return technicien;
    }
    public ComposantOrdinateur getComposantOrdinateur() {
        return composantOrdinateur;
    }
    public TypeComposant getTypeComposant(){
        return this.getComposantOrdinateur().getComposant().getTypeComposant();
    }

    public void setReparation(Reparation reparation) {
        this.reparation = reparation;
    }
    public void setTypeReparation(TypeReparation typeReparation) {
        this.typeReparation = typeReparation;
    }
    public void setTechnicien(Technicien technicien) {
        this.technicien = technicien;
    }
    public void setComposantOrdinateur(ComposantOrdinateur composantOrdinateur) {
        this.composantOrdinateur = composantOrdinateur;
    }

    public void setReparation(Connection c, int reparation) throws SQLException{
        this.reparation = new Reparation().getById(c, reparation);
    }
    public void setTypeReparation(Connection c,int typeReparation) throws SQLException{
        this.typeReparation = new TypeReparation().getById(c, typeReparation);
    }
    public void setTechnicien(Connection c, int technicien) throws SQLException{
        this.technicien = new Technicien().getById(c, technicien);
    }
    public void setComposantOrdinateur(Connection c, int composantOrdinateur) throws SQLException{
        this.composantOrdinateur = new ComposantOrdinateur().getById(c, composantOrdinateur);
    }
}
