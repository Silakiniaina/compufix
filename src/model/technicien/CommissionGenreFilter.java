package model.technicien;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class CommissionGenreFilter {
    
    private Date debut;
    private Date fin;
    private Genre genre;

    public String getQuery() {
        StringBuilder query = new StringBuilder(
            "  select \n" + //
            "    g.id_genre,\n" + //
            "    count(id_reparation) as nombre_reparation,\n" + //
            "    sum(prix) as total_reparation,\n" + //
            "    sum(commission_technicien) as total_commission\n" + //
            "  from \n" + //
            "    v_commission_technicien_complet v\n" + //
            "  JOIN technicien t \n" + //
            "  ON t.id_technicien = v.id_technicien \n" + //
            "  JOIN genre g \n" + //
            "  ON g.id_genre = t.id_genre \n" + //
            " WHERE 1=1 "
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

        if(genre != null){
            query.append("AND g.id_genre = ? ");
        }

        query.append("GROUP BY  g.id_genre "); 
        return query.toString();
    }


/// GETTERS AND SETTERS
    public Date getDebut() {
        return debut;
    }
    public Date getFin() {
        return fin;
    }
    public Genre getGenre() {
        return genre;
    }
    public void setDebut(Date debut) {
        this.debut = debut;
    }
    public void setFin(Date fin) {
        this.fin = fin;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setGenre(Connection c, int genre) throws SQLException{
        this.genre = new Genre().getById(c, genre);
    }
}

