package model.reparation;

import java.sql.Connection;
import java.sql.SQLException;

import model.composant.TypeComposant;
import model.ordinateur.TypeOrdinateur;

public class RetourFilter {
    
    private TypeOrdinateur typeOrdinateur;
    private TypeReparation typeReparation;
    private TypeComposant typeComposant;

    public RetourFilter(Connection c, String typeOrdinateur, String typeReparation, String typeComposant)throws SQLException{
        if(typeOrdinateur != null && !typeOrdinateur.trim().equals("")){
            this.setTypeOrdinateur(new TypeOrdinateur().getById(c, 0));
        }
        if(typeReparation != null && !typeReparation.trim().equals("")){
            this.setTypeReparation(new TypeReparation().getById(c, 0));
        }
        if(typeComposant != null && !typeComposant.trim().equals("")){
            this.setTypeComposant(new TypeComposant().getById(c, 0));
        }
    }

    
/// GETTERS AND SETTERS
    public TypeOrdinateur getTypeOrdinateur() {
        return typeOrdinateur;
    }
    public TypeReparation getTypeReparation() {
        return typeReparation;
    }
    public TypeComposant getTypeComposant() {
        return typeComposant;
    }

    public void setTypeOrdinateur(TypeOrdinateur typeOrdinateur) {
        this.typeOrdinateur = typeOrdinateur;
    }
    public void setTypeReparation(TypeReparation typeReparation) {
        this.typeReparation = typeReparation;
    }
    public void setTypeComposant(TypeComposant typeComposant) {
        this.typeComposant = typeComposant;
    }
    public void setTypeOrdinateur(Connection c, int typeOrdinateur)throws SQLException {
        this.typeOrdinateur = new TypeOrdinateur().getById(c, typeOrdinateur);
    }
    public void setTypeReparation(Connection c, int typeReparation)throws SQLException{
        this.typeReparation = new TypeReparation().getById(c, typeReparation);
    }
    public void setTypeComposant(Connection c, int typeComposant) throws SQLException{
        this.typeComposant = new TypeComposant().getById(c, typeComposant);
    }
}
