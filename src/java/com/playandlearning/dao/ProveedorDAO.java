package com.playandlearning.dao;

import com.playandlearning.modelo.Proveedor;
import com.playandlearning.util.ConexionDB;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO implements Serializable {

    public List<Proveedor> listarTodos() {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedores";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Proveedor prov = new Proveedor();
                prov.setId(rs.getInt("id_proveedor"));
                prov.setRazonSocial(rs.getString("razon_social"));
                prov.setRuc(rs.getString("ruc"));
                prov.setContacto(rs.getString("contacto"));
                prov.setTelefono(rs.getString("telefono"));
                proveedores.add(prov);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return proveedores;
    }

    public Proveedor buscarPorId(int id) {
        Proveedor prov = null;
        String sql = "SELECT * FROM proveedores WHERE id_proveedor = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    prov = new Proveedor();
                    prov.setId(rs.getInt("id_proveedor"));
                    prov.setRazonSocial(rs.getString("razon_social"));
                    prov.setRuc(rs.getString("ruc"));
                    prov.setContacto(rs.getString("contacto"));
                    prov.setTelefono(rs.getString("telefono"));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return prov;
    }
    
    public void agregar(Proveedor prov) {
        String sql = "INSERT INTO proveedores (razon_social, ruc, contacto, telefono) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prov.getRazonSocial());
            stmt.setString(2, prov.getRuc());
            stmt.setString(3, prov.getContacto());
            stmt.setString(4, prov.getTelefono());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    
    public void actualizar(Proveedor prov) {
        String sql = "UPDATE proveedores SET razon_social = ?, ruc = ?, contacto = ?, telefono = ? WHERE id_proveedor = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prov.getRazonSocial());
            stmt.setString(2, prov.getRuc());
            stmt.setString(3, prov.getContacto());
            stmt.setString(4, prov.getTelefono());
            stmt.setInt(5, prov.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    
    public void eliminar(int id) {
        String sql = "DELETE FROM proveedores WHERE id_proveedor = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
