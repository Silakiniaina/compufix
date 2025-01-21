package model.reparation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Retour {
    
    private int idRetour;
    private Date dateRetour;
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
            if(filter.getDateRetour() != null){
                prstm.setDate(idArg, filter.getDateRetour());
                idArg++;
            }

            rs = prstm.executeQuery();
            while(rs.next()){
                Retour r = new Retour(); 
                r.setIdRetour(rs.getInt("id_retour_reparation"));
                r.setReparation(c, rs.getInt("id_reparation"));
                r.setPrixTotal(rs.getDouble("prix_total"));
                r.setDateRetour(rs.getDate("date_retour"));

                results.add(r);
            }

            return results;
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public void insert(Connection c)throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String sql = "INSERT INTO retour_reparation (id_reparation, date_retour, prix_total) VALUES(?, ?, ?)";
        try {
            if ( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            c.setAutoCommit(false);

            prstm = c.prepareStatement(sql);
            prstm.setInt(1, this.getReparation().getIdReparation());
            prstm.setDate(2, this.getDateRetour());
            prstm.setDouble(3, this.getPrixTotal());

            prstm.executeUpdate();
            c.commit();
        } catch (SQLException e) {
           c.rollback();
           throw e;
        } finally{
            Database.closeRessources(null, prstm, c, Boolean.valueOf(isNewConnection));
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
    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
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
