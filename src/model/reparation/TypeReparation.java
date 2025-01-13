package model.reparation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.utils.Database;

public class TypeReparation {
    private int idTypeReparation;
    private String nomTypeReparation;


    public int getIdTypeReparation() {
        return idTypeReparation;
    }

    public void setIdTypeReparation(int idTypeReparation) {
        this.idTypeReparation = idTypeReparation;
    }

    public String getNomTypeReparation() {
        return nomTypeReparation;
    }

    public void setNomTypeReparation(String nomTypeReparation) {
        this.nomTypeReparation = nomTypeReparation;
    }


     public List<TypeReparation> getAll(Connection c)throws SQLException{  
        boolean isNewConnection = false;
        PreparedStatement pr = null;
        ResultSet rs = null;
        List<TypeReparation> result = new ArrayList<>();
        String query= "SELECT * FROM type_reparation ";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            pr= c.prepareStatement(query);
            rs= pr.executeQuery();

            while (rs.next()) {
                TypeReparation g = new TypeReparation();
                g.setIdTypeReparation(rs.getInt("id_type_reparation"));
                g.setNomTypeReparation(rs.getString("nom_type_reparation"));
                result.add(g);

            }
            return result;
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, pr, c, Boolean.valueOf(isNewConnection));
        }
    }

    public TypeReparation getById(Connection c,int id)throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String query= "SELECT * FROM type_reparation WHERE id_type_reparation=?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            pr= c.prepareStatement(query);
            pr.setInt(1, id);
            rs= pr.executeQuery();

            if(rs.next()) {
                this.setIdTypeReparation(rs.getInt("id_type_reparation"));
                this.setNomTypeReparation(rs.getString("nom_type_reparation"));
            }
            return this;
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, pr, c, Boolean.valueOf(isNewConnection));
        }
    }
}
