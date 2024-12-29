package model.composant.cm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.composant.Composant;
import model.composant.disque.TypeDisque;
import model.composant.processeur.TypeProcesseur;
import model.composant.ram.TypeRam;
import model.utils.Database;

public class CarteMere extends Composant{
    
    private int idCarteMere;
    private int nombreSlotRam;
    private int nombreSlotDisque;
    private TypeRam typeRam;
    private TypeProcesseur typeProcesseur;
    private TypeDisque typeDisque;

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

            this.deleteComposant(c);

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
    public void insert(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "INSERT INTO carte_mere(nombre_slot_ram, nombre_slot_disque, id_composant) VALUES (?, ?, ?)";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);

            this.insertComposant(c);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getNombreSlotRam());
            prstm.setInt(2, this.getNombreSlotDisque());
            prstm.setInt(3, this.getIdComposant());
            
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
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "UPDATE carte_mere SET nombre_slot_ram = ?, nombre_slot_disque = ?, id_composant = ? WHERE id_carte_mere = ?";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            
            this.updateComposant(c);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getNombreSlotRam());
            prstm.setInt(2, this.getNombreSlotDisque());
            prstm.setInt(3, this.getIdComposant());
            prstm.setInt(4, this.getIdCarteMere());
            
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
}
