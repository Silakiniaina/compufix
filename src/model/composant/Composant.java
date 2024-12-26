package model.composant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Composant {
    private int idComposant;
    private String nomComposant; 
    private double capacite; 
    private double prixUnitaire;

    // CRUD Composant
    public void insertComposant(Connection connection) throws SQLException {
        String sql = "INSERT INTO composant (nom_composant, capacite, prix_unitaire) VALUES (?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, this.getNomComposant());
            pstmt.setDouble(2, this.getCapacite());
            pstmt.setDouble(3, this.getPrixUnitaire());
            
            pstmt.executeUpdate();
            
            // Récupération de l'ID généré
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.setIdComposant(generatedKeys.getInt(1));
                }
            }
        }
    }

    // GETTERS AND SETTERS
    public int getIdComposant() {
        return idComposant;
    }
    public String getNomComposant() {
        return nomComposant;
    }
    public double getCapacite() {
        return capacite;
    }
    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setIdComposant(int idComposant) {
        this.idComposant = idComposant;
    }
    public void setNomComposant(String nomComposant) {
        this.nomComposant = nomComposant;
    }
    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }
    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
    
}