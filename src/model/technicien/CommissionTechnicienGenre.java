package model.technicien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class CommissionTechnicienGenre {
    
    private Genre genre;
    private int nombreReparation;
    private double totalReparation;
    private double totalCommission;

/// CRUD Operation

    public List<CommissionTechnicienGenre> getAllCommissionsGenre(Connection c, CommissionGenreFilter filter)throws SQLException{
        List<CommissionTechnicienGenre> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = filter != null ? filter.getQuery() : "SELECT * FROM v_commission_technicien_genre";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            int idArg = 1;
            if(filter != null){
                if(filter.getDebut() != null){
                    prstm.setDate( idArg, filter.getDebut());
                    idArg++;
                }
                if(filter.getFin() != null){
                    prstm.setDate( idArg, filter.getFin());
                    idArg++;
                }
                if(filter.getGenre() != null){
                    prstm.setInt(idArg, filter.getGenre().getIdGenre());
                    idArg++;
                }
            }

            rs = prstm.executeQuery();
            while(rs.next()){
                CommissionTechnicienGenre commission = new CommissionTechnicienGenre();
                commission.setGenre(c, rs.getInt("id_genre"));
                commission.setTotalReparation(rs.getDouble("total_reparation"));
                commission.setTotalCommission(rs.getDouble("total_commission"));
                commission.setNombreReparation(rs.getInt("nombre_reparation"));

                results.add(commission);
            }

            return results;
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

/// GETTERS AND SETTERS
    public Genre getGenre() {
        return genre;
    }
    public int getNombreReparation() {
        return nombreReparation;
    }
    public double getTotalReparation() {
        return totalReparation;
    }
    public double getTotalCommission() {
        return totalCommission;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public void setGenre(Connection c, int genre)throws SQLException {
        this.genre = new Genre().getById(c, genre);
    }
    
    public void setNombreReparation(int nombreReparation) {
        this.nombreReparation = nombreReparation;
    }
    public void setTotalReparation(double totalReparation) {
        this.totalReparation = totalReparation;
    }
    public void setTotalCommission(double totalCommission) {
        this.totalCommission = totalCommission;
    }


}
