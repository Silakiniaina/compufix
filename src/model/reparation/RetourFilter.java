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
        this.setTypeOrdinateur(c, typeOrdinateur);
        this.setTypeReparation(c, typeReparation);
        this.setTypeComposant(c, typeComposant);
    }

/// Operations 
    public String generateQuery(){
        String sql  = "SELECT * FROM v_retour_reparation WHERE 1 = 1";
        if(this.getTypeOrdinateur() != null){
            sql += "AND id_type_ordinateur  = ?";
        }
        if(this.getTypeReparation() != null){
            sql += "AND ? = ANY(types_reparation)";
        }
        if(this.getTypeComposant() != null){
            sql += "AND ? = ANY(types_composant)";
        }
        return sql;
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
    public void setTypeOrdinateur(Connection c,String str)throws SQLException{
        if(str != null && !str.trim().equals("")){
            this.typeOrdinateur = new TypeOrdinateur().getById(c, Integer.parseInt(str));
        }
    }
    public void setTypeReparation(Connection c,String str)throws SQLException{
        if(str != null && !str.trim().equals("")){
            this.typeReparation = new TypeReparation().getById(c, Integer.parseInt(str));
        }
    }
    public void setTypeComposant(Connection c,String str)throws SQLException{
        if(str != null && !str.trim().equals("")){
            this.typeComposant = new TypeComposant().getById(c, Integer.parseInt(str));
        }
    }
}
