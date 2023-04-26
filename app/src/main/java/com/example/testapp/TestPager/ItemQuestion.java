package com.example.testapp.TestPager;

public class ItemQuestion {
    private String texto;
    private int puntuacion;

    public ItemQuestion() {
    }

    public ItemQuestion(String texto) {
        this.texto = texto;
        this.puntuacion = 0;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
