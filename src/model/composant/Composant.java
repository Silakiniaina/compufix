package model.composant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.utils.Database;

public class Composant {
    private int idComposant;
    private String nomComposant;
    private double capacite;
    private double prixUnitaire;

    // CRUD Composant
    public void insert(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String sql = "INSERT INTO composant (nom_composant, capacite, prix_unitaire) VALUES (?, ?, ?)";
        
        try{
            
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prstm.setString(1, this.getNomComposant());
            prstm.setDouble(2, this.getCapacite());
            prstm.setDouble(3, this.getPrixUnitaire());
            
            prstm.executeUpdate();
            
            // Récupération de l'I D généré
            try (ResultSet generatedKeys = prstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.setIdComposant(generatedKeys.getInt(1));
                }
            }

            c.commit();
        }catch(SQLException e){
            c.rollback();
            throw e;
        }finally{
            Database.closeRessources(null, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public void update(Connection c) throws SQLException{
        if (this.getIdComposant() == 0) {
            throw new SQLException("Un composant avec un ID : " + this.getIdComposant() + " est inexistant");
        }

        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String sql = "UPDATE composant SET nom_composant = ?, capacite = ?, prix_unitaire = ? WHERE id_composant = ?";

        try{
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(sql);
            prstm.setString(1, this.getNomComposant());
            prstm.setDouble(2, this.getCapacite());
            prstm.setDouble(3, this.getPrixUnitaire());
            prstm.setInt(4, this.getIdComposant());

            int rowsAffected = prstm.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException(
                        "Mise a jour echoue : le composant ID : " + this.getIdComposant() + " non trouve");
            }
        }catch(SQLException e){
            c.rollback();
            throw e;
        }finally{
            Database.closeRessources(null, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public void delete(Connection c) throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "DELETE FROM composant WHERE id_composant = ? ";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getIdComposant());
            
            prstm.executeUpdate();

            c.commit();
        } catch (SQLException e) {
            if (c != null) {
                c.rollback();
            }
            throw e;
        } finally {
           Database.closeRessources(null, prstm, c, Boolean.valueOf(isNewConnection));
        }
    };

    public List<Composant> getAll(Connection c) throws SQLException{
        List<Composant> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM composant";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            
            rs = prstm.executeQuery();
            while (rs.next()) {
                Composant r = new Composant();
                r.setIdComposant(rs.getInt("id_composant"));
                r.setNomComposant(rs.getString("nom_composant"));
                r.setCapacite(rs.getDouble("capacite"));
                r.setPrixUnitaire(rs.getDouble("prix_unitaire"));
                results.add(r);
            }
            return results;
        } finally {
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public Composant getById(Connection c, int id) throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM composant WHERE id_composant = ?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            
            rs = prstm.executeQuery();
            if (rs.next()) {
                this.setIdComposant(rs.getInt("id_composant"));
                this.setNomComposant(rs.getString("nom_composant"));
                this.setCapacite(rs.getDouble("capacite"));
                this.setPrixUnitaire(rs.getDouble("prix_unitaire"));
                return this;
            }
            return null;
        } finally {
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public int getType(){
        return Composant.COMPOSANT_MERE;
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

    public static int COMPOSANT_MERE = 0;
    public static int COMPOSANT_RAM = 1;
    public static int COMPOSANT_CARTE_MERE = 2;
    public static int COMPOSANT_PROCESSEUR = 3;
    public static int COMPOSANT_DISQUE = 4;
}