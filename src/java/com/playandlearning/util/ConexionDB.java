package com.playandlearning.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexionDB {
    // --- ¡¡¡MODIFICAR ESTOS DATOS!!! ---
    private static final String URL = "jdbc:mysql://localhost:3306/playandlearning_db";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Escriba su contraseña aquí
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
