package com.example.lab9_grupomagenta.Daos;

import com.example.lab9_grupomagenta.Beans.BHumano;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HumanosDao extends BaseDao{
    public ArrayList<BHumano> listarHumanos(){
        ArrayList<BHumano> listaHum = new ArrayList<>();
        String sql = "SELECT * FROM humano";
        try(Connection conn = this.obtenerConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);){
            while(rs.next()){
                BHumano humano = new BHumano();
                humano.setNumIdentificacion(rs.getString(1));
                humano.setNombre(rs.getString(2));
                humano.setApellido(rs.getString(3));
                humano.setSexo(rs.getString(4));
                humano.setEstado(rs.getString(5));
                listaHum.add(humano);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return listaHum;
    }

}
