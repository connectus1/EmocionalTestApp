package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.testapp.BottomDialog.BottomSheetRegistro;
import com.example.testapp.LoginRegistro.LoginVolley;
import com.example.testapp.databinding.LoginMainBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    private LoginVolley loginVolley;
    private LoginMainBinding binding;
    private Calendar calendario = Calendar.getInstance();
    private BottomSheetRegistro bottomSheetRegistro;

    private void changeTheme(){
        //Tiempo de espera de 2 segundos para cambiar el Theme de la aplicacion
        try {
            setTheme(R.style.Theme_TestApp_NoActionBar);
            isLogin();

            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeTheme(); //Para cambiar el tema actual
        super.onCreate(savedInstanceState);

        binding = LoginMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(clickLogin);
        binding.txtRegistro.setOnClickListener(clickRegistro);
    }

    public void startIntent(){//Inicia la actividad principal

        if (binding.chkRecordar.isChecked()){ //Si la opcion de guardar cuenta esta activado
            saveAccount();
        }

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

        finish();
    }


    //Guarda el correo y contraseña ingresado por el usuario
    private void saveAccount(){
        SharedPreferences preferences = getSharedPreferences("translate", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        //Si existen credenciales previamente guardadas, entonces las elimina para guardar las nuevas limpiamente
        if (preferences.getString("correo", null) != null){
            editor.clear();
            editor.commit(); editor.apply();
        }

        editor.putString("correo", binding.editCorreo.getEditableText().toString());
        editor.putString("contra", binding.editContra.getEditableText().toString());
        editor.commit(); editor.apply();
    }

    //Verifica si el usuario ya ha guardado datos de inicio de sesion.
    private void isLogin(){
        SharedPreferences preferences = getSharedPreferences("translate", Context.MODE_PRIVATE);
        Log.e("isLogin", preferences.getString("correo", "Sin correo"));

        if (preferences.getString("correo", null) != null){
            String correo = preferences.getString("correo", null);
            String contra = preferences.getString("contra",null);

            loginVolley = new LoginVolley(this,correo,contra);
            loginVolley.start();
        }

    }

    //===============================
    // Listener OnClickListener
    //===============================
    private View.OnClickListener clickLogin = view -> {
        if (binding.editCorreo.getEditableText().toString().isEmpty() || binding.editContra.getEditableText().toString().isEmpty()){
            Toast.makeText(this, "Algun dato sin rellenar", Toast.LENGTH_SHORT).show();
            return;
        }

        loginVolley = new LoginVolley(this,binding.editCorreo.getEditableText().toString(), binding.editContra.getEditableText().toString());
        loginVolley.start();
    };

    private View.OnClickListener clickRegistro = view -> {
        bottomSheetRegistro = new BottomSheetRegistro(this);
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

    //===================================
    // DatePickerDialog para el registro
    //===================================
    public void showCalendar(){
        new DatePickerDialog(this, date, calendario
                .get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)).show();
    }

    private DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
        calendario.set(Calendar.YEAR, year);
        calendario.set(Calendar.MONTH, monthOfYear);
        calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        actualizarInput();
    };

    private void actualizarInput() {
        String formatoDeFecha = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.getDefault());
        bottomSheetRegistro.setCalendarInput(sdf.format(calendario.getTime()));

    }


}