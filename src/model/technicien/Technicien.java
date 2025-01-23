package model.technicien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Technicien {
    
    private int idTechnicien;
    private String nomTechnicien;
    private Genre genre;

    /// CRUD Operations
    public List<Technicien> getAll(Connection c)throws SQLException{  
        boolean isNewConnection = false;
        PreparedStatement pr = null;
        ResultSet rs = null;
        List<Technicien> result = new ArrayList<>();
        String query= "SELECT * FROM technicien ";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            pr= c.prepareStatement(query);
            rs= pr.executeQuery();

            while (rs.next()) {
                Technicien g = new Technicien();
                g.setIdTechnicien(rs.getInt("id_technicien"));
                g.setNomTechnicien(rs.getString("nom_technicien"));
                g.setGenre(c, rs.getInt("id_genre"));
                result.add(g);

            }
            return result;
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, pr, c, Boolean.valueOf(isNewConnection));
        }
    }

    public Technicien getById(Connection c,int id)throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String query= "SELECT * FROM technicien WHERE id_technicien=?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            pr= c.prepareStatement(query);
            pr.setInt(1, id);
            rs= pr.executeQuery();

            if(rs.next()) {
                this.setIdTechnicien(rs.getInt("id_technicien"));
                this.setNomTechnicien(rs.getString("nom_technicien"));
                this.setGenre(c, rs.getInt("id_genre"));
            }
            return this;
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, pr, c, Boolean.valueOf(isNewConnection));
        }
    }

    public void insert(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String sql = "INSERT INTO technicien (nom_technicien, id_genre) VALUES (?, ?)";

        try {
            // Check if the connection is provided
            if (c == null) {
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false); 
            prstm = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prstm.setString(1, this.getNomTechnicien());
            prstm.setInt(2, this.getGenre().getIdGenre());

            prstm.executeUpdate();

            try (ResultSet generatedKeys = prstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.setIdTechnicien(generatedKeys.getInt(1)); 
                }
            }

            c.commit();
        } catch (SQLException e) {
            if (c != null) {
                c.rollback(); 
            }
            throw e; 
        } finally {
            // Close resources
            Database.closeRessources(null, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }
    
    public void delete(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String sql = "DELETE FROM technicien WHERE id_technicien = ?";
    
        try {
            // Check if the connection is provided
            if (c == null) {
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false); // Start transaction
    
            // Prepare the SQL statement
            prstm = c.prepareStatement(sql);
            prstm.setInt(1, this.getIdTechnicien());
    
            // Execute the query
            prstm.executeUpdate();
    
            c.commit(); // Commit the transaction
        } catch (SQLException e) {
            if (c != null) {
                c.rollback(); // Rollback in case of an error
            }
            throw e; // Re-throw the exception
        } finally {
            // Close resources
            Database.closeRessources(null, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public void update(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String sql = "UPDATE technicien SET nom_technicien = ?, id_genre = ? WHERE id_technicien = ?";
    
        try {
            // Check if the connection is provided
            if (c == null) {
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false); // Start transaction
    
            // Prepare the SQL statement
            prstm = c.prepareStatement(sql);
            prstm.setString(1, this.getNomTechnicien());
            prstm.setInt(2, this.getGenre().getIdGenre());
            prstm.setInt(3, this.getIdTechnicien());
    
            // Execute the query
            prstm.executeUpdate();
    
            c.commit(); // Commit the transaction
        } catch (SQLException e) {
            if (c != null) {
                c.rollback(); // Rollback in case of an error
            }
            throw e; // Re-throw the exception
        } finally {
            // Close resources
            Database.closeRessources(null, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }
    
    public int getIdTechnicien() {
        return idTechnicien;
    }
    public void setIdTechnicien(int idTechnicien) {
        this.idTechnicien = idTechnicien;
    }
    public String getNomTechnicien() {
        return nomTechnicien;
    }
    public void setNomTechnicien(String nomTechnicien) {
        this.nomTechnicien = nomTechnicien;
    }
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public void setGenre(Connection c, int genre)throws SQLException {
        this.genre = new Genre().getById(c, genre);
    }
}
