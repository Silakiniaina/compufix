package model.technicien;

import java.sql.Date;

public class CommissionPeriodFilter {
    
    private Date debut;
    private Date fin;

/// ACTIONS 
    public String getQuery(){
        String query = "SELECT id_technicien,SUM(prix) AS total_reparation,SUM(commission_technicien) AS total_commission,COUNT(id_reparation) AS nombre_reparation FROM v_commission_technicien_complet WHERE 1=1";
        if(debut != null){
            query += "AND date_reparation >= '"+debut+"'";
        }
        if(fin != null){
            query += "AND date_reparation <= '"+fin+"'";
        }
        return query += "GROUP BY id_technicien ORDER BY total_commission DESC";
    }

/// GETTERS AND SETTERS
    public Date getDebut() {
        return debut;
    }
    public Date getFin() {
        return fin;
    }
    public void setDebut(Date debut) {
        this.debut = debut;
    }
    public void setFin(Date fin) {
        this.fin = fin;
    }
    
}
