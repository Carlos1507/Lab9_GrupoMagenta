package com.example.lab9_grupomagenta.Daos;

import com.example.lab9_grupomagenta.Beans.BHumano;
import com.example.lab9_grupomagenta.Beans.BSuperviviente;

import java.sql.*;
import java.util.ArrayList;

public class SupervivienteDao extends BaseDao{
    public ArrayList<BSuperviviente> listarSupervivientes(String filtro){
        ArrayList<BSuperviviente> listaSuperv = new ArrayList<>();
        String sql = "SELECT * FROM superviviente";
        if (filtro!=null){
            sql = "select s.idSuperviviente, s.peso, s.fuerza, s.pesoCargado, s.numeroIdentificacion, s.idPareja " +
                    "from superviviente s inner join humano h on s.numeroIdentificacion = h.numeroIdentificacion " +
                    "where h.sexo= '"+filtro+"'";
        }
        try(Connection conn = this.obtenerConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                BSuperviviente superviviente = new BSuperviviente();
                int idSuperviviente = rs.getInt(1);
                superviviente.setIdSuperviviente(idSuperviviente);
                superviviente.setPeso(rs.getDouble(2));
                superviviente.setFuerza(rs.getDouble(3));
                superviviente.setPesoCargado(obtenerPesoCargado(idSuperviviente));
                String numIdentif = rs.getString(5);
                superviviente.setNumIdentificacion(numIdentif);
                BHumano humano = obtenerDatosHumanoSuperv(numIdentif);
                superviviente.setNombre(humano.getNombre());
                superviviente.setApellido(humano.getApellido());
                superviviente.setSexo(humano.getSexo());
                superviviente.setHabilitado(humano.isHabilitado());
                String parejaID = rs.getString(6);
                if(parejaID != null){
                    String identificacionPareja = obtenerIdentificacion(parejaID);
                    BHumano pareja = obtenerDatosHumanoSuperv(identificacionPareja);
                    superviviente.setPareja(pareja);
                }
                listaSuperv.add(superviviente);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return listaSuperv;
    }
    public BHumano obtenerDatosHumanoSuperv(String identificacion){
        BHumano humano = new BHumano();
        String sql = "select numeroIdentificacion, nombre, apellido, sexo, habilitado from humano where numeroIdentificacion=?";
        try(Connection conn = this.obtenerConexion();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, identificacion);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    humano.setNumIdentificacion(rs.getString(1));
                    humano.setNombre(rs.getString(2));
                    humano.setApellido(rs.getString(3));
                    humano.setSexo(rs.getString(4));
                    humano.setHabilitado(rs.getBoolean(5));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return humano;
    }
    public String obtenerIdentificacion(String id){
        String identificacion = null;
        String sql = "select numeroIdentificacion from superviviente where idSuperviviente= ?";
        try(Connection conn = this.obtenerConexion();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, id);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    identificacion = rs.getString(1);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return identificacion;
    }
    public double obtenerPesoCargado(int idSuperv){
        double peso = 0.0;
        String sql = "select round(sum(o.masa),2) from inventario i\n" +
                "inner join objeto o on i.idObjeto = o.idObjeto\n" +
                "where i.idSuperviviente=?";
        try(Connection conn = this.obtenerConexion();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,idSuperv);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    peso = rs.getDouble(1);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return peso;
    }
    public BSuperviviente obtenerSuperviviente(int idSuperv){
        BSuperviviente superviviente = null;
        String sql = "SELECT * FROM superviviente where idSuperviviente=?";
        try (Connection conn = this.obtenerConexion();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, idSuperv);
            try(ResultSet rs = pstmt.executeQuery();){
                if(rs.next()){
                    superviviente = new BSuperviviente();
                    superviviente.setIdSuperviviente(idSuperv);
                    superviviente.setPeso(rs.getDouble(2));
                    superviviente.setFuerza(rs.getDouble(3));
                    superviviente.setPesoCargado(obtenerPesoCargado(idSuperv));
                    String numIdentif = rs.getString(5);
                    superviviente.setNumIdentificacion(numIdentif);
                    BHumano humano = obtenerDatosHumanoSuperv(numIdentif);
                    superviviente.setNombre(humano.getNombre());
                    superviviente.setApellido(humano.getApellido());
                    superviviente.setSexo(humano.getSexo());
                    superviviente.setHabilitado(humano.isHabilitado());
                    String parejaID = rs.getString(6);
                    if(parejaID != null){
                        String identificacionPareja = obtenerIdentificacion(parejaID);
                        BHumano pareja = obtenerDatosHumanoSuperv(identificacionPareja);
                        superviviente.setPareja(pareja);
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return superviviente;
    }
    public void eliminarSuperviviente(String numID){
        String sql = "UPDATE humano set habilitado=false where numeroIdentificacion=?";
        try(Connection conn = this.obtenerConexion();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, numID);
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void crearSuperviviente(String nombre, String apellido, double peso, double fuerza, String sexo, int pareja, String numIdentificacion){
        String sql = "INSERT INTO humano VALUES ( ? , ? ,?, ?, ?,?)";
        try(Connection conn = this.obtenerConexion();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, numIdentificacion);
            pstmt.setString(2, nombre);
            pstmt.setString(3, apellido);
            pstmt.setString(4,sexo);
            pstmt.setString(5, "superviviente");
            pstmt.setBoolean(6,true);
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        agregaSuperviviente(peso, fuerza, pareja, numIdentificacion);
        int idSupervCreado = obtenerIDSuperviviente(numIdentificacion);
        if(pareja!=0){
            actualizarParejaSuperv(pareja, idSupervCreado);
        }
    }
    public void agregaSuperviviente(double peso, double fuerza, int pareja, String numIdentificacion){
        System.out.println("parejaselect: "+pareja);
        String sql;
        if (pareja ==0 ){
            sql= "INSERT INTO superviviente (peso, fuerza, pesoCargado, numeroIdentificacion) VALUES (?, ?, ?, ?)";
        }else{
            sql= "INSERT INTO superviviente (peso, fuerza, pesoCargado, numeroIdentificacion, idPareja) VALUES (?, ?, ?, ? ,?)";
        }
        try(Connection conn = this.obtenerConexion();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setDouble(1, peso);
            pstmt.setDouble(2, fuerza);
            pstmt.setDouble(3, 0.0);
            pstmt.setString(4, numIdentificacion);
            if(pareja!=0){
                pstmt.setInt(5, pareja);
            }
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("creado con éxito");
    }
    public void actualizarParejaSuperv(int pareja, int idSuperv){
        String sql = "UPDATE superviviente SET idPareja=? where idSuperviviente=?";
        try(Connection conn = this.obtenerConexion();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, idSuperv);
            pstmt.setInt(2, pareja);
            pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public int obtenerIDSuperviviente(String numIdentificacion){
        int idSuperv = 0;
        String sql = "SELECT idSuperviviente from superviviente where numeroIdentificacion=?";
        try(Connection conn = this.obtenerConexion();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,numIdentificacion);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    idSuperv = rs.getInt(1);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return idSuperv;

    }
    public boolean identificacionUsada(String codigo){
        boolean IDusado=false;
        String sql = "Select * from humano where numeroIdentificacion=?";
        try(Connection conn = this.obtenerConexion();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, codigo);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    IDusado = true;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return IDusado;
    }
}
