package model.composant.processeur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.composant.Composant;
import model.utils.Database;

public class Processeur extends Composant{
    
    private int idProcesseur; 
    private int generation;
    private int nombreCoeur;
    private TypeProcesseur typeProcesseur;

    
    @Override
    public List<Composant> getAll(Connection c) throws SQLException {
        List<Composant> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM processeur, composant WHERE processeur.id_composant = composant.id_composant ORDER BY id_processeur";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            
            rs = prstm.executeQuery();
            while (rs.next()) {
                Processeur r = new Processeur();
                r.setIdProcesseur(rs.getInt("id_processeur"));
                r.setTypeProcesseur(c, rs.getInt("id_type_processeur"));
                r.setIdComposant(rs.getInt("id_composant"));
                r.setNomComposant(rs.getString("nom_composant"));
                r.setGeneration(rs.getInt("generation"));
                r.setNombreCoeur(rs.getInt("nombre_coeur"));
                r.setCapacite(rs.getDouble("capacite"));
                r.setPrixUnitaire(rs.getDouble("prix_unitaire"));
                results.add(r);
            }
            return results;
        } finally {
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    @Override
    public Composant getById(Connection c, int id) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM processeur, composant WHERE processeur.id_composant = composant.id_composant AND id_processeur = ?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            
            rs = prstm.executeQuery();
            if (rs.next()) {
                this.setIdProcesseur(rs.getInt("id_processeur"));
                this.setTypeProcesseur(c, rs.getInt("id_type_processeur"));
                this.setIdComposant(rs.getInt("id_composant"));
                this.setNomComposant(rs.getString("nom_composant"));
                this.setGeneration(rs.getInt("generation"));
                this.setNombreCoeur(rs.getInt("nombre_coeur"));
                this.setCapacite(rs.getDouble("capacite"));
                this.setPrixUnitaire(rs.getDouble("prix_unitaire"));
                return this;
            }
            return null;
        } finally {
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    @Override
    public void delete(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "DELETE FROM processeur WHERE id_processeur = ? ";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getIdProcesseur());
            
            prstm.executeUpdate();

            this.deleteComposant(c);

            c.commit();
        } catch (SQLException e) {
            if (c != null) {
                c.rollback();
            }
            throw e;
        } finally {
           Database.closeRessources(null, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    @Override
    public void insert(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "INSERT INTO processeur(generation, nombre_coeur,id_type_processeur, id_composant) VALUES (?, ?, ?, ?)";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);

            this.insertComposant(c);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getGeneration());
            prstm.setInt(2, this.getNombreCoeur());
            prstm.setInt(3, this.getTypeProcesseur().getIdTypeProcesseur());
            prstm.setInt(4, this.getIdComposant());
            
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
    }

    @Override
    public void update(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "UPDATE processeur SET generation = ?, nombre_coeur = ?, id_composant = ?, id_type_processeur = ?  WHERE id_processeur = ?";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            
            this.updateComposant(c);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getGeneration());
            prstm.setInt(2, this.getNombreCoeur());
            prstm.setInt(3, this.getIdComposant());
            prstm.setInt(4, this.getTypeProcesseur().getIdTypeProcesseur());
            prstm.setInt(5, this.getIdProcesseur());
            
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
    }

    // GETTERS AND SETTERS
    public int getIdProcesseur() {
        return idProcesseur;
    }
    public TypeProcesseur getTypeProcesseur(){
        return this.typeProcesseur;
    }

    public void setTypeProcesseur(Connection c, int id)throws SQLException{
        this.typeProcesseur = new TypeProcesseur().getById(c, id);
    }
    public void setIdProcesseur(int idprocesseur) {
        this.idProcesseur = idprocesseur;
    }

    public int getGeneration() {
        return generation;
    }

    public int getNombreCoeur() {
        return nombreCoeur;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public void setNombreCoeur(int nombreCoeur) {
        this.nombreCoeur = nombreCoeur;
    }
}
;