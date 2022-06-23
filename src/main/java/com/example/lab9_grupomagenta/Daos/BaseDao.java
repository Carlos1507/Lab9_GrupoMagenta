package com.example.lab9_grupomagenta.Daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDao {

    public Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        String username = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/laboratorio_9";

        return DriverManager.getConnection(url, username, password);
    }
}
