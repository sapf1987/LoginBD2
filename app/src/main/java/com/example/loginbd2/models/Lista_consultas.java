package com.example.loginbd2.models;

public class Lista_consultas {

    private int id;
    private String especialidad,doctores,dias,estado;

    public Lista_consultas(int id, String especialidad, String doctores, String dias, String estado) {
        this.id = id;
        this.especialidad = especialidad;
        this.doctores = doctores;
        this.dias = dias;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getDoctores() {
        return doctores;
    }

    public void setDoctores(String doctores) {
        this.doctores = doctores;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
