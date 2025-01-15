package model.recommandation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.composant.Composant;
import model.utils.Database;

public class ComposantRecommande {
    
    private int idComposantRecommande;
    private String description;
    private Composant composant;
    private Date dateRecommandation;

/// Operation    
    public List<ComposantRecommande> getAll(Connection c) throws SQLException, Exception{
        List<ComposantRecommande> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT * FROM composant_recommande";
        try {
            if( c == null ){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            rs = prstm.executeQuery();

            while (rs.next()) {
                ComposantRecommande cr = new ComposantRecommande();
                cr.setIdComposantRecommande(rs.getInt("id_composant_recommande"));
                cr.setComposant(c, rs.getInt("id_composant"));
                cr.setDescription(rs.getString("description"));
                cr.setDateRecommandation(rs.getDate("date_recommandation"));
                results.add(cr);
            }

            return results;
        } catch (Exception e) {
            throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public List<ComposantRecommande> getAllByMois(Connection c, int mois, int annee) throws SQLException,Exception{
        List<ComposantRecommande> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT * FROM composant_recommande WHERE EXTRACT(MONTH FROM date_recommandation) = ? AND EXTRACT(YEAR FROM date_recommandation) = ? GROUP BY id_composant_recommande,EXTRACT(MONTH FROM date_recommandation),EXTRACT(YEAR FROM date_recommandation)";
        try {
            if( c == null ){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setInt(1,mois);
            prstm.setInt(2,annee);
            rs = prstm.executeQuery();

            while (rs.next()) {
                ComposantRecommande cr = new ComposantRecommande();
                cr.setIdComposantRecommande(rs.getInt("id_composant_recommande"));
                cr.setComposant(c, rs.getInt("id_composant"));
                cr.setDescription(rs.getString("description"));
                cr.setDateRecommandation(rs.getDate("date_recommandation"));

                results.add(cr);
            }

            return results;
        } catch (Exception e) {
            throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public static int getMinYear(Connection c) throws SQLException,Exception{
        int result = 0;
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT MIN(EXTRACT(YEAR FROM date_recommandation)) as min_annee FROM composant_recommande";
        try {
            if( c == null ){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            rs = prstm.executeQuery();

            if (rs.next()) {
                result = rs.getInt("min_annee");
            }

            if(result == 0){
                result = 1980;
            }
            return result;
        } catch (Exception e) {
            throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public void insert(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String sql = "INSERT INTO composant_recommande (date_recommandation, description, id_composant) VALUES(?, ?, ?)";
        
        try {
            if (c == null) {
                c = Database.getConnection();
                isNewConnection = true;
            }

            c.setAutoCommit(false);

            prstm = c.prepareStatement(sql);
            prstm.setDate(1, this.getDateRecommandation());
            prstm.setString(2, this.getDescription());
            prstm.setInt(3, this.getComposant().getIdComposant());

            prstm.executeUpdate();
            c.commit();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        } finally {
            Database.closeRessources(null, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

/// GETTERS AND SETTERS
    public void setIdComposantRecommande(int idComposantRecommande) {
        this.idComposantRecommande = idComposantRecommande;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdComposantRecommande() {
        return idComposantRecommande;
    }
    public String getDescription() {
        return description;
    }
    public Composant getComposant() {
        return composant;
    }
    public void setComposant(Composant composant) {
        this.composant = composant;
    }
    public void setComposant(Connection c,int composant) throws SQLException{
        this.composant = new Composant().getById(c, composant);
    }

    public Date getDateRecommandation() {
        return dateRecommandation;
    }

    public void setDateRecommandation(Date dateRecommandation) {
        this.dateRecommandation = dateRecommandation;
    }
}
