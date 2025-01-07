package model.ordinateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.composant.Composant;
import model.composant.cm.CarteMere;
import model.utils.Database;

public class Ordinateur {
    
    private int idOrdinateur;
    private String nomOrdinateur; 
    private String description;
    private Double prix;
    private List<ComposantOrdinateur> composants;

    public Ordinateur(){
        this.setComposants(new ArrayList<>());
    }

/// CRUD Operation
    public List<Ordinateur> getAll(Connection c) throws SQLException{
        List<Ordinateur> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT * FROM v_ordinateur";
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
                ordi.setPrix(rs.getDouble("prix"));
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

    public void insert(Connection c)throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        String sql = "INSERT INTO ordinateur(nom_ordinateur, description) VALUES(?, ?)";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            
            c.setAutoCommit(false);
            
            prstm = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prstm.setString(1, this.getNomOrdinateur());
            prstm.setString(2, this.getDescription());

            prstm.executeUpdate();

            // Récupération de l'I D généré
            try (ResultSet generatedKeys = prstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.setIdOrdinateur(generatedKeys.getInt(1));
                }
            }
            
            c.commit();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        }finally{
            Database.closeRessources(null, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

/// Composant check
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

    public void checkCompatibility(Composant c)throws Exception{
        if(!this.hasCarteMere() && !(c instanceof CarteMere)){
            throw new Exception("Aucune carte mere trouve dans l\'ordinateur pour ajouter le composant : "+c.getNomComposant());
        }

        if(this.hasCarteMere() && c instanceof CarteMere){
            throw new Exception("Cette ordinateur a deja un carte mere : "+this.getCarteMere().getNomComposant());
        }

        // compatibility check 
        if(this.hasCarteMere()){
            this.getCarteMere().checkComposantCompatibility(c);
        }
    }

/// Installation composant
    public void ajouterComposant(Connection c, Composant composant, int quantite) throws SQLException,Exception{
        // Verification des composants
        this.checkCompatibility(composant);

        // Dans tous les cas, on ajoute à la liste des composants
        ComposantOrdinateur comp = new ComposantOrdinateur();
        comp.setComposant(composant);
        comp.setQuantite(quantite);
        
        this.getComposants().add(comp);
    }

    public void installerComposant(Connection c, ComposantOrdinateur comp)throws SQLException{
        comp.insert(c, this);
    }

    public void installerComposants(Connection c)throws SQLException{
        for(ComposantOrdinateur composant : this.getComposants()){
            composant.insert(c, this);
        }
    }

/// Utils
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
    
    public CarteMere getCarteMere() {
        for (ComposantOrdinateur composant : this.getComposants()) {
            if (composant.getComposant().getTypeComposant().isCarteMere()) {
                if (composant.getComposant() instanceof CarteMere) {
                    return (CarteMere) composant.getComposant();
                } else {
                    throw new IllegalStateException("The component is marked as a CarteMere but is not an instance of CarteMere.");
                }
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

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public List<ComposantOrdinateur> getComposants() {
        return composants;
    }
    public void setComposants(List<ComposantOrdinateur> composants) {
        this.composants = composants;
    }
}
