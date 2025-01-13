package model.reparation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.composant.TypeComposant;
import model.ordinateur.Ordinateur;
import model.utils.Database;

public class Reparation {
    
    private int idReparation;
    private Date dateReparation;
    private Ordinateur ordinateur;
    // private List<TypeComposant> typeComposant;
    private TypeComposant typeComposant;

/// CRUD Operation
    public List<Reparation> getAll(Connection c)throws SQLException{
        List<Reparation> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT * FROM v_filtre_reparation";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            
            rs = prstm.executeQuery();

            while(rs.next()){
                Reparation r = new Reparation();
                r.setIdReparation(rs.getInt("id_reparation"));
                r.setDateReparation(rs.getDate("date_reparation"));
                r.setOrdinateur(c, rs.getInt("id_ordinateur"));
                r.setTypeComposant(c, rs.getInt("id_type_composant"));
                results.add(r);
            }
            return results;   
        } catch (Exception e) {
            throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public List<Reparation> getAllByTypeComposant(Connection c, int id)throws SQLException{
        List<Reparation> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT * FROM v_filtre_reparation WHERE id_type_composant = ?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setInt(1, id);
            
            rs = prstm.executeQuery();

            while(rs.next()){
                Reparation r = new Reparation();
                r.setIdReparation(rs.getInt("id_reparation"));
                r.setDateReparation(rs.getDate("date_reparation"));
                r.setOrdinateur(c, rs.getInt("id_ordinateur"));
                r.setTypeComposant(c, rs.getInt("id_type_composant"));
                results.add(r);
            }
            return results;   
        } catch (Exception e) {
            throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public int getIdReparation() {
        return idReparation;
    }
    public void setIdReparation(int idReparation) {
        this.idReparation = idReparation;
    }
    public Date getDateReparation() {
        return dateReparation;
    }
    public void setDateReparation(Date dateReparation) {
        this.dateReparation = dateReparation;
    }
    public Ordinateur getOrdinateur() {
        return ordinateur;
    }
    public void setOrdinateur(Connection c, int ordinateur)throws SQLException {
        this.ordinateur = new Ordinateur().getById(c, ordinateur);
    }
    public TypeComposant getTypeComposant() {
        return typeComposant;
    }
    public void setTypeComposant(Connection c, int typeComposant) throws SQLException{
        this.typeComposant = new TypeComposant().getById(c, typeComposant);
    }

    
    
}
