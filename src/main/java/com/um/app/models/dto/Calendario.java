package com.um.app.models.dto;

import jakarta.validation.constraints.NotEmpty;

public class Calendario {
    @NotEmpty private int materia_id;
    @NotEmpty private int aula_id;
    @NotEmpty private int dia;
    @NotEmpty private String inicio;
    @NotEmpty private String fin;
    @NotEmpty private int numaula;
    @NotEmpty private String alias;
    @NotEmpty private String materia_nombre;

    public Calendario(int materia_id, int aula_id, int dia, String inicio, String fin, int numaula, String alias, String materia_nombre) {
        this.materia_id = materia_id;
        this.aula_id = aula_id;
        this.dia = dia;
        this.inicio = inicio;
        this.fin = fin;
        this.numaula = numaula;
        this.alias = alias;
        this.materia_nombre = materia_nombre;
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

    @Override
    public String toString() {
        return "{" +
            " materia_id='" + getMateria_id() + "'" +
            ", aula_id='" + getAula_id() + "'" +
            ", dia='" + getDia() + "'" +
            ", inicio='" + getInicio() + "'" +
            ", fin='" + getFin() + "'" +
            ", numaula='" + getNumaula() + "'" +
            ", alias='" + getAlias() + "'" +
            ", materia_nombre='" + getMateria_nombre() + "'" +
            "}";
    }
    
}
