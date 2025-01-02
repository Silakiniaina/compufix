package model.composant.stock;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Stock {
    
    public List<ElementStock> getEtatStock(Connection c, Date d) throws SQLException{
        List<ElementStock> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM v_etat_stock WHERE id_composant IN (SELECT DISTINCT id_composant FROM mouvement_stock WHERE date_mouvement <= ?)";
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            prstm.setDate(1, d);

            rs = prstm.executeQuery();
            while(rs.next()){
                ElementStock st = new ElementStock();
                st.setComposant(c, rs.getInt("id_composant"));
                st.setTotal(rs.getDouble("total"));
                st.setUtilise(rs.getDouble("utilise"));
                st.setRestant(rs.getDouble("restant"));

                results.add(st);
            }

            return results;
        } catch (SQLException e) {
            throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    } 

    public void addMouvement(Connection c, ElementMouvementStock element, boolean est_entree) throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "INSERT INTO mouvement_stock(date_mouvement, id_composant, quantite_composant, est_entree) VALUES (?, ?, ?, ?)";
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setDate(1, element.getDateMouvement());
            prstm.setInt(2, element.getComposant().getIdComposant());
            prstm.setDouble(3, element.getQuantite());
            prstm.setBoolean(4, est_entree);

            prstm.executeUpdate();

            c.commit();
        } catch (Exception e) {
            c.rollback();
            throw e;
        }finally{
            Database.closeRessources(null, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }
}
