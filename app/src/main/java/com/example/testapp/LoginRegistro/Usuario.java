package com.example.testapp.LoginRegistro;

public class Usuario {
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;

    public Usuario(){}

    public Usuario(String nombre, String apellido, String correo, String contraseña) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido(){
        return apellido;
    }
    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
