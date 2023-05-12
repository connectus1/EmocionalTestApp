package com.example.testapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.testapp.BottomDialog.BottomSheetQR;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Opciones del BottomNavigation
        binding.bottomNavigationView.setItemIconTintList(null);
        binding.bottomNavigationView.findViewById(R.id.nav_off).setOnClickListener(clickOff);

        binding.fabQR.setOnClickListener(clickQR);
        binding.fabQR.getDrawable().mutate().setTint(getColor(R.color.black));

    }

    private View.OnClickListener clickQR = view ->{
            BottomSheetQR qr = new BottomSheetQR(this);
            qr.show();
    };

    private View.OnClickListener clickOff = view -> {
          deleteAccount();
    };

    private void deleteAccount(){
        exitAccount();
    }

    private void exitAccount(){
        SharedPreferences preferences = getSharedPreferences("translate", Context.MODE_PRIVATE);
        AlertDialog dialog = null;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Esta seguro de salir?");
        builder.setTitle("Cerrar Sesión");

        builder.setPositiveButton("Aceptar", (dialogInterface, i) -> {
            if(preferences.getString("correo", null) != null){
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit(); editor.apply();
            }

            finalizar();
        }).setNegativeButton("Cancelar", (dialogInterface, i) -> dialogInterface.dismiss());


        dialog = builder.create();
        dialog.show();

    }

    private void finalizar(){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);

        finish();
    }
}