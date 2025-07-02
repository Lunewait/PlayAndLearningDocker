// Archivo: DocenteBean.java (Versión Final con Flash Scope)
package com.playandlearning.controlador;

import com.playandlearning.dao.DocenteDAO;
import com.playandlearning.modelo.Docente;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named("docenteBean")
@ViewScoped
public class DocenteBean implements Serializable {
    
    private DocenteDAO docenteDAO;
    private List<Docente> docentes;
    private Docente docenteSeleccionado;

    @PostConstruct
    public void init() {
        docenteDAO = new DocenteDAO();
        cargarDocentes();

        // 1. Revisa si un objeto 'docente' fue enviado a través del Flash Scope
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> flashMap = externalContext.getFlash();
        Docente docenteDesdeFlash = (Docente) flashMap.get("docente");

        if (docenteDesdeFlash != null) {
            // 2. Si venimos de 'Editar' o 'Nuevo', usamos el objeto del Flash
            this.docenteSeleccionado = docenteDesdeFlash;
        } else {
            // 3. Si no, creamos uno vacío
            this.docenteSeleccionado = new Docente();
        }
    }

    private void cargarDocentes() {
        docentes = docenteDAO.listarTodos();
    }

    // Acción para preparar el formulario para un nuevo docente
    public String prepararNuevo() {
        // Ponemos un objeto 'Docente' nuevo y vacío en el Flash Scope
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.getFlash().put("docente", new Docente());
        return "formularioDocente.xhtml?faces-redirect=true";
    }

    // Acción para preparar el formulario para editar un docente existente
    public String prepararEdicion(int idDocente) {
        Docente docenteAEditar = docenteDAO.buscarPorId(idDocente);
        // Ponemos el docente encontrado en el Flash Scope para pasarlo a la siguiente vista
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.getFlash().put("docente", docenteAEditar);
        return "formularioDocente.xhtml?faces-redirect=true";
    }

    // Acción para guardar (tanto nuevos como existentes)
    public String guardar() {
        if (docenteSeleccionado.getId() == 0) {
            docenteDAO.agregar(docenteSeleccionado);
        } else {
            docenteDAO.actualizar(docenteSeleccionado);
        }
        return "gestionDocentes.xhtml?faces-redirect=true";
    }

    // Acción para eliminar
    public void eliminar(int idDocente) {
        docenteDAO.eliminar(idDocente);
        cargarDocentes(); // Recargamos la lista
    }

    // Getters y Setters
    public List<Docente> getDocentes() { return docentes; }
    public Docente getDocenteSeleccionado() { return docenteSeleccionado; }
    public void setDocenteSeleccionado(Docente docenteSeleccionado) { this.docenteSeleccionado = docenteSeleccionado; }
}