// Archivo: DocenteDAO.java (Versión Completa y Corregida)
package com.playandlearning.dao;

import com.playandlearning.modelo.Docente;
import com.playandlearning.util.ConexionDB;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocenteDAO implements Serializable {

    public List<Docente> listarTodos() {
        List<Docente> docentes = new ArrayList<>();
        String sql = "SELECT * FROM docentes";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Docente docente = new Docente();
                docente.setId(rs.getInt("id_docente"));
                docente.setNombre(rs.getString("nombre"));
                docente.setApellido(rs.getString("apellido"));
                docente.setEspecialidad(rs.getString("especialidad"));
                docente.setCorreo(rs.getString("correo"));
                docente.setTelefono(rs.getString("telefono"));
                docentes.add(docente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return docentes;
    }

    public Docente buscarPorId(int id) {
        Docente docente = null;
        String sql = "SELECT * FROM docentes WHERE id_docente = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    docente = new Docente();
                    docente.setId(rs.getInt("id_docente"));
                    docente.setNombre(rs.getString("nombre"));
                    docente.setApellido(rs.getString("apellido"));
                    docente.setEspecialidad(rs.getString("especialidad"));
                    docente.setCorreo(rs.getString("correo"));
                    docente.setTelefono(rs.getString("telefono"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return docente;
    }

    public void agregar(Docente docente) {
        String sql = "INSERT INTO docentes (nombre, apellido, especialidad, correo, telefono) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, docente.getNombre());
            stmt.setString(2, docente.getApellido());
            stmt.setString(3, docente.getEspecialidad());
            stmt.setString(4, docente.getCorreo());
            stmt.setString(5, docente.getTelefono());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =======================================================
    //          MÉTODO 'actualizar' QUE FALTABA
    // =======================================================
    public void actualizar(Docente docente) {
        String sql = "UPDATE docentes SET nombre = ?, apellido = ?, especialidad = ?, correo = ?, telefono = ? WHERE id_docente = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, docente.getNombre());
            stmt.setString(2, docente.getApellido());
            stmt.setString(3, docente.getEspecialidad());
            stmt.setString(4, docente.getCorreo());
            stmt.setString(5, docente.getTelefono());
            stmt.setInt(6, docente.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM docentes WHERE id_docente = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}