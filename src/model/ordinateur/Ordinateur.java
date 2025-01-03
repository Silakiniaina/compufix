package model.ordinateur;

import java.util.ArrayList;
import java.util.List;

public class Ordinateur {
    
    private int idOrdinateur;
    private String nomOrdinateur; 
    private String description;
    private List<ComposantOrdinateur> composants;

    public Ordinateur(){
        this.setComposants(new ArrayList<>());
    }

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

    public List<ComposantOrdinateur> getComposants() {
        return composants;
    }
    public void setComposants(List<ComposantOrdinateur> composants) {
        this.composants = composants;
    }
}
