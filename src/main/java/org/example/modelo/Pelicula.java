package org.example.modelo;

public class Pelicula {

    private int id = 0;
    private String titulo = "";
    private String protagonista = "";
    private String tematica = "";
    private String guion = "";
    private Boolean disponible = true;

    public Pelicula(int id, String titulo, String protagonista, String tematica, String guion, Boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.protagonista = protagonista;
        this.tematica = tematica;
        this.guion = guion;
        this.disponible = disponible;
    }

    public Pelicula(String titulo, String protagonista, String tematica, String guion, Boolean disponible) {
        this.titulo = titulo;
        this.protagonista = protagonista;
        this.tematica = tematica;
        this.guion = guion;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getProtagonista() {
        return protagonista;
    }

    public void setProtagonista(String protagonista) {
        this.protagonista = protagonista;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getGuion() {
        return guion;
    }

    public void setGuion(String guion) {
        this.guion = guion;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
}
