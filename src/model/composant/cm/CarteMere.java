package model.composant.cm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.composant.Composant;
import model.composant.disque.Disque;
import model.composant.disque.TypeDisque;
import model.composant.processeur.Processeur;
import model.composant.processeur.TypeProcesseur;
import model.composant.ram.RAM;
import model.composant.ram.TypeRam;
import model.ordinateur.Ordinateur;
import model.utils.Database;

public class CarteMere extends Composant{
    
    private int idCarteMere;
    private int nombreSlotRam;
    private int nombreSlotDisque;
    private TypeRam typeRam;
    private TypeProcesseur typeProcesseur;
    private TypeDisque typeDisque;

    private List<RAM> ramsInstallees;
    private List<Disque> disquesInstalles;
    private Processeur processeurInstalle;

    public CarteMere(){
        this.setRamsInstallees(new ArrayList<>());
        this.setDisquesInstalles(new ArrayList<>());
    }

/// CRUD Operation
    @Override
    public List<Composant> getAll(Connection c) throws SQLException {
        List<Composant> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM carte_mere, composant WHERE carte_mere.id_composant = composant.id_composant ORDER BY id_carte_mere";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            
            rs = prstm.executeQuery();
            while (rs.next()) {
                CarteMere r = new CarteMere();
                r.setIdCarteMere(rs.getInt("id_carte_mere"));
                r.setIdComposant(rs.getInt("id_composant"));
                r.setNomComposant(rs.getString("nom_composant"));
                r.setNombreSlotRam(rs.getInt("nombre_slot_ram"));
                r.setNombreSlotDisque(rs.getInt("nombre_slot_disque"));
                r.setTypeDisque(c, rs.getInt("id_type_disque"));
                r.setTypeRam(c, rs.getInt("id_type_ram"));
                r.setTypeProcesseur(c, rs.getInt("id_type_processeur"));
                r.setCapacite(rs.getDouble("capacite"));
                r.setPrixUnitaire(rs.getDouble("prix_unitaire"));
                r.setTypeComposant(c, rs.getInt("id_type_composant"));
                results.add(r);
            }
            return results;
        } finally {
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    @Override
    public Composant getById(Connection c, int id) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM carte_mere, composant WHERE carte_mere.id_composant = composant.id_composant AND id_carte_mere = ?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            
            rs = prstm.executeQuery();
            if (rs.next()) {
                this.setRamsInstallees(new ArrayList<>());
                this.setDisquesInstalles(new ArrayList<>());
                this.setIdCarteMere(rs.getInt("id_carte_mere"));
                this.setIdComposant(rs.getInt("id_composant"));
                this.setNomComposant(rs.getString("nom_composant"));
                this.setNombreSlotRam(rs.getInt("nombre_slot_ram"));
                this.setNombreSlotDisque(rs.getInt("nombre_slot_disque"));
                this.setTypeDisque(c, rs.getInt("id_type_disque"));
                this.setTypeRam(c, rs.getInt("id_type_ram"));
                this.setTypeProcesseur(c, rs.getInt("id_type_processeur"));
                this.setCapacite(rs.getDouble("capacite"));
                this.setPrixUnitaire(rs.getDouble("prix_unitaire"));
                this.setTypeComposant(c, rs.getInt("id_type_composant"));
                return this;
            }
            return null;
        } finally {
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    // @Override
    // public Composant getByIdComposant(Connection c, int id) throws SQLException {
    //     boolean isNewConnection = false;
    //     PreparedStatement prstm = null;
    //     ResultSet rs = null;
    //     String query = "SELECT * FROM carte_mere, composant WHERE carte_mere.id_composant = composant.id_composant AND carte_mere.id_composant = ?";
    //     try {
    //         if( c == null){
    //             c = Database.getConnection();
    //             isNewConnection = true;
    //         }
    //         prstm = c.prepareStatement(query);
    //         prstm.setInt(1, id);
            
    //         rs = prstm.executeQuery();
    //         if (rs.next()) {
    //             this.setIdCarteMere(rs.getInt("id_carte_mere"));
    //             this.setIdComposant(rs.getInt("id_composant"));
    //             this.setNomComposant(rs.getString("nom_composant"));
    //             this.setNombreSlotRam(rs.getInt("nombre_slot_ram"));
    //             this.setNombreSlotDisque(rs.getInt("nombre_slot_disque"));
    //             this.setTypeDisque(c, rs.getInt("id_type_disque"));
    //             this.setTypeRam(c, rs.getInt("id_type_ram"));
    //             this.setTypeProcesseur(c, rs.getInt("id_type_processeur"));
    //             this.setCapacite(rs.getDouble("capacite"));
    //             this.setPrixUnitaire(rs.getDouble("prix_unitaire"));
    //             this.setTypeComposant(c, rs.getInt("id_type_composant"));
    //             return this;
    //         }
    //         return null;
    //     } finally {
    //         Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
    //     }
    // }

    @Override
    public void delete(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "DELETE FROM carte_mere WHERE id_carte_mere = ? ";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getIdCarteMere());
            
            prstm.executeUpdate();

            c.commit();
            super.delete(c);
        } catch (SQLException e) {
            if (c != null) {
                c.rollback();
            }
            throw e;
        } finally {
           Database.closeRessources(null, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    @Override
    public void insert(Connection c) throws SQLException {
        super.insert(c);

        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "INSERT INTO carte_mere(nombre_slot_ram, nombre_slot_disque, id_type_disque, id_type_ram, id_type_processeur, id_composant) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getNombreSlotRam());
            prstm.setInt(2, this.getNombreSlotDisque());
            prstm.setInt(3, this.getTypeDisque().getIdTypeDisque());
            prstm.setInt(4, this.getTypeRam().getIdTypeRam());
            prstm.setInt(5, this.getTypeProcesseur().getIdTypeProcesseur());
            prstm.setInt(6, this.getIdComposant());
            
            prstm.executeUpdate();

            c.commit();
        } catch (SQLException e) {
            if (c != null) {
                c.rollback();
            }
            throw e;
        } finally {
           Database.closeRessources(null, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    @Override
    public void update(Connection c) throws SQLException {
        super.update(c);

        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "UPDATE carte_mere SET nombre_slot_ram = ?, nombre_slot_disque = ?, id_type_disque = ? , id_type_ram = ?, id_type_processeur = ?, id_composant = ? WHERE id_carte_mere = ?";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
        
            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getNombreSlotRam());
            prstm.setInt(2, this.getNombreSlotDisque());
            prstm.setInt(3, this.getTypeDisque().getIdTypeDisque());
            prstm.setInt(4, this.getTypeRam().getIdTypeRam());
            prstm.setInt(5, this.getTypeProcesseur().getIdTypeProcesseur());
            prstm.setInt(6, this.getIdComposant());
            prstm.setInt(7, this.getIdCarteMere());
            
            prstm.executeUpdate();
            
            c.commit();
        } catch (SQLException e) {
            if (c != null) {
                c.rollback();
            }
            throw e;
        } finally {
           Database.closeRessources(null, prstm, c, Boolean.valueOf(isNewConnection));
        } 
    }

    @Override
    public int getType() {
        return Composant.COMPOSANT_CARTE_MERE;
    }

/// COMPATIBILITY CHECK 
    // Vérification RAM
    public void checkRamCompatibility(RAM ram)throws Exception {
        // Vérifier le type de RAM
        if (!ram.getTypeRam().getNomTypeRam().equals(this.getTypeRam().getNomTypeRam())) {
            throw new Exception("Le type de ram : "+ram.getTypeRam().getNomTypeRam()+" est pas compatible a celui du carte mere : "+this.getTypeRam().getNomTypeRam());
        }
        
        // Vérifier si un slot est disponible
        if (!this.hasSlotsRAMDisponibles()) {
            throw new Exception("Aucun slot disponible pour inserer le ram");
        }
    }

    // Vérification Processeur
    public void checkProcesseurCompatibility(Processeur processeur) throws Exception{
        // Vérifier si le type de socket correspond
        if (!processeur.getTypeProcesseur().getNomTypeProcesseur().equals(this.getTypeProcesseur().getNomTypeProcesseur())) {
            throw new Exception("Le type de processeur : "+processeur.getTypeProcesseur().getNomTypeProcesseur()+" est pas compatible a celui du carte mere : "+this.getTypeProcesseur().getNomTypeProcesseur());
        }
        
        // Vérifier si un processeur est déjà installé
        if (this.hasProcesseurInstalle()) {
            throw new Exception("Le slot pour le processeur est deja occupe");
        };
    }

    // Vérification Disque
    public void checkDisqueCompatibility(Disque disque) throws Exception{
        if (!disque.getTypeDisque().getNomTypeDisque().equals(this.getTypeDisque().getNomTypeDisque())) {
            throw new Exception("Le type de disque : "+disque.getTypeDisque().getNomTypeDisque()+" est pas compatible a celui du carte mere : "+this.getTypeDisque().getNomTypeDisque());
        }

        if (!this.hasSlotsDisqueDisponibles()) {
            throw new Exception("Aucun slot disponible pour inserer le disque");
        }
    }
    
    // verification globale
    public void checkComposantCompatibility(Composant c)throws Exception{
        if(c instanceof RAM){
            this.checkRamCompatibility((RAM)c);
        } else if(c instanceof Processeur){
            this.checkProcesseurCompatibility((Processeur)c);
        }else if( c instanceof Disque){
            this.checkDisqueCompatibility((Disque)c);
        }
    }

/// INSTALLATION 
    public void installComposant(Connection c, int idComposant, String typeSlot)throws SQLException {
        boolean isNewConnection = false; 
        PreparedStatement prstm = null; 
        String sql = "INSERT INTO carte_mere_utilisation(id_composant,type_slot,id_carte_mere) VALUES (?, ?, ?)";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            prstm = c.prepareStatement(sql);
            prstm.setInt(1, idComposant);
            prstm.setString(2, typeSlot);
            prstm.setInt(3, this.getIdCarteMere());

            prstm.executeUpdate();
            c.commit();
        } catch (Exception e) {
            c.rollback();
            throw e;
        } finally{
            Database.closeRessources(null, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

    // installation RAM
    public void installerRAM(Connection c, RAM ram) throws SQLException,Exception{
        this.installComposant(c, ram.getIdComposant(), "RAM");
        this.getRamsInstallees().add(ram);
    }

    // installation Processeur
    public void installerProcesseur(Connection c, Processeur processeur)throws SQLException,Exception{
        this.installComposant(c, processeur.getIdComposant(), "Processeur");
        this.setProcesseurInstalle(processeur);
    }

    // installation Disque
    public void installerDisque(Connection c, Disque disque) throws SQLException,Exception{
        this.installComposant(c, disque.getIdComposant(), "Disque");
        this.getDisquesInstalles().add(disque);
    }

    // installation globale
    public void installerComposant(Connection c , Composant comp) throws SQLException, Exception{
        if(c instanceof RAM){
            this.installerRAM(c,(RAM)comp);
        } else if(c instanceof Processeur){
            this.installerProcesseur(c,(Processeur)comp);
        }else if( c instanceof Disque){
            this.installerDisque(c,(Disque)comp);
        }
    }

/// UTILS 
    // Méthodes utilitaires
    public boolean hasSlotsRAMDisponibles() {
        return ramsInstallees.size() < nombreSlotRam;
    }
    
    public boolean hasSlotsDisqueDisponibles() {
        return disquesInstalles.size() < nombreSlotDisque;
    }
    
    public boolean hasProcesseurInstalle() {
        return processeurInstalle != null;
    }
    
    // Obtenir le nombre de slots disponibles
    public int getNombreSlotsRAMDisponibles() {
        return nombreSlotRam - ramsInstallees.size();
    }
    
    public int getNombreSlotsDisqueDisponibles() {
        return nombreSlotDisque - disquesInstalles.size();
    }

/// GETTERS AND SETTERS
    public int getIdCarteMere() {
        return idCarteMere;
    }
    public int getNombreSlotRam() {
        return nombreSlotRam;
    }
    public int getNombreSlotDisque() {
        return nombreSlotDisque;
    }
    public TypeRam getTypeRam() {
        return typeRam;
    }
    public TypeProcesseur getTypeProcesseur() {
        return typeProcesseur;
    }
    public TypeDisque getTypeDisque() {
        return typeDisque;
    }
    public List<RAM> getRamsInstallees() {
        return ramsInstallees;
    }
    public List<Disque> getDisquesInstalles() {
        return disquesInstalles;
    }
    public Processeur getProcesseurInstalle() {
        return processeurInstalle;
    }

    public void setProcesseurInstalle(Processeur processeurInstalle) {
        this.processeurInstalle = processeurInstalle;
    }
    public void setIdCarteMere(int idCarteMere) {
        this.idCarteMere = idCarteMere;
    }
    public void setNombreSlotRam(int nombreSlotRam) {
        this.nombreSlotRam = nombreSlotRam;
    }
    public void setNombreSlotDisque(int nombreSlotDisque) {
        this.nombreSlotDisque = nombreSlotDisque;
    }
    public void setTypeRam(Connection c, int typeRam) throws SQLException{
        this.typeRam = new TypeRam().getById(c, typeRam);
    }
    public void setTypeProcesseur(Connection c, int typeProcesseur)throws SQLException {
        this.typeProcesseur = new TypeProcesseur().getById(c, typeProcesseur);
    }
    public void setTypeDisque(Connection c, int typeDisque) throws SQLException{
        this.typeDisque = new TypeDisque().getById(c, typeDisque);
    }
    public void setRamsInstallees(List<RAM> ramsInstallees) {
        this.ramsInstallees = ramsInstallees;
    }
    public void setDisquesInstalles(List<Disque> disquesInstalles) {
        this.disquesInstalles = disquesInstalles;
    }
}
