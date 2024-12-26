package model.composant.ram;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.composant.Composant;
import model.utils.Database;

public class RAM extends Composant{
    
    private int idRam; 
    private boolean portable;
    private TypeRam typeRam;

    @Override
    public void delete(Connection c) throws SQLException {
        // TODO Auto-generated method stub
        
    }
    @Override
    public List<Composant> getAll(Connection c) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Composant getById(Connection c, int id) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(Connection c) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "INSERT INTO ram(est_portable, id_type_ram, id_composant) VALUES (?, ?, ?)";
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);

            this.insertComposant(c);

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
        // TODO Auto-generated method stub
        
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
