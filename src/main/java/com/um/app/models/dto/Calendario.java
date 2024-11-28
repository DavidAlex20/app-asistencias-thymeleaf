package com.um.app.models.dto;

import jakarta.validation.constraints.NotEmpty;

public class Calendario {
    @NotEmpty private int id;
    @NotEmpty private int materia_id;
    @NotEmpty private int aula_id;
    @NotEmpty private int dia;
    @NotEmpty private String inicio;
    @NotEmpty private String fin;
    @NotEmpty private int numaula;
    @NotEmpty private String alias;
    @NotEmpty private String materia_nombre;
    @NotEmpty private int maestro_id;

    private boolean started = false;
    private boolean finished = false;

    public Calendario(int id, int materia_id, int aula_id, int dia, String inicio, String fin, int numaula, String alias, String materia_nombre, int maestro_id) {
        this.id = id;
        this.materia_id = materia_id;
        this.aula_id = aula_id;
        this.dia = dia;
        this.inicio = inicio;
        this.fin = fin;
        this.numaula = numaula;
        this.alias = alias;
        this.materia_nombre = materia_nombre;
        this.maestro_id = maestro_id;
    }

    public int getId() {
        return id;
    }

    public int getMateria_id() {
        return materia_id;
    }

    public int getAula_id() {
        return aula_id;
    }

    public int getDia() {
        return dia;
    }

    public String getInicio() {
        return inicio;
    }

    public String getFin() {
        return fin;
    }

    public int getNumaula() {
        return numaula;
    }

    public String getAlias() {
        return alias;
    }

    public String getMateria_nombre() {
        return materia_nombre;
    }

    public int getMaestro_id() {
        return maestro_id;
    }
    
    public boolean getStarted() {
        return this.started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean getFinished() {
        return this.finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", materia_id='" + getMateria_id() + "'" +
            ", aula_id='" + getAula_id() + "'" +
            ", dia='" + getDia() + "'" +
            ", inicio='" + getInicio() + "'" +
            ", fin='" + getFin() + "'" +
            ", numaula='" + getNumaula() + "'" +
            ", alias='" + getAlias() + "'" +
            ", materia_nombre='" + getMateria_nombre() + "'" +
            ", maestro_id='" + getMaestro_id() + "'" +
            ", started='" + getStarted() + "'" +
            ", finished='" + getFinished() + "'" +
            "}";
    }

    
}
