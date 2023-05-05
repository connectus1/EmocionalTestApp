package com.example.testapp.TestPager;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testapp.TestActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VolleyTest extends Volley implements Response.Listener<String>, Response.ErrorListener {
    private TestRequest request;
    private Activity activity;
    private String url = "https://biosur365.com/emocionalapp/get-test.php";

    public VolleyTest(Activity activity, String id){
        this.activity = activity;
        url = url + "?id=" + id;

        request = new TestRequest(Request.Method.GET, url, this::onResponse, this::onErrorResponse);
    }

    // Inicia la cola de peticiones Volley
    public void start(){
        newRequestQueue(activity).add(request);
    }

    // Si el Request no es nulo entonces cancela la peticion actual
    public void close(){
        if (request != null){
            request.cancel();
        }
    }


    @Override
    public void onResponse(String response) {
        if (response.equals("error")){
            Toast.makeText(activity, "Error para cargar el test", Toast.LENGTH_SHORT).show();
            return;
        }

        ((TestActivity)activity).initPager(responseToList(response));
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show();
        close();
    }

    // Convierte el String Response en un JSONObject iterable;
    // Se extrae el valor y lo agrega a un List<ItemQuestion>
    private List<ItemQuestion> responseToList(String response){
        List<ItemQuestion> questionList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject cuerpo = new JSONObject(jsonObject.get("cuerpo").toString());

            Iterator<String> keys = cuerpo.keys();
            while (keys.hasNext()) {
                String key = keys.next();

                JSONObject value = new JSONObject(cuerpo.get(key).toString());
                questionList.add(new ItemQuestion(value.get("pregunta").toString()));
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return questionList;
    }

    protected static class TestRequest extends StringRequest{
        public TestRequest(int method, String url, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }
    }
}
