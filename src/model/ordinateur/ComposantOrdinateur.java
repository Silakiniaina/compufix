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
                        
                        break;
                    case 3:
                        
                        break;
                    case 4:
                        
                        break;
                    case 5:
                        
                        break;
                    default:
                        break;
                }
                composant.setComposant(c, rs.getInt("id_composant"));
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
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
