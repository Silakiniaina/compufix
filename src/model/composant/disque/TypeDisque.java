package model.composant.disque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class TypeDisque {
    
    private int idTypeDisque; 
    private String nomTypeDisque;

    // CRUD
    public void insert(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "INSERT INTO type_disque(nom_type_disque) VALUES (?)";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            prstm = c.prepareStatement(query);
            prstm.setString(1, this.getNomTypeDisque());
            
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
        String query = "UPDATE type_disque SET nom_type_disque = ? WHERE id_type_disque = ?";
        
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            prstm = c.prepareStatement(query);
            prstm.setString(1, this.getNomTypeDisque());
            prstm.setInt(2, this.getIdTypeDisque());
            
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
        String query = "DELETE FROM type_disque WHERE id_type_disque = ? ";
        
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getIdTypeDisque());
            
            int affectedRows = prstm.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("La suppression a échoué, aucun type RAM trouvé pour l'id " + this.getIdTypeDisque());
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

    public TypeDisque getById(Connection c, int id) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "SELECT * FROM type_disque WHERE id_type_disque = ?";
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
                this.setIdTypeDisque(rs.getInt("id_type_disque"));
                this.setNomTypeDisque(rs.getString("nom_type_disque"));
                return this;
            }
            return null;
        } finally {
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public List<TypeDisque> getAll(Connection c) throws SQLException {
        List<TypeDisque> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM type_disque ORDER BY id_type_disque";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            
            rs = prstm.executeQuery();
            while (rs.next()) {
                TypeDisque TypeDisque = new TypeDisque();
                TypeDisque.setIdTypeDisque(rs.getInt("id_type_disque"));
                TypeDisque.setNomTypeDisque(rs.getString("nom_type_disque"));
                results.add(TypeDisque);
            }
            return results;
        } finally {
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    // GETTERS AND SETTERS
    public int getIdTypeDisque() {
        return idTypeDisque;
    }
    public String getNomTypeDisque() {
        return nomTypeDisque;
    }

    public void setIdTypeDisque(int idTypeDisque) {
        this.idTypeDisque = idTypeDisque;
    }
    public void setNomTypeDisque(String nomTypeDisque) {
        this.nomTypeDisque = nomTypeDisque;
    }
}
