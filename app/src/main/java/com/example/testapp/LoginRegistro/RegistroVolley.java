package com.example.testapp.LoginRegistro;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testapp.BottomDialog.BottomSheetRegistro;


public class RegistroVolley extends Volley implements Response.Listener<String>, Response.ErrorListener {
    private Activity activity;
    private Usuario usuario;
    private String url = "https://biosur365.com/emocionalapp/user-account.php";
    private RequestRegistro requestRegistro;

    public RegistroVolley(Activity activity, Usuario usuario) {
        this.activity = activity;
        this.usuario = usuario;

        requestRegistro = new RequestRegistro(Request.Method.POST, url,this::onResponse, this::onErrorResponse){
            @Override
            public byte[] getBody() throws AuthFailureError {
                return usuario.getJson().toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
    }

    //Ingresa a la cola de peticiones de volley el Request
    public void registrar(){
        newRequestQueue(activity).add(requestRegistro);
    }

    //Si el Request no es nulo, cierra la peticion por Volley
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
        if (response.equals("ok")){
            BottomSheetRegistro.closeDialog();
        }
    }

    private static class RequestRegistro extends StringRequest{

        public RequestRegistro(int method, String url, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }
    }
}
