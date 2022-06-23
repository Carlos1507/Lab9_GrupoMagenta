package com.example.lab9_grupomagenta.Daos;

import com.example.lab9_grupomagenta.Beans.BObjeto;
import com.example.lab9_grupomagenta.Beans.BVariante;
import com.example.lab9_grupomagenta.Beans.BVirus;

import java.sql.*;
import java.util.ArrayList;

public class VirusDao extends BaseDao{
    public ArrayList<BVirus> listarVirus(){
        ArrayList<BVirus> listaVir = new ArrayList<>();
        String sql = "select * from virus;";
        try(Connection conn = this.obtenerConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);){
            while(rs.next()){
                BVirus bVirus = new BVirus();
                bVirus.setIdVirus(rs.getInt(1));
                bVirus.setNombre(rs.getString(2));
                bVirus.setCasosEncontrados(buscarCasosEncontrados(rs.getInt(1))); //
                listaVir.add(bVirus);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return listaVir;
    }

    public int buscarCasosEncontrados(int idVirus){
        int numCasos = 0;

        String sql = "select count(*) from zombie z \n" +
                "inner join variantevirus vv on (z.idVarianteVirus = vv.idVarianteVirus)\n" +
                "inner join virus vi on (vi.idVirus = vv.idVirus)\n" +
                "where vi.idVirus = ?\n" +
                "group by vi.idVirus";

        try (Connection connection = this.obtenerConexion();
             PreparedStatement pstmt = connection.prepareStatement(sql);){
            pstmt.setInt(1,idVirus);

            try (ResultSet rs = pstmt.executeQuery();){
                if (rs.next()){
                    numCasos = rs.getInt(1);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return numCasos;
    }


    public ArrayList<BVariante> listarVariante(){
        ArrayList<BVariante> listaVari = new ArrayList<>();
        String sql = "select idVarianteVirus, vv.nombre, gradoInfectividad, vi.idvirus from virus vi inner join variantevirus vv on (vi.idvirus = vv.idvirus);";
        try(Connection conn = this.obtenerConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);){
            while(rs.next()){
                BVariante bVariante = new BVariante();
                bVariante.setIdVarianteVirus(rs.getInt(1));
                bVariante.setNombre(rs.getString(2));
                bVariante.setGradoInfectividad(rs.getDouble(3));
                bVariante.setIdVirus(rs.getInt(4));
                listaVari.add(bVariante);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return listaVari;
    }

    public void crearVirus(String nombre){

        String sql = "INSERT INTO virus (`nombre`, `casosEncontrados`) VALUES (?, ?);";

        try (Connection connection = this.obtenerConexion();
             PreparedStatement pstmt = connection.prepareStatement(sql);){

            pstmt.setString(1,nombre);
            pstmt.setInt(2,0); //Al crear un virus no necesarimente hay infectados ya
            pstmt.executeUpdate(); //Es update porque es para insert, update y delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearVariante(String nombre, int idVirus){

        String sql = "INSERT INTO variantevirus (`nombre`, `gradoInfectividad`, `idVirus`) VALUES (?, ?, ?);";

        try (Connection connection = this.obtenerConexion();
             PreparedStatement pstmt = connection.prepareStatement(sql);){

            pstmt.setString(1,nombre);
            pstmt.setInt(2,80);
            pstmt.setInt(3,idVirus);
            pstmt.executeUpdate(); //Es update porque es para insert, update y delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String buscarVirus(String nombre){
        BVirus bVirus = null;
        int k = 0;
        for (BVirus virus : this.listarVirus()){
            if(virus.getNombre().equalsIgnoreCase(nombre)){
                k = 1;
                break;
            }
        }

        if (k==0){
            bVirus = new BVirus();
            bVirus.setNombre(nombre);
        }

        try{
            bVirus.getNombre();
            String rpta = "noExiste";
            return rpta;
        } catch (NullPointerException e) {
            String rpta = "Existe";
            return rpta;
        }
    }

    public String buscarVariante(String nombre){
        BVariante bVariante = null;
        int k = 0;
        for (BVariante variante : this.listarVariante()){
            if(variante.getNombre().equalsIgnoreCase(nombre)){
                k = 1;
                break;
            }
        }

        if (k==0){
            bVariante= new BVariante();
            bVariante.setNombre(nombre);
        }

        try{
            bVariante.getNombre();
            String rpta = "noExiste";
            return rpta;
        } catch (NullPointerException e) {
            String rpta = "Existe";
            return rpta;
        }
    }

    public void borrarVariante(String idVariante){

        String sql = "delete from variantevirus where idVarianteVirus = ?";

        try (Connection connection = this.obtenerConexion();
             PreparedStatement pstmt = connection.prepareStatement(sql);){

            pstmt.setInt(1,Integer.parseInt(idVariante));
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarVirus(String idVirus){

        String sql = "delete from virus where idVirus = ?";

        try (Connection connection = this.obtenerConexion();
             PreparedStatement pstmt = connection.prepareStatement(sql);){

            pstmt.setInt(1,Integer.parseInt(idVirus));
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarSinVariante(){
        String sql = "select * from virus;";
        try(Connection conn = this.obtenerConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);){
            while(rs.next()){
                BVirus bVirus = new BVirus();
                bVirus.setIdVirus(rs.getInt(1));
                bVirus.setNombre(rs.getString(2));
                bVirus.setCasosEncontrados(rs.getInt(3));

                for (BVariante bVariante : listarVariante()){
                    if (bVariante.getIdVirus() == rs.getInt(1)){
                        break;
                    }else{
                        borrarVirus(String.valueOf(rs.getInt(1)));
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> listarIdZombie(String idVariante){
        ArrayList<Integer> ids = new ArrayList<>();

        String sql = "select idZombie from variantevirus v\n" +
                "\tinner join zombie z on (v.idVarianteVirus = z.idVarianteVirus)\n" +
                "where v.idVarianteVirus = ?";

        try (Connection connection = this.obtenerConexion();
             PreparedStatement pstmt = connection.prepareStatement(sql);){

            pstmt.setInt(1,Integer.parseInt(idVariante));

            try(ResultSet rs = pstmt.executeQuery();){
                while (rs.next()){
                    ids.add(rs.getInt(1));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return ids;
    }

    public void borrarZombies(String idVariante){

        String sql = "delete from zombie where idZombie = ?";

        try (Connection connection = this.obtenerConexion();
             PreparedStatement pstmt = connection.prepareStatement(sql);){

            for(int i=0;i<listarIdZombie(idVariante).size();i++){
                pstmt.setInt(1,listarIdZombie(idVariante).get(i));
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
