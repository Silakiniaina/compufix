package model.composant.ram;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.composant.Composant;
import model.utils.Database;

public class RAM extends Composant{
    
    private int idRam; 
    private boolean portable;
    private TypeRam typeRam;

    
    @Override
    public List<Composant> getAll(Connection c) throws SQLException {
        List<Composant> results = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM ram, composant WHERE ram.id_composant = composant.id_composant ORDER BY id_ram";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            
            rs = prstm.executeQuery();
            while (rs.next()) {
                RAM r = new RAM();
                r.setIdRam(rs.getInt("id_ram"));
                r.setPortable(rs.getBoolean("est_portable"));
                r.setTypeRam(c, rs.getInt("id_type_ram"));
                r.setIdComposant(rs.getInt("id_composant"));
                r.setNomComposant(rs.getString("nom_composant"));
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
        String query = "SELECT * FROM ram, composant WHERE ram.id_composant = composant.id_composant AND id_ram = ?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            
            rs = prstm.executeQuery();
            if (rs.next()) {
                this.setIdRam(rs.getInt("id_ram"));
                this.setPortable(rs.getBoolean("est_portable"));
                this.setTypeRam(c, rs.getInt("id_type_ram"));
                this.setIdComposant(rs.getInt("id_composant"));
                this.setNomComposant(rs.getString("nom_composant"));
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
        String query = "DELETE FROM ram WHERE id_ram = ? ";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getIdRam());
            
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
        String query = "INSERT INTO ram(est_portable, id_type_ram, id_composant) VALUES (?, ?, ?)";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setBoolean(1, this.isPortable());
            prstm.setInt(2, this.getTypeRam().getIdTypeRam());
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
        String query = "UPDATE ram SET est_portable = ?, id_type_ram = ?, id_composant = ? WHERE id_ram = ?";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            
            prstm = c.prepareStatement(query);
            prstm.setBoolean(1, this.isPortable());
            prstm.setInt(2, this.getTypeRam().getIdTypeRam());
            prstm.setInt(3, this.getIdComposant());
            prstm.setInt(4, this.getIdRam());
            
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

    // GETTERS AND SETTERS
    public int getIdRam() {
        return idRam;
    }
    public boolean isPortable() {
        return portable;
    }
    public TypeRam getTypeRam(){
        return this.typeRam;
    }

    public void setTypeRam(Connection c, int id)throws SQLException{
        this.typeRam = new TypeRam().getById(c, id);
    }
    public void setIdRam(int idRam) {
        this.idRam = idRam;
    }
    public void setPortable(boolean portable) {
        this.portable = portable;
    }
}
