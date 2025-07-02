package com.playandlearning.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexionDB {
    // --- ¡¡¡MODIFICAR ESTOS DATOS!!! ---
    private static final String URL = "jdbc:mysql://centerbeam.proxy.rlwy.net:32598/railway";
    private static final String USER = "root";
    private static final String PASSWORD = "vkqLqRJXBhWHhxuFiOTYlpOWjJIZwaqh"; // Escriba su contraseña aquí
    // ------------------------------------

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error: MySQL JDBC Driver no encontrado.", e);
        }
    }
    
}
