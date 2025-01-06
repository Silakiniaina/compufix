package model.composant.disque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.composant.Composant;
import model.composant.disque.Disque;
import model.ordinateur.Ordinateur;
import model.utils.Database;

public class Disque extends Composant{
    
    private int idDisque;
    private TypeDisque typeDisque;
    private boolean portable;

    @Override
    public List<Composant> getAll(Connection c) throws SQLException {
        List<Composant> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM disque_dur, composant WHERE disque_dur.id_composant = composant.id_composant ORDER BY id_disque_dur";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            
            rs = prstm.executeQuery();
            while (rs.next()) {
                Disque r = new Disque();
                r.setIdDisque(rs.getInt("id_disque_dur"));
                r.setPortable(rs.getBoolean("est_portable"));
                r.setTypeDisque(c, rs.getInt("id_type_disque"));
                r.setIdComposant(rs.getInt("id_composant"));
                r.setNomComposant(rs.getString("nom_composant"));
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
        String query = "SELECT * FROM disque_dur, composant WHERE disque_dur.id_composant = composant.id_composant AND id_disque_dur = ?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            
            rs = prstm.executeQuery();
            if (rs.next()) {
                this.setIdDisque(rs.getInt("id_disque_dur"));
                this.setPortable(rs.getBoolean("est_portable"));
                this.setTypeDisque(c, rs.getInt("id_type_disque"));
                this.setIdComposant(rs.getInt("id_composant"));
                this.setNomComposant(rs.getString("nom_composant"));
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

    @Override
    public Composant getByIdComposant(Connection c, int id) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM disque_dur, composant WHERE disque_dur.id_composant = composant.id_composant AND disque_dur.id_composant = ?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            
            rs = prstm.executeQuery();
            if (rs.next()) {
                this.setIdDisque(rs.getInt("id_disque_dur"));
                this.setPortable(rs.getBoolean("est_portable"));
                this.setTypeDisque(c, rs.getInt("id_type_disque"));
                this.setIdComposant(rs.getInt("id_composant"));
                this.setNomComposant(rs.getString("nom_composant"));
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

    @Override
    public void delete(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "DELETE FROM disque_dur WHERE id_disque_dur = ? ";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getIdDisque());
            
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
        String query = "INSERT INTO disque_dur(est_portable,id_type_disque, id_composant) VALUES (?, ?, ?)";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setBoolean(1, this.isPortable());
            prstm.setInt(2, this.getTypeDisque().getIdTypeDisque());
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
        super.update(c);

        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "UPDATE disque_dur SET est_portable = ?, id_type_disque = ?, id_composant = ? WHERE id_disque_dur = ?";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setBoolean(1, this.isPortable());
            prstm.setInt(2, this.getTypeDisque().getIdTypeDisque());
            prstm.setInt(3, this.getIdComposant());
            prstm.setInt(4, this.getIdDisque());
            
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
        return Composant.COMPOSANT_DISQUE;
    }

    public static List<Disque> getDisquesInstallees(Connection c, int idcm, int ido) throws SQLException{
        List<Disque> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql  = "SELECT * FROM v_installation_disque WHERE type_slot = ? AND id_carte_mere = ? AND id_ordinateur = ? ";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setString(1,"Disque");
            prstm.setInt(2, idcm);
            prstm.setInt(3, ido);

            rs = prstm.executeQuery();
            while (rs.next()) {
                Disque r = new Disque();
                r.setIdDisque(rs.getInt("id_disque_dur"));
                r.setPortable(rs.getBoolean("est_portable"));
                r.setTypeDisque(c, rs.getInt("id_type_disque"));
                r.setIdComposant(rs.getInt("id_composant"));
                r.setNomComposant(rs.getString("nom_composant"));
                r.setCapacite(rs.getDouble("capacite"));
                r.setPrixUnitaire(rs.getDouble("prix_unitaire"));
                results.add(r);
            }

            return results;
        } catch (Exception e) {
            throw e;
        } finally{ 
            Database.closeRessources(rs, prstm, c, Boolean.valueOf(isNewConnection));
        }
    }
    
    public int getIdDisque() {
        return idDisque;
    }
    public TypeDisque getTypeDisque() {
        return typeDisque;
    }
    public void setIdDisque(int idDisque) {
        this.idDisque = idDisque;
    }
    public void setTypeDisque(Connection c, int typeDisque) throws SQLException{
        this.typeDisque = new TypeDisque().getById(null, typeDisque);
    }
    public boolean isPortable() {
        return portable;
    }

    public void setPortable(boolean portable) {
        this.portable = portable;
    }
}
