package model.recommandation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ordinateur.Mois;
import model.utils.Database;

public class Mois {

    private int idMois;
    private String nomMois;


    public int getIdMois() {
        return idMois;
    }

    public void setIdMois(int idMois) {
        this.idMois = idMois;
    }

    public String getNomMois() {
        return nomMois;
    }

    public void setNomMois(String nomMois) {
        this.nomMois = nomMois;
    }


     public List<Mois> getAll(Connection c)throws SQLException{  
        boolean isNewConnection = false;
        PreparedStatement pr = null;
        ResultSet rs = null;
        List<Mois> result = new ArrayList<>();
        String query= "SELECT * FROM mois ";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            pr= c.prepareStatement(query);
            rs= pr.executeQuery();

            while (rs.next()) {
                Mois g = new Mois();
                g.setIdMois(rs.getInt("id_mois"));
                g.setNomMois(rs.getString("nom_mois"));
                result.add(g);

            }
            return result;
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, pr, c, Boolean.valueOf(isNewConnection));
        }
    }

    public Mois getById(Connection c,int id)throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String query= "SELECT * FROM mois WHERE id_mois=?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            pr= c.prepareStatement(query);
            pr.setInt(1, id);
            rs= pr.executeQuery();

            if(rs.next()) {
                this.setIdMois(rs.getInt("id_mois"));
                this.setNomMois(rs.getString("nom_mois"));
            }
            return this;
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, pr, c, Boolean.valueOf(isNewConnection));
        }
    }
    
}
