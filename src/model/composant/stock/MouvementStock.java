package model.composant.stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class MouvementStock {
    
    // Functions
    public List<ElementMouvementStock> getAllMouvement(Connection c, boolean est_entree)throws SQLException{
        List<ElementMouvementStock> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT * FROM mouvement_stock WHERE est_entree = ?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setBoolean(1, est_entree);

            rs = prstm.executeQuery();

            while (rs.next()) {
                ElementMouvementStock element = new ElementMouvementStock();
                element.setComposant(c, rs.getInt("id_composant"));
                element.setQuantite(rs.getDouble("quantite_composant"));
                element.setDateMouvement(rs.getDate("date_mouvement"));

                results.add(element);
            }
            return results;
        } catch (SQLException e) {
           throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }
}
