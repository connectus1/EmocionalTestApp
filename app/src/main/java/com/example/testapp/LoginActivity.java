package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.testapp.BottomDialog.BottomSheetRegistro;
import com.example.testapp.LoginRegistro.LoginVolley;
import com.example.testapp.databinding.LoginMainBinding;

public class LoginActivity extends AppCompatActivity {
    private LoginVolley loginVolley;
    private LoginMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = LoginMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(clickLogin);
        binding.txtRegistro.setOnClickListener(clickRegistro);
    }

    public void startIntent(){//Inicia la actividad principal

        if (binding.chkRecordar.isActivated()){ //Si la opcion de guardar cuenta esta activado
            saveAccount();
        }

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

        finish();
    }


    //Guarda el correo y contraseña ingresado por el usuario
    private void saveAccount(){
        SharedPreferences preferences = getSharedPreferences("TestApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        //Si existen credenciales previamente guardadas, entonces las elimina para guardar las nuevas limpiamente
        if (preferences.getString("correo", null) != null){
            editor.clear();
            editor.commit();
        }

        editor.putString("correo", binding.editCorreo.getEditableText().toString());
        editor.putString("contra", binding.editContra.getEditableText().toString());
        editor.commit();
    }

    //===============================
    // Listener OnClickListener
    //===============================
    private View.OnClickListener clickLogin = view -> {
        startIntent();
//        loginVolley = new LoginVolley();
//        loginVolley.start();
    };

    private View.OnClickListener clickRegistro = view -> {
        BottomSheetRegistro bottomSheetRegistro = new BottomSheetRegistro(this);
        bottomSheetRegistro.show();
    };

    //===================================
    // Estados de la actividad
    //===================================
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginVolley != null)
            loginVolley.close();
    }

}