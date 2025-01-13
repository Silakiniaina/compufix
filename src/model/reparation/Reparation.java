package model.reparation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.composant.TypeComposant;
import model.ordinateur.Ordinateur;
import model.utils.Database;

public class Reparation {
    
    private int idReparation;
    private Date dateReparation;
    private Ordinateur ordinateur;
    private List<ComposantReparation> composants;

    /// CRUD Operation
    public List<Reparation> getAll(Connection c)throws SQLException{
        List<Reparation> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT DISTINCT id_reparation, date_reparation, id_ordinateur FROM v_reparation";
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
                r.setComposants(r.getComposants(c));
                results.add(r);
            }
            return results;   
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public Reparation getById(Connection c, int id)throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECTSELECT DISTINCT id_reparation, date_reparation, id_ordinateur FROM v_reparation WHERE id_reparation = ?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setInt(1, id);
            
            rs = prstm.executeQuery();

            if(rs.next()){
                this.setIdReparation(rs.getInt("id_reparation"));
                this.setDateReparation(rs.getDate("date_reparation"));
                this.setOrdinateur(c, rs.getInt("id_ordinateur"));
                this.setComposants(this.getComposants(c));
                return this;
            }

            return null;   
        } catch (SQLException e) {
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
        String sql = "SELECT DISTINCT id_reparation, date_reparation, id_ordinateur FROM v_reparation WHERE id_type_composant = ?";
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
                r.setComposants(r.getComposants(c));
                results.add(r);
            }
            return results;   
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public List<ComposantReparation> getComposants(Connection c)throws SQLException{
        List<ComposantReparation> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT * FROM composant_reparation WHERE id_reparation = ?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setInt(1, this.getIdReparation());
            
            rs = prstm.executeQuery();

            while(rs.next()){
                ComposantReparation r = new ComposantReparation();
                r.setReparation(this);
                r.setTechnicien(c, rs.getInt("id_technicien"));
                r.setTypeReparation(c, rs.getInt("id_type_reparation"));
                r.setComposantOrdinateur(c, rs.getInt("id_composant_ordinateur"));
                results.add(r);
            }
            return results;   
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public List<TypeComposant> getTypeComposants() throws SQLException{
        if (this.getComposants() == null || this.getComposants().isEmpty()) {
            return List.of(); // Retourne une liste vide si aucun composant
        }
        // Récupère les types des this.getComposants()
        return this.getComposants().stream()
                         .map(ComposantReparation::getTypeComposant) 
                         .distinct() 
                         .collect(Collectors.toList());
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

    public List<ComposantReparation> getComposants() {
        return composants;
    }

    public void setComposants(List<ComposantReparation> composants) {
        this.composants = composants;
    }
}