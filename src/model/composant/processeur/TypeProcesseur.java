package model.composant.processeur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class TypeProcesseur {
    
    private int idTypeProcesseur; 
    private String nomTypeProcesseur;

    // CRUD
    public void insert(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "INSERT INTO type_processeur(nom_type_processeur) VALUES (?)";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            prstm = c.prepareStatement(query);
            prstm.setString(1, this.getNomTypeProcesseur());
            
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

    public void update(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "UPDATE type_processeur SET nom_type_processeur = ? WHERE id_type_processeur = ?";
        
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            prstm = c.prepareStatement(query);
            prstm.setString(1, this.getNomTypeProcesseur());
            prstm.setInt(2, this.getIdTypeProcesseur());
            
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

    public void delete(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "DELETE FROM type_processeur WHERE id_type_processeur = ? ";
        
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getIdTypeProcesseur());
            
            int affectedRows = prstm.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("La suppression a échoué, aucun type RAM trouvé pour l'id " + this.getIdTypeProcesseur());
            }
            c.commit();
        } catch (SQLException e) {
            if (c != null) {
                c.rollback();
            }
            throw new SQLException("Cette type de ram est encore utiliser par des ram et ne peut pas etre supprime");
        } finally {
            Database.closeRessources(null, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public TypeProcesseur getById(Connection c, int id) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "SELECT * FROM type_processeur WHERE id_type_processeur = ?";
        ResultSet rs = null; 
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            
            rs = prstm.executeQuery();
            if (rs.next()) {
                this.setIdTypeProcesseur(rs.getInt("id_type_processeur"));
                this.setNomTypeProcesseur(rs.getString("nom_type_processeur"));
                return this;
            }
            return null;
        } finally {
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public List<TypeProcesseur> getAll(Connection c) throws SQLException {
        List<TypeProcesseur> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM type_processeur ORDER BY id_type_processeur";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            
            rs = prstm.executeQuery();
            while (rs.next()) {
                TypeProcesseur TypeProcesseur = new TypeProcesseur();
                TypeProcesseur.setIdTypeProcesseur(rs.getInt("id_type_processeur"));
                TypeProcesseur.setNomTypeProcesseur(rs.getString("nom_type_processeur"));
                results.add(TypeProcesseur);
            }
            return results;
        } finally {
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    // GETTERS AND SETTERS
    public int getIdTypeProcesseur() {
        return idTypeProcesseur;
    }
    public String getNomTypeProcesseur() {
        return nomTypeProcesseur;
    }

    public void setIdTypeProcesseur(int idTypeProcesseur) {
        this.idTypeProcesseur = idTypeProcesseur;
    }
    public void setNomTypeProcesseur(String nomTypeProcesseur) {
        this.nomTypeProcesseur = nomTypeProcesseur;
    }
}
