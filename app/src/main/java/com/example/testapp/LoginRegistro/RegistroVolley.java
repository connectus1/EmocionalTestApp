package com.example.testapp.LoginRegistro;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class RegistroVolley extends Volley implements Response.Listener<String>, Response.ErrorListener {
    private Activity activity;
    private Usuario usuario;
    private String url = "";
    private RequestRegistro requestRegistro;

    public RegistroVolley(Activity activity, Usuario usuario) {
        this.activity = activity;

        this.usuario = usuario;
        setUrl(usuario);

        requestRegistro = new RequestRegistro(Request.Method.PUT, url,this::onResponse, this::onErrorResponse);
    }

    private void setUrl(@NonNull Usuario usuario){
        url += "?correo=" + usuario.getCorreo()
                + "&contra=" + usuario.getContraseña()
                + "&nombre=" + usuario.getNombre()
                + "&apellido="+ usuario.getApellido();
    }

    public void registrar(){
        newRequestQueue(activity).add(requestRegistro);
    }

    public void close(){
        if (requestRegistro != null){
            requestRegistro.cancel();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(activity, "Error:\n" + error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(String response) {

    }

    private static class RequestRegistro extends StringRequest{

        public RequestRegistro(int method, String url, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }
    }
}
