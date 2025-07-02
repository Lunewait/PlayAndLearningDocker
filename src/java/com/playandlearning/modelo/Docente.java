package com.playandlearning.modelo;

import java.io.Serializable;

public class Docente implements Serializable {
    private int id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String correo;
    private String telefono;

    // Constructor vacío (requerido por JSF)
    public Docente() {}

    // Getters y Setters para todas las propiedades
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}