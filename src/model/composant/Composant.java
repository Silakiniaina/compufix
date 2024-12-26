package model.composant;

public class Composant {
    private int idComposant;
    private String nomComposant; 
    private double capacite; 
    private double prixUnitaire;

    

    // GETTERS AND SETTERS
    public int getIdComposant() {
        return idComposant;
    }
    public String getNomComposant() {
        return nomComposant;
    }
    public double getCapacite() {
        return capacite;
    }
    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setIdComposant(int idComposant) {
        this.idComposant = idComposant;
    }
    public void setNomComposant(String nomComposant) {
        this.nomComposant = nomComposant;
    }
    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }
    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
    
}