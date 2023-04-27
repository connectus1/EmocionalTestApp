package com.example.testapp.LoginRegistro;

import org.json.JSONException;
import org.json.JSONObject;

public class Usuario {
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;
    private String fechaNac;

    public Usuario(){}

    public Usuario(String nombre, String apellido, String correo, String contraseña, String fechaNac) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;

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

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public JSONObject getJson(){
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("nombre", getNombre());
            jsonBody.put("apellido", getApellido());
            jsonBody.put("fechanac", getFechaNac());
            jsonBody.put("correo", getCorreo());
            jsonBody.put("contra",getContraseña());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonBody;
    }

}
