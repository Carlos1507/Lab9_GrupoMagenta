package com.example.lab9_grupomagenta.Daos;

import com.example.lab9_grupomagenta.Beans.BEfectividad;
import com.example.lab9_grupomagenta.Beans.BObjeto;

import java.sql.*;
import java.util.ArrayList;

public class ObjetosDao extends BaseDao{
    public ArrayList<BObjeto> listarObjetos(){
        ArrayList<BObjeto> listaObjeto = new ArrayList<>();
        String sql = "select nombre, masa, vacuna, idObjeto from objeto;";
        try(Connection conn = this.obtenerConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);){
            while(rs.next()){
                BObjeto objeto = new BObjeto();
                objeto.setNombre(rs.getString(1));
                objeto.setMasa(rs.getDouble(2));
                objeto.setVacuna(rs.getInt(3));
                objeto.setIdObjeto(rs.getInt(4));
                listaObjeto.add(objeto);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return listaObjeto;
    }

    public ArrayList<BObjeto> efectividadTipoZombie(String idObjeto){
        ArrayList<BObjeto> listaObj = new ArrayList<>();

        String sql = "select porcentaje, tz.nombre\n" +
                "from efectividad e \n" +
                "\tinner join tipozombie tz on (e.idTipoZombie = tz.idTipoZombie)\n" +
                "    inner join vacuna v on (e.idVacuna = v.idVacuna)\n" +
                "    inner join objeto o on (o.idVacuna = v.idVacuna)\n" +
                "where idObjeto = ?";

        try (Connection connection = this.obtenerConexion();
             PreparedStatement pstmt = connection.prepareStatement(sql);){

            pstmt.setInt(1,Integer.parseInt(idObjeto));

            try(ResultSet rs = pstmt.executeQuery();){
                while (rs.next()){
                    BObjeto objeto = new BObjeto();
                    objeto.setMasa(rs.getDouble(1)); //Porcentaje
                    objeto.setNombre(rs.getString(2));
                    listaObj.add(objeto);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return listaObj;
    }

    public void guardarObjetoN(String nombre, double masa, int vacuna, int cantidad){

        String sql = "INSERT INTO `lab9`.`objeto` (`nombre`, `masa`, `vacuna`, `cantidad`) VALUES (?, ?, ?, ?);";

        try (Connection connection = this.obtenerConexion();
             PreparedStatement pstmt = connection.prepareStatement(sql);){

            pstmt.setString(1,nombre);
            pstmt.setDouble(2,masa);
            pstmt.setInt(3,vacuna);
            pstmt.setInt(4,cantidad);
            pstmt.executeUpdate(); //Es update porque es para insert, update y delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarObjetoV(String nombre, double masa, int vacuna, int cantidad, int idVacuna){



        String sql = "INSERT INTO `lab9`.`objeto` (`nombre`, `masa`, `vacuna`, `cantidad`, `idVacuna`) VALUES (?, ?, ?, ?, ?);";

        try (Connection connection = this.obtenerConexion();
             PreparedStatement pstmt = connection.prepareStatement(sql);){

            pstmt.setString(1,nombre);
            pstmt.setDouble(2,masa);
            pstmt.setInt(3,vacuna);
            pstmt.setInt(4,cantidad);
            pstmt.setInt(5,idVacuna);
            pstmt.executeUpdate(); //Es update porque es para insert, update y delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearVacuna(double masa){

        String sql = "INSERT INTO `lab9`.`vacuna` (`peso`) VALUES (?);";

        try (Connection connection = this.obtenerConexion();
             PreparedStatement pstmt = connection.prepareStatement(sql);){

            pstmt.setDouble(1,masa);
            pstmt.executeUpdate(); //Es update porque es para insert, update y delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> listarIdVacuna(){
        ArrayList<Integer> listaVacuna = new ArrayList<>();

        String sql = "SELECT idVacuna FROM lab9.vacuna;";

        try (Connection connection = this.obtenerConexion();
             PreparedStatement pstmt = connection.prepareStatement(sql);){

            try(ResultSet rs = pstmt.executeQuery();){
                while (rs.next()){
                    listaVacuna.add(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaVacuna;
    }

    public void guardarEfectividad(int idVacuna,int idTipoZombie, double porcentaje){

        String sql = "INSERT INTO `lab9`.`efectividad` (`idVacuna`, `idTipoZombie`, `porcentaje`) VALUES (?, ?, ?);";

        try (Connection connection = this.obtenerConexion();
             PreparedStatement pstmt = connection.prepareStatement(sql);){

            pstmt.setInt(1,idVacuna);
            pstmt.setInt(2,idTipoZombie);
            pstmt.setDouble(3,porcentaje);
            pstmt.executeUpdate(); //Es update porque es para insert, update y delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> objetosEnInventarSuper(){
        ArrayList<Integer> listaobb = new ArrayList<>();
        String sql = "select idObjeto from superviviente s inner join inventario i on (s.idSuperviviente = i.idSuperviviente) group by idObjeto";
        try(Connection conn = this.obtenerConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);){
            while(rs.next()){
                listaobb.add(rs.getInt(1));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return listaobb;
    }

    public BObjeto buscarObjeto(String idObjeto){
        BObjeto bObjeto = null;

        String sql = "select * from objeto where idObjeto = ?;";

        try (Connection connection = this.obtenerConexion();
             PreparedStatement pstmt = connection.prepareStatement(sql);){
            pstmt.setInt(1,Integer.parseInt(idObjeto));

            try (ResultSet rs = pstmt.executeQuery();){
                if (rs.next()){  //Si hay un resultado, lleno mi bjob, si no encuentro, se queda como null
                    bObjeto = new BObjeto();
                    bObjeto.setIdObjeto(rs.getInt(1));
                    bObjeto.setNombre(rs.getString(2));
                    bObjeto.setMasa(rs.getDouble(3));
                    bObjeto.setVacuna(rs.getInt(4));
                    bObjeto.setCantidad(rs.getInt(5));
                    bObjeto.setIdVacuna(rs.getInt(6));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return bObjeto;
    }

    public ArrayList<BEfectividad> buscarVacuna(String idVacuna){
        ArrayList<BEfectividad> listaEfectividad = new ArrayList<>();

        String sql = "select * from efectividad where idVacuna = ?;";

        try (Connection connection = this.obtenerConexion();
             PreparedStatement pstmt = connection.prepareStatement(sql);){
            pstmt.setInt(1,Integer.parseInt(idVacuna));

            try (ResultSet rs = pstmt.executeQuery();){
                while (rs.next()){
                    BEfectividad bEfectividad = new BEfectividad();
                    bEfectividad.setIdVacuna(rs.getInt(1));
                    bEfectividad.setIdTipoZombie(rs.getInt(2));
                    bEfectividad.setPorcentaje(rs.getDouble(3));
                    listaEfectividad.add(bEfectividad);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return listaEfectividad;
    }

    public void actualizar(String nombre,String peso, String idObjeto){

        String sql = "UPDATE `lab9`.`objeto` SET `nombre` = ?, `masa` = ? WHERE (`idObjeto` = ?)";

        try (Connection connection = this.obtenerConexion();
             PreparedStatement pstmt = connection.prepareStatement(sql);){

            pstmt.setString(1,nombre);
            pstmt.setDouble(2,Double.parseDouble(peso));
            pstmt.setInt(3,Integer.parseInt(idObjeto));
            pstmt.executeUpdate(); //Es update porque es para insert, update y delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarVacuna(String idVacuna,int idTipoZombie,String porcentaje){

        String sql = "UPDATE `lab9`.`efectividad` SET `porcentaje` = ? WHERE (`idVacuna` = ?) and (`idTipoZombie` = ?);";

        try (Connection connection = this.obtenerConexion();
             PreparedStatement pstmt = connection.prepareStatement(sql);){

            pstmt.setDouble(1,Double.parseDouble(porcentaje));
            pstmt.setInt(2,Integer.parseInt(idVacuna));
            pstmt.setInt(3,idTipoZombie);
            pstmt.executeUpdate(); //Es update porque es para insert, update y delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
