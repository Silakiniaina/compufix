package model.composant.ram;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class TypeRam {
    
    private int idTypeRam; 
    private String nomTypeRam;

    // CRUD
    public void insert(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "INSERT INTO type_ram(nom_type_ram) VALUES (?)";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            prstm = c.prepareStatement(query);
            prstm.setString(1, this.getNomTypeRam());
            
            prstm.executeUpdate();

            c.commit();
        } catch (SQLException e) {
            if (c != null) {
                c.rollback();
            }
            throw e;
        } finally {
            if (prstm != null) {
                prstm.close();
            }
            if (c != null && isNewConnection) {
                c.close();
            }
        }
    }

    public void update(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "UPDATE type_ram SET nom_type_ram = ? WHERE id_type_ram = ?";
        
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            prstm = c.prepareStatement(query);
            prstm.setString(1, this.getNomTypeRam());
            prstm.setInt(2, this.getIdTypeRam());
            
            prstm.executeUpdate();
            c.commit();
        } catch (SQLException e) {
            if (c != null) {
                c.rollback();
            }
            throw e;
        } finally {
            if (prstm != null) {
                prstm.close();
            }
            if (c != null && isNewConnection) {
                c.close();
            }
        }
    }

    public void delete(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "DELETE FROM type_ram WHERE id_type_ram = ?";
        
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getIdTypeRam());
            
            int affectedRows = prstm.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("La suppression a échoué, aucun type RAM trouvé pour l'id " + this.getIdTypeRam());
            }
            c.commit();
        } catch (SQLException e) {
            if (c != null) {
                c.rollback();
            }
            throw e;
        } finally {
            if (prstm != null) {
                prstm.close();
            }
            if (c != null && isNewConnection) {
                c.close();
            }
        }
    }

    public TypeRam getById(Connection c, int id) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "SELECT * FROM type_ram WHERE id_type_ram = ?";
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
                this.setIdTypeRam(rs.getInt("id_type_ram"));
                this.setNomTypeRam(rs.getString("nom_type_ram"));
                return this;
            }
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (prstm != null) {
                prstm.close();
            }
            if (c != null && isNewConnection) {
                c.close();
            }
        }
    }

    public List<TypeRam> getAll(Connection c) throws SQLException {
        List<TypeRam> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM type_ram ORDER BY id_type_ram";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            
            rs = prstm.executeQuery();
            while (rs.next()) {
                TypeRam typeRam = new TypeRam();
                typeRam.setIdTypeRam(rs.getInt("id_type_ram"));
                typeRam.setNomTypeRam(rs.getString("nom_type_ram"));
                results.add(typeRam);
            }
            return results;
        } finally {
            if( rs != null){
                rs.close();
            }
            if (prstm != null) {
                prstm.close();
            }
            if (c != null && isNewConnection) {
                c.close();
            }
        }
    }

    // GETTERS AND SETTERS
    public int getIdTypeRam() {
        return idTypeRam;
    }
    public String getNomTypeRam() {
        return nomTypeRam;
    }

    public void setIdTypeRam(int idTypeRam) {
        this.idTypeRam = idTypeRam;
    }
    public void setNomTypeRam(String nomTypeRam) {
        this.nomTypeRam = nomTypeRam;
    }
}
