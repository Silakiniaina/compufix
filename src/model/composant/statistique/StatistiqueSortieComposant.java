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

public class StatistiqueSortieComposant {
    
    private Composant composant; 
    private double moyenne;
    private double actuelle;

    public List<StatistiqueSortieComposant> getMoyenneSortie(Connection c, Date debut, Date fin) throws SQLException{
        List<StatistiqueSortieComposant> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT * FROM v_statistique_moyenne_sortie WHERE id_composant IN (SELECT id_composant FROM mouvement_stock WHERE date_mouvement BETWEEN ? AND ?)";
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
                StatistiqueSortieComposant stat = new StatistiqueSortieComposant();
                stat.setComposant(c, rs.getInt("id_composant"));
                stat.setMoyenne(rs.getDouble("moyenne_sorties"));
                stat.setActuelle(rs.getDouble("sortie_actuelle"));

                results.add(stat);
            }

            return results;
        } catch (Exception e) {
            throw e;
        } finally{
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public double getMoyenne() {
        return moyenne;
    }
    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    public void setComposant(Connection c, int composant) throws SQLException{
        this.composant = new Composant().getById(c, composant);
    }

    public Composant getComposant(){
        return this.composant;
    }

    public double getActuelle() {
        return actuelle;
    }

    public void setActuelle(double actuelle) {
        this.actuelle = actuelle;
    }
}
