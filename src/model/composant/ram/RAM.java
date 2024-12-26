package model.composant.ram;

import java.sql.Connection;
import java.sql.SQLException;

public class RAM {
    
    private int idRam; 
    private boolean portable;
    private TypeRam typeRam;


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
