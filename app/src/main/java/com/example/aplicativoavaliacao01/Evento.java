package com.example.aplicativoavaliacao01;

public class Evento {

    private int idimg;
    private String titulo, subtitulo,identificador;

    public Evento(String identificador, int idimg, String titulo, String subtitulo) {
        this.identificador = identificador;
        this.idimg = idimg;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public int getIdimg() {
        return idimg;
    }

    public void setIdimg(int idimg) {
        this.idimg = idimg;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }
}
