package model.composant.stock;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class ElementStock {
    
    private int idComposant;
    private String nomComposant;
    private double total; 
    private double utilise;
    private double restant;

    // etat stock 
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
                st.setIdComposant(rs.getInt("id_composant"));
                st.setNomComposant(rs.getString("nom_composant"));
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

    // GETTERS AND SETTERS
    public int getIdComposant() {
        return idComposant;
    }
    public String getNomComposant() {
        return nomComposant;
    }
    public double getTotal() {
        return total;
    }
    public double getUtilise() {
        return utilise;
    }
    public double getRestant() {
        return restant;
    }

    public void setIdComposant(int idComposant) {
        this.idComposant = idComposant;
    }
    public void setNomComposant(String nomComposant) {
        this.nomComposant = nomComposant;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public void setUtilise(double utilise) {
        this.utilise = utilise;
    }
    public void setRestant(double restant) {
        this.restant = restant;
    }
}