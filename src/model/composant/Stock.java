package model.composant;

public class Stock {
    
    private int idComposant;
    private String nomComposant;
    private double total; 
    private double utilise;
    private double restant;

    // etat stock 

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
