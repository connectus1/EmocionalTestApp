package com.example.testapp.LoginRegistro;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testapp.LoginActivity;

public class LoginVolley extends Volley implements Response.Listener<String>, Response.ErrorListener{
    private LoginRequest request;
    private Activity activity;
    private String url = "https://psicotest.speaksign.com.mx/API/user-account.php";

    public  LoginVolley(Activity activity, String correo, String contra){
        this.activity = activity;
        url += "?correo="+correo + "&contra=" + contra;
        request = new LoginRequest(Request.Method.GET, url,this::onResponse, this::onErrorResponse);
    }

    public void start(){
        newRequestQueue(activity).add(request);
    }

    public void close(){
        if (request != null){
            request.cancel();
        }
    }

    @Override
    public void onResponse(String response) {
        switch (response){
            case "ok": ((LoginActivity)activity).startIntent();
                 break;
            default: Toast.makeText(activity, "Correo o contraseña incorrecta", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(activity, "error:\n" + error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private static class LoginRequest extends StringRequest{
        public LoginRequest(int method, String url, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }
    }
}
