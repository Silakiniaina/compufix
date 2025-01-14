package model.technicien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Technicien {
    
    private int idTechnicien;
    private String nomTechnicien;

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
            }
            return this;
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, pr, c, Boolean.valueOf(isNewConnection));
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
}
