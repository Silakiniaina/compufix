package model.composant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class Composant {
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

    public void updateComposant(Connection connection) throws SQLException {
        if (this.getIdComposant() == 0) {
            throw new SQLException("Un composant avec un ID : "+this.getIdComposant()+" est inexistant");
        }

        String sql = "UPDATE composant SET nom_composant = ?, capacite = ?, prix_unitaire = ? WHERE id_composant = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, this.getNomComposant());
            pstmt.setDouble(2, this.getCapacite());
            pstmt.setDouble(3, this.getPrixUnitaire());
            pstmt.setInt(4, this.getIdComposant());
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Mise a jour echoue : le composant ID : " + this.getIdComposant() + " non trouve");
            }
        }
    }

    public void deleteComposant(Connection connection) throws SQLException {
        if (this.getIdComposant() == 0) {
            throw new SQLException("Un composant avec un ID : "+this.getIdComposant()+ " est inexistant");
        }

        String sql = "DELETE FROM composant WHERE id_composant = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, this.getIdComposant());
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Supperssion echoue: Composant avec ID " + this.getIdComposant() + " non trouve");
            }
        }
    }

    // ABSTRACT
    public abstract void insert(Connection c) throws SQLException;
    public abstract void update(Connection c) throws SQLException;
    public abstract void delete(Connection c) throws SQLException;
    public abstract List<Composant> getAll(Connection c) throws SQLException;
    public abstract Composant getById(Connection c, int id) throws SQLException;

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