package com.playandlearning.dao;

import com.playandlearning.modelo.Taller;
import com.playandlearning.util.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TallerDAO {

    // LISTAR TODOS (Read) - Ya lo teníamos
    public List<Taller> listarTodos() {
        List<Taller> talleres = new ArrayList<>();
        String sql = "SELECT * FROM talleres";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Taller taller = new Taller();
                taller.setId(rs.getInt("id_taller"));
                taller.setNombre(rs.getString("nombre"));
                taller.setArea(rs.getString("area"));
                taller.setDescripcion(rs.getString("descripcion"));
                talleres.add(taller);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return talleres;
    }
    
    // OBTENER UN TALLER POR SU ID (Necesario para la página de Editar)
    public Taller buscarPorId(int id) {
        Taller taller = null;
        String sql = "SELECT * FROM talleres WHERE id_taller = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    taller = new Taller();
                    taller.setId(rs.getInt("id_taller"));
                    taller.setNombre(rs.getString("nombre"));
                    taller.setArea(rs.getString("area"));
                    taller.setDescripcion(rs.getString("descripcion"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taller;
    }

    // INSERTAR NUEVO TALLER (Create) - Ya lo teníamos
    public void agregar(Taller taller) {
        String sql = "INSERT INTO talleres (nombre, area, descripcion) VALUES (?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, taller.getNombre());
            stmt.setString(2, taller.getArea());
            stmt.setString(3, taller.getDescripcion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ACTUALIZAR UN TALLER EXISTENTE (Update)
    public void actualizar(Taller taller) {
        String sql = "UPDATE talleres SET nombre = ?, area = ?, descripcion = ? WHERE id_taller = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, taller.getNombre());
            stmt.setString(2, taller.getArea());
            stmt.setString(3, taller.getDescripcion());
            stmt.setInt(4, taller.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ELIMINAR UN TALLER (Delete)
    public void eliminar(int id) {
        String sql = "DELETE FROM talleres WHERE id_taller = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
