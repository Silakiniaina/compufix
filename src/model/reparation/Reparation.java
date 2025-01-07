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
    private List<TypeComposant> typeComposant;

/// CRUD Operation
    public List<Reparation> getAll(Connection c)throws SQLException{
        List<Reparation> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT * FROM reparation";
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
    public List<TypeComposant> getTypeComposant() {
        return typeComposant;
    }
    public void setTypeComposant(List<TypeComposant> typeComposant) {
        this.typeComposant = typeComposant;
    }

    
    
}
