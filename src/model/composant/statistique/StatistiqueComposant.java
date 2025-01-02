package model.composant.statistique;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.composant.Composant;
import model.utils.Database;

public class StatistiqueComposant {
    
    private Composant composant; 
    private int nombreSortie;
    private int nombreEntree;
    private double totalSortie;
    private double totalEntree;

    public List<StatistiqueComposant> getStatistique(Connection c, Date debut, Date fin) throws SQLException{
        List<StatistiqueComposant> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT * FROM v_statistique_mouvement WHERE id_composant IN (SELECT id_composant FROM mouvement_stock WHERE date_mouvement BETWEEN ? AND ?)";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setDate(1, debut);
            prstm.setDate(2, fin);
            rs = prstm.executeQuery();

            while (rs.next()) {
                StatistiqueComposant stat = new StatistiqueComposant();
                stat.setComposant(c, rs.getInt("id_composant"));
                stat.setNombreEntree(rs.getInt("nombre_entrees"));
                stat.setNombreSortie(rs.getInt("nombre_sorties"));
                stat.setTotalEntree(rs.getDouble("total_entrees"));
                stat.setTotalSortie(rs.getDouble("total_sorties"));

                results.add(stat);
            }

            return results;
        } catch (Exception e) {
            throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public Composant getComposant() {
        return composant;
    }
    public int getNombreSortie() {
        return nombreSortie;
    }
    public int getNombreEntree() {
        return nombreEntree;
    }
    public double getTotalSortie() {
        return totalSortie;
    }
    public double getTotalEntree() {
        return totalEntree;
    }

    public void setComposant(Connection c, int composant) throws SQLException{
        this.composant = new Composant().getById(c, composant);
    }
    public void setNombreSortie(int nombreSortie) {
        this.nombreSortie = nombreSortie;
    }
    public void setNombreEntree(int nombreEntree) {
        this.nombreEntree = nombreEntree;
    }
    public void setTotalSortie(double totalSortie) {
        this.totalSortie = totalSortie;
    }
    public void setTotalEntree(double totalEntree) {
        this.totalEntree = totalEntree;
    }
    
}
