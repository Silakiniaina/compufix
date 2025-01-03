package model.ordinateur;

public class Ordinateur {
    
    private int idOrdinateur;
    private String nomOrdinateur; 
    private String description;

    

    // GETTERS AND SETTERS
    public int getIdOrdinateur() {
        return idOrdinateur;
    }
    public String getNomOrdinateur() {
        return nomOrdinateur;
    }
    public String getDescription() {
        return description;
    }

    public void setIdOrdinateur(int idOrdinateur) {
        this.idOrdinateur = idOrdinateur;
    }
    public void setNomOrdinateur(String nomOrdinateur) {
        this.nomOrdinateur = nomOrdinateur;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
