package model.ordinateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.composant.Composant;
import model.composant.cm.CarteMere;
import model.composant.disque.Disque;
import model.composant.processeur.Processeur;
import model.composant.ram.RAM;
import model.utils.Database;

public class ComposantOrdinateur {
    
    private Composant composant; 
    private int quantite;
    
    public List<ComposantOrdinateur> getComposantParOrdinateur(Connection c, int id) throws SQLException{
        List<ComposantOrdinateur> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT * FROM v_composant_ordinateur WHERE id_ordinateur = ?";
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
                int type = rs.getInt("id_type_composant");
                switch (type) {
                    case 2:
                        composant.setComposant(new Disque().getByIdComposant(c,rs.getInt("id_composant")));
                        break;
                    case 3:
                        composant.setComposant(new Processeur().getByIdComposant(c,rs.getInt("id_composant")));
                        break;
                    case 4:
                        composant.setComposant(new RAM().getByIdComposant(c,rs.getInt("id_composant")));
                        break;
                    case 5:
                        composant.setComposant(new CarteMere().getByIdComposant(c,rs.getInt("id_composant")));
                        break;
                    default:
                        break;
                }
                composant.setQuantite(rs.getInt("quantite"));

                results.add(composant);
            }

            return results;
        } catch (Exception e) {
            throw e;
        } finally { 
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public void insert(Connection c, Ordinateur o) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "INSERT INTO composant_ordinateur(id_composant,id_ordinateur,quantite) VALUES (?, ?, ?)";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getComposant().getIdComposant());
            prstm.setInt(2, o.getIdOrdinateur());
            prstm.setInt(3, this.getQuantite());
            
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
    public int getQuantite() {
        return quantite;
    }
    public void setComposant(Connection c, int composant)throws SQLException {
        this.composant = new Composant().getById(c, composant);
    }
    public void setComposant(Composant c){
        this.composant  = c;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
