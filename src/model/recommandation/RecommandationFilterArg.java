package model.recommandation;

public class RecommandationFilterArg{
    
    private int annee;
    private int mois;

/// ACTIONS
    public String getQuery(){
        String query = "SELECT * FROM composant_recommande WHERE 1=1 ";
        if( this.getMois() != 0){
            query += " AND EXTRACT(MONTH FROM date_recommandation) = ?";
        }
        if(this.getAnnee() != 0){
            query += " AND EXTRACT(YEAR FROM date_recommandation) = ?";
        }
        query += "GROUP BY id_composant_recommande,EXTRACT(MONTH FROM date_recommandation),EXTRACT(YEAR FROM date_recommandation)";
        return query;
    }

/// GETTERS AND SETTERS
    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    public int getMois() {
        return mois;
    }
    public void setMois(int mois) {
        this.mois = mois;
    }
    
}
