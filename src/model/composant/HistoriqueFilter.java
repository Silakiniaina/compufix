package model.composant;

import java.sql.Date;

public class HistoriqueFilter {
    
    private Composant composant;
    private Date dateDebut;
    private Date dateFin;

    public String getQuery(){
        String query = "SELECT * FROM historique_prix_composant WHERE 1=1";
        if(composant != null){
            query += " AND id_composant = " + composant.getIdComposant();
        }
        if(dateDebut != null){
            query += " AND date_modification >= '" + dateDebut + "'";
        }
        if(dateFin != null){
            query += " AND date_modification <= '" + dateFin + "'";
        }
        return query;
    }

/// GETTERS SETTERS
    public void setComposant(Composant composant) {
        this.composant = composant;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public Composant getComposant() {
        return composant;
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    
}
