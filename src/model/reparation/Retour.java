package model.reparation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Retour {
    
    private int idRetour;
    private Reparation reparation; 
    private double prixTotal;

/// CRUD Operation
    public List<Retour> getAllWithFilter(Connection c, RetourFilter filter) throws SQLException{
        List<Retour> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = filter != null ? filter.generateQuery() : "SELECT * FROM v_retour_reparation";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            int idArg = 1;
            if(filter.getTypeOrdinateur() != null){
                prstm.setInt( idArg, filter.getTypeOrdinateur().getIdTypeOrdinateur());
                idArg++;
            }
            if(filter.getTypeReparation() != null){
                prstm.setInt( idArg, filter.getTypeReparation().getIdTypeReparation());
                idArg++;
            }
            if(filter.getTypeComposant() != null){
                prstm.setInt( idArg, filter.getTypeComposant().getIdTypeComposant());
                idArg++;
            }

            rs = prstm.executeQuery();
            while(rs.next()){
                Retour r = new Retour(); 
                r.setIdRetour(rs.getInt("id_retour"));
                r.setReparation(c, rs.getInt("id_reparation"));
                r.setPrixTotal(rs.getDouble("prix_total"));

                results.add(r);
            }

            return results;
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

/// GETTERS AND SETTERS
    public int getIdRetour() {
        return idRetour;
    }
    public Reparation getReparation() {
        return reparation;
    }
    public double getPrixTotal() {
        return prixTotal;
    }

    public void setIdRetour(int idRetour) {
        this.idRetour = idRetour;
    }
    public void setReparation(Reparation reparation) {
        this.reparation = reparation;
    }
    public void setReparation(Connection c, int reparation) throws SQLException{
        this.reparation = new Reparation().getById(c, reparation);
    }
    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }
}
