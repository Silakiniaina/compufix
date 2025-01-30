package model.composant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class HistoriquePrixComposant {
    
    private int idHistorique;
    private Date dateModification;
    private double ancienPrix;
    private double nouveauPrix;
    private Composant composant;

    public void insert(Connection c)throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "INSERT INTO historique_prix_composant(date_modification,ancien_prix,nouveau_prix,id_composant) VALUES (?,?,?,?)";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            prstm = c.prepareStatement(query);
            prstm.setDate(1, this.getDateModification());
            prstm.setDouble(2, this.getAncienPrix());
            prstm.setDouble(3, this.getNouveauPrix());
            prstm.setInt(4, this.getComposant().getIdComposant());

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

    public List<HistoriquePrixComposant> getAllByFilter(Connection c, HistoriqueFilter filter)throws SQLException{
        boolean isNewConnection = false;
        List<HistoriquePrixComposant> historiques = new ArrayList<HistoriquePrixComposant>();
        ResultSet rs = null; 
        PreparedStatement prstm = null;
        String query  = filter != null ? filter.getQuery() : "SELECT * FROM historique_prix_composant";
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();
            while(rs.next()){
                HistoriquePrixComposant h = new HistoriquePrixComposant();
                h.setIdHistorique(rs.getInt("id_historique"));
                h.setDateModification(rs.getDate("date_modification"));
                h.setAncienPrix(rs.getDouble("ancien_prix"));
                h.setNouveauPrix(rs.getDouble("nouveau_prix"));
                h.setComposant(c, rs.getInt("id_composant"));
                historiques.add(h);
            }

            return historiques;
        } catch (Exception e) {
            throw e;
        }finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

/// GETTERS SETTERS
    public int getIdHistorique() {
        return idHistorique;
    }
    public Date getDateModification() {
        return dateModification;
    }
    public double getAncienPrix() {
        return ancienPrix;
    }
    public double getNouveauPrix() {
        return nouveauPrix;
    }
    public Composant getComposant() {
        return composant;
    }
    public void setIdHistorique(int idHistorique) {
        this.idHistorique = idHistorique;
    }
    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }
    public void setAncienPrix(double ancienPrix) {
        this.ancienPrix = ancienPrix;
    }
    public void setNouveauPrix(double nouveauPrix) {
        this.nouveauPrix = nouveauPrix;
    }
    public void setComposant(Composant composant) {
        this.composant = composant;
    } 
    public void setComposant(Connection c, int composant)throws SQLException {
        this.composant = new Composant().getById(c, composant);
    } 
}