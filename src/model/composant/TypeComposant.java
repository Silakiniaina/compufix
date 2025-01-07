package model.composant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class TypeComposant {
    
    private int idTypeComposant; 
    private String nomTypeComposant;

    // CRUD
    public void insert(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "INSERT INTO type_composant(nom_type_composant) VALUES (?)";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            prstm = c.prepareStatement(query);
            prstm.setString(1, this.getNomTypeComposant());
            
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
        String query = "UPDATE type_composant SET nom_type_composant = ? WHERE id_type_composant = ?";
        
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            prstm = c.prepareStatement(query);
            prstm.setString(1, this.getNomTypeComposant());
            prstm.setInt(2, this.getIdTypeComposant());
            
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
        String query = "DELETE FROM type_composant WHERE id_type_composant = ? ";
        
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getIdTypeComposant());
            
            int affectedRows = prstm.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("La suppression a échoué, aucun type RAM trouvé pour l'id " + this.getIdTypeComposant());
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

    public TypeComposant getById(Connection c, int id) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "SELECT * FROM type_composant WHERE id_type_composant = ?";
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
                this.setIdTypeComposant(rs.getInt("id_type_composant"));
                this.setNomTypeComposant(rs.getString("nom_type_composant"));
                return this;
            }
            return null;
        } finally {
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public List<TypeComposant> getAll(Connection c) throws SQLException {
        List<TypeComposant> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM type_composant ORDER BY id_type_composant";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            
            rs = prstm.executeQuery();
            while (rs.next()) {
                TypeComposant typeRam = new TypeComposant();
                typeRam.setIdTypeComposant(rs.getInt("id_type_composant"));
                typeRam.setNomTypeComposant(rs.getString("nom_type_composant"));
                results.add(typeRam);
            }
            return results;
        } finally {
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public boolean isCarteMere(){
        return this.getIdTypeComposant() == 5 ? true : false;
    }

    public boolean isRam(){
        return this.getIdTypeComposant() == 4 ? true : false;
    }

    public boolean isProcesseur(){
        return this.getIdTypeComposant() == 3 ? true : false;
    }

    public boolean isDisque(){
        return this.getIdTypeComposant() == 2 ? true : false;
    }
    
    // GETTERS AND SETTERS
    public int getIdTypeComposant() {
        return idTypeComposant;
    }
    public String getNomTypeComposant() {
        return nomTypeComposant;
    }

    public void setIdTypeComposant(int idTypeComposant) {
        this.idTypeComposant = idTypeComposant;
    }
    public void setNomTypeComposant(String nomTypeComposant) {
        this.nomTypeComposant = nomTypeComposant;
    }
}
