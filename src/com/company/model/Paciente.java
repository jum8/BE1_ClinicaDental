package com.company.model;

import java.time.LocalDate;

public class Paciente {
    private long id;
    private String apellido;
    private String nombre;
    private int dni;
    private LocalDate fechaIngreso;
    private Domicilio domicilio;

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }


    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setId(long id) {
        this.id = id;
    }
}
