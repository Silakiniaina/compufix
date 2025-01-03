package model.composant.cm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.composant.Composant;
import model.composant.disque.Disque;
import model.composant.disque.TypeDisque;
import model.composant.processeur.Processeur;
import model.composant.processeur.TypeProcesseur;
import model.composant.ram.RAM;
import model.composant.ram.TypeRam;
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

    // CRUD Operation
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
                return this;
            }
            return null;
        } finally {
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }

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
    public boolean isRAMCompatible(RAM ram) {
        // Vérifier le type de RAM
        if (ram.getTypeRam() != this.getTypeRam()) {
            return false;
        }
        
        // Vérifier si un slot est disponible
        if (ramsInstallees.size() >= nombreSlotRam) {
            return false;
        }
        
        return true;
    }

    // Vérification Processeur
    public boolean isProcesseurCompatible(Processeur processeur) {
        // Vérifier si le type de socket correspond
        if (processeur.getTypeProcesseur() != this.getTypeProcesseur()) {
            return false;
        }
        
        // Vérifier si un processeur est déjà installé
        if (processeurInstalle != null) {
            return false;
        }
        
        return true;
    }

    // Vérification Disque
    public boolean isDisqueCompatible(Disque disque) {
        // Vérifier le type de disque
        if (disque.getTypeDisque() != this.getTypeDisque()) {
            return false;
        }
        
        // Vérifier si un slot est disponible
        if (disquesInstalles.size() >= nombreSlotDisque) {
            return false;
        }
        
        return true;
    }

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
