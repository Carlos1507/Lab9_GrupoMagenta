package com.example.lab9_grupomagenta.Daos;

import com.example.lab9_grupomagenta.Beans.BTipoZombie;
import com.example.lab9_grupomagenta.Beans.BVariante;
import com.example.lab9_grupomagenta.Beans.BZombie;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ZombiesDao extends BaseDao{

    public ArrayList<BZombie> listarZombies(){
        ArrayList<BZombie> listaZombie = new ArrayList<>();
        String sql = "SELECT H.nombre, H.apellido, H.sexo, H.numeroIdentificacion,TIMESTAMPDIFF(HOUR,z.tiempoInfectado, now()) as \"Dias Infectados\", V.nombre, z.victimas, T.nombre FROM zombie z, humano H, tipozombie T, variantevirus V where z.numeroIdentificacion=H.numeroIdentificacion and T.idTipoZombie = z.idTipoZombie and z.idVarianteVirus = V.idVarianteVirus ";

        try(Connection conn = this.obtenerConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);){
            while(rs.next()){
                BZombie zombie = new BZombie();
                zombie.setNombre(rs.getString(1));
                zombie.setApellido(rs.getString(2));
                zombie.setSexo(rs.getString(3));
                zombie.setNumIdentificacion(rs.getString(4));
                zombie.setTiempoInfectado(rs.getString(5));
                zombie.setVarianteVirus(rs.getString(6));
                zombie.setVictimas(rs.getInt(7));
                zombie.setTipoZombie(rs.getString(8));
                listaZombie.add(zombie);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return listaZombie;
    }

    public ArrayList<BTipoZombie> listarTipoZombies(){
        ArrayList<BTipoZombie> listaTipoZombie = new ArrayList<>();
        String sql ="SELECT * FROM tipozombie";
        try(Connection conn = this.obtenerConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);){
            while(rs.next()){
                BTipoZombie tipoZombie = new BTipoZombie();
                tipoZombie.setIdTipoZombie(rs.getInt(1));
                tipoZombie.setNombre(rs.getString(2));
                listaTipoZombie.add(tipoZombie);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return listaTipoZombie;
    }


    public ArrayList<BVariante> listarVariantes(){
        ArrayList<BVariante> listarVariantes = new ArrayList<>();
        String sql ="SELECT * FROM variantevirus;";
        try(Connection conn = this.obtenerConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);){
            while(rs.next()){
                BVariante tipoVariante = new BVariante();
                tipoVariante.setIdVarianteVirus(rs.getInt(1));
                tipoVariante.setNombre(rs.getString(2));
                listarVariantes.add(tipoVariante);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return listarVariantes;
    }


    public String  varianteMasComun() {
        String sql = "SELECT Z.idZombie, V.nombre ,count(Z.idZombie) FROM zombie Z,variantevirus V where Z.idVarianteVirus=V.idVarianteVirus group by Z.idVarianteVirus order by count(Z.idZombie) desc;";
        String varianteComun = null;
        try (Connection conn = this.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {
            rs.next();
            varianteComun = rs.getString(2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return varianteComun;
    }

    public void crearZombie(String nIdentificacion, String nombre, String apellido,String sexo, String idTipoZombie, String idVarianteVirus){
        String sql1 = "INSERT INTO `laboratorio_9`.`humano` (`numeroIdentificacion`, `nombre`, `apellido`, `sexo`, `estado`, `habilitado`) VALUES (?, ?, ?, ?, 'zombie', '0')";
        String sql2 ="INSERT INTO `laboratorio_9`.`zombie` (`victimas`, `tiempoInfectado`, `idTipoZombie`, `idVarianteVirus`, `numeroIdentificacion`) VALUES ('0', Now(), ?, ?, ?);";
        try (Connection connection = this.obtenerConexion();
             PreparedStatement pstmt = connection.prepareStatement(sql1);) {

            pstmt.setString(1, nIdentificacion);
            pstmt.setString(2, nombre);
            pstmt.setString(3, apellido);
            pstmt.setString(4, sexo);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection2 = this.obtenerConexion();
             PreparedStatement pstmt2 = connection2.prepareStatement(sql2);) {

            pstmt2.setString(1, idTipoZombie);
            pstmt2.setString(2, idVarianteVirus);
            pstmt2.setString(3, nIdentificacion);
            pstmt2.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<String,String>  porcentajeZombie() {
        String sql = "SELECT H.sexo, count(idZombie)/(SELECT count(idZombie) FROM zombie) FROM zombie Z, humano H where Z.numeroIdentificacion = H.numeroIdentificacion group by H.sexo;";
        HashMap<String,String> hashMap = new HashMap<>();
        try (Connection conn = this.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {
            while(rs.next()){
                String sexo = rs.getString(1);
                String cantidad = rs.getString(2);
                hashMap.put(sexo,cantidad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    public HashMap<String,String>  promedioVictimas() {
        String sql = "SELECT T.nombre,avg(Z.victimas) FROM  tipozombie T left join zombie Z   on Z.idTipoZombie = T.idTipoZombie group by Z.idTipoZombie";
        HashMap<String,String> hashMap = new HashMap<>();
        try (Connection conn = this.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {
            while(rs.next()){
                String tipoZombie = new String(rs.getString(1).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
                String promedio = rs.getString(2);
                hashMap.put(tipoZombie,promedio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

}
