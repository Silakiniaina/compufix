package model.technicien;

import java.sql.Connection;
import java.sql.SQLException;

public class CommissionTechnicien {
    
    private Technicien technicien; 
    private double totalReparation;
    private double totalCommission; 
    private int nombreReparation;



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
