package model.technicien;

import java.sql.Date;

public class CommissionPeriodFilter {
    
    private Date debut;
    private Date fin;

/// ACTIONS 
    public String getQuery() {
        StringBuilder query = new StringBuilder(
            "SELECT t.id_technicien, " +
            "COALESCE(SUM(v.prix), 0) AS total_reparation, " +
            "COALESCE(SUM(v.commission_technicien), 0) AS total_commission, " +
            "COUNT(v.id_reparation) AS nombre_reparation " +
            "FROM technicien t " +
            "LEFT JOIN v_commission_technicien_complet v " +
            "ON t.id_technicien = v.id_technicien "
        );

        // Add filtering conditions to the ON clause
        if (debut != null || fin != null) {
            query.append("AND ");
            if (debut != null) {
                query.append("v.date_reparation >= ? ");
                if (fin != null) {
                    query.append("AND ");
                }
            }
            if (fin != null) {
                query.append("v.date_reparation <= ? ");
            }
        }

        query.append("GROUP BY t.id_technicien ");
        query.append("ORDER BY total_commission DESC");

        return query.toString();
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
