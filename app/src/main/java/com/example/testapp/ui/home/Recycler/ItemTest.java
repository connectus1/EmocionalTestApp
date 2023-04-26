package com.example.testapp.ui.home.Recycler;

import android.telephony.ims.ImsMmTelManager;

public class ItemTest {
    private String titulo;
    private String id;
    private int Image;

    public ItemTest() {
    }

    public ItemTest(String titulo, String id, int image) {
        this.titulo = titulo;
        this.id = id;
        Image = image;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
