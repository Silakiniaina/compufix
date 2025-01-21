package model.reparation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.composant.TypeComposant;
import model.ordinateur.ComposantOrdinateur;
import model.ordinateur.Ordinateur;
import model.technicien.Technicien;
import model.utils.Database;

public class ComposantReparation {
    
    private Reparation reparation;
    private TypeReparation typeReparation;
    private Technicien technicien;
    private ComposantOrdinateur composantOrdinateur;

    public void insert(Connection c, Reparation r) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "INSERT INTO composant_reparation(id_reparation,id_technicien,id_type_reparation, id_composant_ordinateur) VALUES (?, ?, ?, ?)";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, r.getIdReparation());
            prstm.setInt(2, this.getTechnicien().getIdTechnicien());
            prstm.setInt(3, this.getTypeReparation().getIdTypeReparation());
            prstm.setInt(4, this.getComposantOrdinateur().getIdComposantOrdinateur());
            
            prstm.executeUpdate();

            c.commit();
        } catch (SQLException e) {
            if (c != null) {
                c.rollback();
            }
            throw e;
        } finally {
           Database.closeRessources(null, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

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
