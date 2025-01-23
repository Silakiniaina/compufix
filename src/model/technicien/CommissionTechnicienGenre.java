package model.technicien;

import java.sql.Connection;
import java.sql.SQLException;

public class CommissionTechnicienGenre {
    
    private Genre genre;
    private int nombreReparation;
    private double totalReparation;
    private double totalCommission;

/// SETTERS

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
