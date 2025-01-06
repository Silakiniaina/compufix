package model.ordinateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.composant.Composant;
import model.composant.cm.CarteMere;
import model.utils.Database;

public class Ordinateur {
    
    private int idOrdinateur;
    private String nomOrdinateur; 
    private String description;
    private List<ComposantOrdinateur> composants;

    public Ordinateur(){
        this.setComposants(new ArrayList<>());
    }

    public List<Ordinateur> getAll(Connection c) throws SQLException{
        List<Ordinateur> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT * FROM ordinateur";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);

            rs = prstm.executeQuery();
            while(rs.next()){
                Ordinateur ordi = new Ordinateur();
                ordi.setIdOrdinateur(rs.getInt("id_ordinateur"));
                ordi.setNomOrdinateur(rs.getString("nom_ordinateur"));
                ordi.setDescription(rs.getString("description"));
                ordi.setComposants(new ComposantOrdinateur().getComposantParOrdinateur(c, ordi.getIdOrdinateur()));
                results.add(ordi);
            }

            return results;
        } catch (Exception e) {
            throw e;
        } finally { 
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    public boolean hasCarteMere(){
        boolean result = false;
        for(ComposantOrdinateur composant : this.getComposants()){
            if(composant.getComposant().getTypeComposant().isCarteMere()){
                result = true;
                break;
            }
        }
        return result;
    }

    public void ajouterComposant(Connection c, Composant composant, int quantite) throws SQLException,Exception{
        // compatibility check 
        this.getCarteMere().checkComposantCompatibility(composant);

        // installation composant
        this.getCarteMere().installerComposant(c, composant);
        
        // Dans tous les cas, on ajoute à la liste des composants
        ComposantOrdinateur comp = new ComposantOrdinateur();
        comp.setComposant(c, composant.getIdComposant());
        comp.setQuantite(quantite);
        comp.insert(c, this);
        
        this.getComposants().add(comp);
    }

    public void describe(){
        System.out.println("Nom : "+this.getNomOrdinateur());
        System.out.println("Processeur slot : "+this.getCarteMere().hasProcesseurInstalle());
        System.out.println("Slot ram disponible : "+this.getCarteMere().getNombreSlotsRAMDisponibles()+" / "+this.getCarteMere().getNombreSlotRam());
        System.out.println("Slot disque disponible : "+this.getCarteMere().getNombreSlotsDisqueDisponibles()+" / "+this.getCarteMere().getNombreSlotDisque());
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
    
    public CarteMere getCarteMere(){
        for(ComposantOrdinateur composant : this.getComposants()){
            if(composant.getComposant().getTypeComposant().isCarteMere()){
               return (CarteMere)composant.getComposant();
            }
        }
        return null;
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
