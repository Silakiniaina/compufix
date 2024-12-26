package model.composant.ram;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
