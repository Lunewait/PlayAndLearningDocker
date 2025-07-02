package com.playandlearning.controlador;

import com.playandlearning.dao.ProveedorDAO;
import com.playandlearning.modelo.Proveedor;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named("proveedorBean")
@ViewScoped
public class ProveedorBean implements Serializable {
    
    private ProveedorDAO proveedorDAO;
    private List<Proveedor> proveedores;
    private Proveedor proveedorSeleccionado;

    @PostConstruct
    public void init() {
        proveedorDAO = new ProveedorDAO();
        cargarProveedores();
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> flashMap = externalContext.getFlash();
        Proveedor proveedorDesdeFlash = (Proveedor) flashMap.get("proveedor");

        if (proveedorDesdeFlash != null) {
            this.proveedorSeleccionado = proveedorDesdeFlash;
        } else {
            this.proveedorSeleccionado = new Proveedor();
        }
    }

    private void cargarProveedores() {
        proveedores = proveedorDAO.listarTodos();
    }
    
    public String prepararNuevo() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.getFlash().put("proveedor", new Proveedor());
        return "formularioProveedor.xhtml?faces-redirect=true";
    }
    
    public String prepararEdicion(int idProveedor) {
        Proveedor proveedorAEditar = proveedorDAO.buscarPorId(idProveedor);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.getFlash().put("proveedor", proveedorAEditar);
        return "formularioProveedor.xhtml?faces-redirect=true";
    }

    public String guardar() {
        if (proveedorSeleccionado.getId() == 0) {
            proveedorDAO.agregar(proveedorSeleccionado);
        } else {
            proveedorDAO.actualizar(proveedorSeleccionado);
        }
        return "gestionProveedores.xhtml?faces-redirect=true";
    }

    public void eliminar(int idProveedor) {
        proveedorDAO.eliminar(idProveedor);
        cargarProveedores();
    }
    
    // Getters y Setters
    public List<Proveedor> getProveedores() { return proveedores; }
    public Proveedor getProveedorSeleccionado() { return proveedorSeleccionado; }
    public void setProveedorSeleccionado(Proveedor proveedorSeleccionado) { this.proveedorSeleccionado = proveedorSeleccionado; }
}
