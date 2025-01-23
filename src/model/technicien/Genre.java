package model.technicien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Genre {
    
    private int idGenre;
    private String nomGenre;

/// CRUD Operations

    public List<Genre> getAll()throws Exception{
        Connection c =null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        List<Genre> result = new ArrayList<>();
        String query= "SELECT * FROM Genre ";
        try {
            c = Database.getConnection();
            pr= c.prepareStatement(query);
            rs= pr.executeQuery();

            while (rs.next()) {
                Genre g = new Genre();
                g.setIdGenre(rs.getInt("id_genre"));
                g.setNomGenre(rs.getString("label"));
                result.add(g);

            }
            return result;
        } catch (Exception e) {
            throw e;
        } finally{
            if (rs != null) {
                rs.close();
            }
            if (pr != null) {
                pr.close();
            }
            if (c != null) {
                c.close();
            }
        }
    }

    public Genre getById(int id)throws Exception{
        Connection c =null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String query= "SELECT * FROM Genre WHERE id_genre=?";
        try {
            c = Database.getConnection();
            pr= c.prepareStatement(query);
            pr.setInt(1, id);
            rs= pr.executeQuery();

            if(rs.next()) {
                this.setIdGenre(rs.getInt("id_genre"));
                this.setNomGenre(rs.getString("label"));
            }
            return this;
        } catch (Exception e) {
            throw e;
        } finally{
            if (rs != null) {
                rs.close();
            }
            if (pr != null) {
                pr.close();
            }
            if (c != null) {
                c.close();
            }
        }
    }

    public void insert() throws Exception{
        Connection c =null;
        PreparedStatement pr = null;
        String sql= "INSERT INTO genre(label) VALUES (?) ";
        try {
            c = Database.getConnection();
            c.setAutoCommit(false);
            pr= c.prepareStatement(sql);
            pr.setString(1, this.getNomGenre());
            pr.executeUpdate();
            c.commit();
        } catch (Exception e) {
            c.rollback();
            throw e;
        }finally{
            if (pr != null) {
                pr.close();
            }
            if (c != null) {
                c.close();
            }
        }
    }

    public void delete() throws Exception{
        Connection c =null;
        PreparedStatement pr = null;
        String sql= "DELETE FROM  genre WHERE  id_genre=? ";
        try {
            c = Database.getConnection();
            c.setAutoCommit(false);
            pr= c.prepareStatement(sql);
            pr.setInt(1, this.getIdGenre());
            pr.executeUpdate();
            c.commit();
        } catch (Exception e) {
            c.rollback();
            throw e;
        }finally{
            if (pr != null) {
                pr.close();
            }
            if (c != null) {
                c.close();
            }
        }
    }



    public void update(Genre g) throws Exception{
        Connection c =null;
        PreparedStatement pr = null;
        String sql= "UPDATE genre set label = ? WHERE id_genre = ? ";
        try {
            c = Database.getConnection();
            c.setAutoCommit(false);
            pr= c.prepareStatement(sql);
            pr.setString(1, g.getNomGenre());
            pr.setInt(2, this.getIdGenre());
            pr.executeUpdate();
            c.commit();
        } catch (Exception e) {
            c.rollback();
            throw e;
        }finally{
            if (pr != null) {
                pr.close();
            }
            if (c != null) {
                c.close();
            }
        }
    }
/// GETTERS AND SETTERS
    public int getIdGenre() {
        return idGenre;
    }
    public String getNomGenre() {
        return nomGenre;
    }
    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }
    public void setNomGenre(String nomGenre) {
        this.nomGenre = nomGenre;
    }
    
}
