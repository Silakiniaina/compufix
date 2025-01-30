package model.reparation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.composant.TypeComposant;
import model.ordinateur.Ordinateur;
import model.utils.Database;

public class Reparation {
    
    private int idReparation;
    private Date dateReparation;
    private Ordinateur ordinateur;
    private List<ComposantReparation> composants;
    private boolean returned;
    private double prixTotal;

    /// Constructor
    public Reparation(){
        this.setComposants(new ArrayList<ComposantReparation>());
    }

/// CRUD Operation
    public List<Reparation> getAll(Connection c)throws SQLException{
        List<Reparation> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT DISTINCT id_reparation, date_reparation, id_ordinateur,est_retourne FROM v_reparation";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            
            rs = prstm.executeQuery();

            while(rs.next()){
                Reparation r = new Reparation();
                r.setIdReparation(rs.getInt("id_reparation"));
                r.setDateReparation(rs.getDate("date_reparation"));
                r.setOrdinateur(c, rs.getInt("id_ordinateur"));
                r.setComposants(r.getComposants(c));
                r.setReturned(rs.getBoolean("est_retourne"));
                r.getPrixTotal(c);
                results.add(r);
            }
            return results;   
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public Reparation getById(Connection c, int id)throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT DISTINCT id_reparation, date_reparation, id_ordinateur, est_retourne FROM v_reparation WHERE id_reparation = ?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setInt(1, id);
            
            rs = prstm.executeQuery();

            if(rs.next()){
                this.setIdReparation(rs.getInt("id_reparation"));
                this.setDateReparation(rs.getDate("date_reparation"));
                this.setOrdinateur(c, rs.getInt("id_ordinateur"));
                this.setReturned(rs.getBoolean("est_retourne"));
                this.setComposants(this.getComposants(c));
                this.getPrixTotal(c);
                return this;
            }

            return null;   
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public List<Reparation> getAllByTypeComposant(Connection c, int id)throws SQLException{
        List<Reparation> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT DISTINCT id_reparation, date_reparation, id_ordinateur, est_retourne FROM v_reparation WHERE id_type_composant = ?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setInt(1, id);
            
            rs = prstm.executeQuery();

            while(rs.next()){
                Reparation r = new Reparation();
                r.setIdReparation(rs.getInt("id_reparation"));
                r.setDateReparation(rs.getDate("date_reparation"));
                r.setOrdinateur(c, rs.getInt("id_ordinateur"));
                r.setReturned(rs.getBoolean("est_retourne"));
                r.setComposants(r.getComposants(c));
                r.getPrixTotal(c);
                results.add(r);
            }
            return results;   
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public List<ComposantReparation> getComposants(Connection c)throws SQLException{
        List<ComposantReparation> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT * FROM composant_reparation WHERE id_reparation = ?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setInt(1, this.getIdReparation());
            
            rs = prstm.executeQuery();

            while(rs.next()){
                ComposantReparation r = new ComposantReparation();
                r.setReparation(this);
                r.setTechnicien(c, rs.getInt("id_technicien"));
                r.setTypeReparation(c, rs.getInt("id_type_reparation"));
                r.setComposantOrdinateur(c, rs.getInt("id_composant_ordinateur"));
                results.add(r);
            }
            return results;   
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public List<TypeComposant> getTypeComposants() throws SQLException{
        if (this.getComposants() == null || this.getComposants().isEmpty()) {
            return List.of(); // Retourne une liste vide si aucun composant
        }
        // Récupère les types des this.getComposants()
        return this.getComposants().stream()
                         .map(ComposantReparation::getTypeComposant) 
                         .distinct() 
                         .collect(Collectors.toList());
    }

    public void insert(Connection c)throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        String sql = "INSERT INTO reparation(id_ordinateur,date_reparation) VALUES(?, ?)";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            
            c.setAutoCommit(false);
            
            prstm = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prstm.setInt(1, this.getOrdinateur().getIdOrdinateur());
            prstm.setDate(2, this.getDateReparation());

            prstm.executeUpdate();

            // Récupération de l'I D généré
            try (ResultSet generatedKeys = prstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.setIdReparation(generatedKeys.getInt(1));
                }
            }
            
            c.commit();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        }finally{
            Database.closeRessources(null, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public void insererComposantReparations(Connection c)throws SQLException{
        for(ComposantReparation composant : this.getComposants()){
            composant.insert(c,this);
        }
    }

    public void getPrixTotal(Connection c)throws SQLException{
        double prix = 0;
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT id_reparation,SUM(prix) AS prix_total FROM composant_reparation WHERE id_reparation = ? GROUP BY id_reparation";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setInt(1, this.getIdReparation());
            
            rs = prstm.executeQuery();

            if(rs.next()){
                this.setPrixTotal(rs.getDouble("prix_total"));
            }  
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public int getIdReparation() {
        return idReparation;
    }
    public void setIdReparation(int idReparation) {
        this.idReparation = idReparation;
    }
    public Date getDateReparation() {
        return dateReparation;
    }
    public void setDateReparation(Date dateReparation) {
        this.dateReparation = dateReparation;
    }

    public void setDateReparation(String dateReparationStr) throws Exception{
        if(dateReparationStr == null || dateReparationStr.trim().equals("")){
            throw new Exception("Le champ date est obligatoire");
        }
        this.dateReparation = Date.valueOf(LocalDate.parse(dateReparationStr));
    }

    public Ordinateur getOrdinateur() {
        return ordinateur;
    }
    public void setOrdinateur(Connection c, int ordinateur)throws SQLException {
        this.ordinateur = new Ordinateur().getById(c, ordinateur);
    }

    public void setOrdinateur(Ordinateur o)throws SQLException {
        this.ordinateur = o;
    }
    public List<ComposantReparation> getComposants() {
        return composants;
    }

    public void setComposants(List<ComposantReparation> composants) {
        this.composants = composants;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }
}