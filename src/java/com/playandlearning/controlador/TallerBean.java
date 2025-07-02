package com.playandlearning.controlador;

import com.playandlearning.dao.TallerDAO;
import com.playandlearning.modelo.Taller;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named("tallerBean")
@ViewScoped
public class TallerBean implements Serializable {

    private TallerDAO tallerDAO;
    private List<Taller> talleres;
    private Taller tallerSeleccionado;

    @PostConstruct
    public void init() {
        tallerDAO = new TallerDAO();
        cargarTalleres();

        // Vamos a revisar si el objeto viene del Flash Scope
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> flashMap = externalContext.getFlash();
        Taller tallerDesdeFlash = (Taller) flashMap.get("taller");

        if (tallerDesdeFlash != null) {
            // Si nos enviaron un taller para editar, lo usamos
            this.tallerSeleccionado = tallerDesdeFlash;
        } else {
            // Si no, creamos uno nuevo
            this.tallerSeleccionado = new Taller();
        }
    }

    private void cargarTalleres() {
        talleres = tallerDAO.listarTodos();
    }

    public String prepararEdicion(int idTaller) {
        Taller tallerAEditar = tallerDAO.buscarPorId(idTaller);

        // Usamos Flash Scope para pasar el objeto a la siguiente petición
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> flashMap = externalContext.getFlash();
        flashMap.put("taller", tallerAEditar);

        return "formularioTaller.xhtml?faces-redirect=true";
    }
    
    public String prepararNuevo() {
        // Al preparar uno nuevo, nos aseguramos de que el flash esté limpio
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.getFlash().put("taller", new Taller());
        
        return "formularioTaller.xhtml?faces-redirect=true";
    }

    public String guardar() {
        if (tallerSeleccionado.getId() == 0) {
            tallerDAO.agregar(tallerSeleccionado);
        } else {
            tallerDAO.actualizar(tallerSeleccionado);
        }
        return "gestionTalleres.xhtml?faces-redirect=true";
    }

    public void eliminar(int idTaller) {
        tallerDAO.eliminar(idTaller);
        cargarTalleres();
    }

    // Getters y Setters
    public List<Taller> getTalleres() { return talleres; }
    public Taller getTallerSeleccionado() { return tallerSeleccionado; }
    public void setTallerSeleccionado(Taller tallerSeleccionado) { this.tallerSeleccionado = tallerSeleccionado; }
}