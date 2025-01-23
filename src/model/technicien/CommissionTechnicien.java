package model.technicien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.reparation.Retour;
import model.utils.Database;

public class CommissionTechnicien {
    
    private Technicien technicien; 
    private double totalReparation;
    private double totalCommission; 
    private int nombreReparation;

/// ACTION
    public List<CommissionTechnicien> getAllCommissions(Connection c, CommissionPeriodFilter filter)throws SQLException{
        List<CommissionTechnicien> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = filter != null ? filter.getQuery() : "SELECT t.id_technicien,COALESCE(SUM(v.prix), 0) AS total_reparation, COALESCE(SUM(v.commission_technicien), 0) AS total_commission,COALESCE(COUNT(v.id_reparation), 0) AS nombre_reparation FROM technicien t LEFT JOIN v_commission_technicien_complet v ON t.id_technicien = v.id_technicien GROUP BY t.id_technicien ORDER BY total_commission DESC";
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
            }

            rs = prstm.executeQuery();
            while(rs.next()){
                CommissionTechnicien commission = new CommissionTechnicien();
                commission.setTechnicien(c, rs.getInt("id_technicien"));
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
    public Technicien getTechnicien() {
        return technicien;
    }
    public double getTotalReparation() {
        return totalReparation;
    }
    public double getTotalCommission() {
        return totalCommission;
    }
    public int getNombreReparation() {
        return nombreReparation;
    }

    public void setTechnicien(Technicien technicien) {
        this.technicien = technicien;
    }
    public void setTechnicien(Connection c, int technicien)throws SQLException {
        this.technicien = new Technicien().getById(c, technicien);
    }
    public void setTotalReparation(double totalReparation) {
        this.totalReparation = totalReparation;
    }
    public void setTotalCommission(double totalCommission) {
        this.totalCommission = totalCommission;
    }
    public void setNombreReparation(int nombreReparation) {
        this.nombreReparation = nombreReparation;
    }
}
