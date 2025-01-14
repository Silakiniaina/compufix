package model.ordinateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class TypeOrdinateur {
    private int idTypeOrdinateur;
    private String nomTypeOrdinateur;


    public int getIdTypeOrdinateur() {
        return idTypeOrdinateur;
    }

    public void setIdTypeOrdinateur(int idTypeOrdinateur) {
        this.idTypeOrdinateur = idTypeOrdinateur;
    }

    public String getNomTypeOrdinateur() {
        return nomTypeOrdinateur;
    }

    public void setNomTypeOrdinateur(String nomTypeOrdinateur) {
        this.nomTypeOrdinateur = nomTypeOrdinateur;
    }


     public List<TypeOrdinateur> getAll(Connection c)throws SQLException{  
        boolean isNewConnection = false;
        PreparedStatement pr = null;
        ResultSet rs = null;
        List<TypeOrdinateur> result = new ArrayList<>();
        String query= "SELECT * FROM type_ordinateur ";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            pr= c.prepareStatement(query);
            rs= pr.executeQuery();

            while (rs.next()) {
                TypeOrdinateur g = new TypeOrdinateur();
                g.setIdTypeOrdinateur(rs.getInt("id_type_ordinateur"));
                g.setNomTypeOrdinateur(rs.getString("nom_type_ordinateur"));
                result.add(g);

            }
            return result;
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, pr, c, Boolean.valueOf(isNewConnection));
        }
    }

    public TypeOrdinateur getById(Connection c,int id)throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String query= "SELECT * FROM type_ordinateur WHERE id_type_ordinateur=?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            pr= c.prepareStatement(query);
            pr.setInt(1, id);
            rs= pr.executeQuery();

            if(rs.next()) {
                this.setIdTypeOrdinateur(rs.getInt("id_type_ordinateur"));
                this.setNomTypeOrdinateur(rs.getString("nom_type_ordinateur"));
            }
            return this;
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, pr, c, Boolean.valueOf(isNewConnection));
        }
    }
}