package com.example.loginbd2.models;

public class Lista_cancelar {
    int id_c;
    String especialidad_c;
    String doctores_c;
    String dias_c;

    public Lista_cancelar(int id_c, String especialidad_c, String doctores_c, String dias_c) {
        this.id_c = id_c;
        this.especialidad_c = especialidad_c;
        this.doctores_c = doctores_c;
        this.dias_c = dias_c;
    }

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public String getEspecialidad_c() {
        return especialidad_c;
    }

    public void setEspecialidad_c(String especialidad_c) {
        this.especialidad_c = especialidad_c;
    }

    public String getDoctores_c() {
        return doctores_c;
    }

    public void setDoctores_c(String doctores_c) {
        this.doctores_c = doctores_c;
    }

    public String getDias_c() {
        return dias_c;
    }

    public void setDias_c(String dias_c) {
        this.dias_c = dias_c;
    }
}
