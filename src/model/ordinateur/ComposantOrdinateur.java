package model.ordinateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.composant.Composant;
import model.utils.Database;

public class ComposantOrdinateur {

    private int idComposantOrdinateur;
    private Composant composant; 

    public List<ComposantOrdinateur> getComposantParOrdinateur(Connection c, int id) throws SQLException{
        List<ComposantOrdinateur> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT * FROM composant_ordinateur WHERE id_ordinateur = ?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setInt(1, id);

            rs = prstm.executeQuery();
            while(rs.next()){
                ComposantOrdinateur composant = new ComposantOrdinateur();
                composant.setIdComposantOrdinateur(rs.getInt("id_composant_ordinateur"));
                composant.setComposant(new Composant().getById(c, rs.getInt("id_composant")));
                results.add(composant);
            }

            return results;
        } catch (Exception e) {
            throw e;
        } finally { 
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public ComposantOrdinateur getById(Connection c, int id) throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT * FROM composant_ordinateur WHERE id_composant_ordinateur = ?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setInt(1, id);

            rs = prstm.executeQuery();
            if(rs.next()){
               this.setIdComposantOrdinateur(rs.getInt("id_composant_ordinateur"));
               this.setComposant(new Composant().getById(c, rs.getInt("id_composant")));
                return this;
            }

            return null;
        } catch (Exception e) {
            throw e;
        } finally { 
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public void insert(Connection c, Ordinateur o) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "INSERT INTO composant_ordinateur(id_composant,id_ordinateur) VALUES (?, ?)";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getComposant().getIdComposant());
            prstm.setInt(2, o.getIdOrdinateur());
            
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
    public Composant getComposant() {
        return composant;
    }
    public void setComposant(Connection c, int composant)throws SQLException {
        this.composant = new Composant().getById(c, composant);
    }
    public void setComposant(Composant c){
        this.composant  = c;
    }
    public int getIdComposantOrdinateur() {
        return idComposantOrdinateur;
    }

    public void setIdComposantOrdinateur(int idComposantOrdinateur) {
        this.idComposantOrdinateur = idComposantOrdinateur;
    }
}
